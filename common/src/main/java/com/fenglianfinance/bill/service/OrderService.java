/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.service;

import com.fenglianfinance.bill.SerialNumberGeneratorUtils;
import javax.inject.Inject;

import com.fenglianfinance.bill.exception.*;

import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fenglianfinance.bill.DTOUtils;
import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.PurchaseOrder.Status;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.gateway.common.GatewayService;
import com.fenglianfinance.bill.messaging.api.MessagingConstants;
import com.fenglianfinance.bill.model.OrderDetails;
import com.fenglianfinance.bill.model.OrderForm;
import com.fenglianfinance.bill.model.OrderMessage;
import com.fenglianfinance.bill.model.OrderSN;
import com.fenglianfinance.bill.model.OrderSearchCriteria;
import com.fenglianfinance.bill.repository.OrderRepository;
import com.fenglianfinance.bill.repository.OrderSpecifications;
import com.fenglianfinance.bill.repository.ProductRepository;
import com.fenglianfinance.bill.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hansy
 */
@Service
@Transactional
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private SerialNumberGeneratorUtils generator;

    @Inject
    private GatewayService gatewayService;

    @Inject
    private UserRepository userRepository;

    public OrderSN placeOrder(Long userId, OrderForm orderForm) {
        Assert.notNull(userId, "userId is not null");
        Assert.notNull(orderForm, "orderForm is not null");
        Assert.notNull(orderForm.getProductId(), "orderForm is not null");

        if (log.isDebugEnabled()) {
            log.debug("place order@ userId @" + userId + ", orderForm@" + orderForm);
        }

        if (orderRepository.countByUserIdAndStatus(userId, PurchaseOrder.Status.PENDING_PAYMENT) > 0) {
            throw new PendingOrderExistedException();
        }

        checkIfProductIsOutOfStock(orderForm);

        String serialNumber = generator.nextOrderSerialNumber();

        if (log.isDebugEnabled()) {
            log.debug("generating order serial number @" + serialNumber);
        }

        Product product = productRepository.findOne(orderForm.getProductId());

        rabbitTemplate.convertAndSend(
                MessagingConstants.EXCHANGE_ORDER,
                MessagingConstants.ROUTING_ORDER,
                new OrderMessage(userId, orderForm.getProductId(),
                        serialNumber, product.getUnitPrice().multiply(new BigDecimal(orderForm.getCount())))
        );

        UserAccount user = userRepository.findOne(userId);

        OrderSN sn = new OrderSN();
        sn.setSerialNumber(serialNumber);
        if(null != product.getEnterprise()){
        	sn.setBorrowerCustId(product.getEnterprise().getUserAccount().getAccountingInfo().getAcctCustId());
        }
        sn.setUserCustId(user.getAccountingInfo().getAcctCustId());

        return sn;
    }

    //    public void confirmPayment(Long userId, Long orderId) {
//        Assert.notNull(userId, "userId should not be null");
//        Assert.notNull(orderId, "orderId should not be null");
//
//        if (log.isDebugEnabled()) {
//            log.debug("place order@ userId @" + userId + ", orderId@" + orderId);
//        }
//
//        PurchaseOrder order = orderRepository.findOne(orderId);
//
//        if (order == null) {
//            throw new ResourceNotFoundException(orderId);
//        }
//
//        if (userId.longValue() != order.getUser().getId()) {
//            throw new OrderOwnerMismatchedExceptin(userId, orderId);
//        }
//
//        checkProductStock(order);
//
//        rabbitTemplate.convertAndSend(MessagingConstants.EXCHANGE_PAYMENT,
//                MessagingConstants.ROUTING_PAYMENT,
//                new PaymentMessage(userId, orderId)
//        );
//
//    }
    private void checkIfProductIsOutOfStock(OrderForm orderForm) throws OutOfStockException {
        final Product product = productRepository.findOne(orderForm.getProductId());

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(product.getOnSaleDate())) {
            throw new ProductNotOnSaleException(orderForm.getProductId());
        }

        //int boughtCount = orderForm.getCount().divide(product.getUnitPrice()).intValue();
        //if (product.getType() != Product.Type.DEMAND) {
        if (orderForm.getCount() > product.getLeftCount()) {
            throw new OutOfStockException(orderForm.getProductId());
        }
        // }
    }

    public Page<OrderDetails> findOrders(OrderSearchCriteria criteria, Long userId, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("findOrders by @start @" + criteria + ", page@" + page);
        }

        Page<PurchaseOrder> orders = orderRepository.findAll(
                OrderSpecifications.searchOrders(
                        criteria.getStart(),
                        criteria.getEnd(),
                        StringUtils.hasText(criteria.getType()) ? Product.Type.valueOf(criteria.getType()) : null,
                        StringUtils.hasText(criteria.getStatus()) ? PurchaseOrder.Status.valueOf(criteria.getStatus()) : null,
                        StringUtils.hasText(criteria.getActive()) ? Boolean.valueOf(criteria.getActive()) : true,
                        userId),
                page);

        if (log.isDebugEnabled()) {
            log.debug("all orders @" + orders.getTotalElements());
        }

        return DTOUtils.mapPage(orders, OrderDetails.class);
    }

    public void updateOrder(Long id, OrderForm form) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OrderDetails findOrderBySerialNumber(String serialNumber) {
        Assert.notNull(serialNumber, "order serialNumber can not be null");

        if (log.isDebugEnabled()) {
            log.debug(" findOrderBySerialNumber @" + serialNumber);
        }

        PurchaseOrder order = orderRepository.findBySerialNumber(serialNumber);

        if (order == null) {
            throw new ResourceNotFoundException(serialNumber);
        }

        return DTOUtils.map(order, OrderDetails.class);
    }

    public OrderDetails findOrderById(Long id) {
        Assert.notNull(id, "order id can not be null");

        if (log.isDebugEnabled()) {
            log.debug(" findOrderById @" + id);
        }

        PurchaseOrder order = orderRepository.findOne(id);

        if (order == null) {
            throw new ResourceNotFoundException(id);
        }

        OrderDetails orderDetails = DTOUtils.map(order, OrderDetails.class);
        orderDetails.setBorrowerCustId(order.getProduct().getEnterprise().getUserAccount().getAccountingInfo().getAcctCustId());
        return orderDetails;

    }

    public void deactivateOrder(Long id) {
        Assert.notNull(id, "order id can not be null");
        orderRepository.updateActiveStatus(id, false);
    }

    public void activateOrder(Long id) {
        Assert.notNull(id, "order id can not be null");
        orderRepository.updateActiveStatus(id, true);
    }

    public Page<OrderDetails> findOrdersByUserId(Long userId, Pageable page) {
        Assert.notNull(userId, "userId can not be null");
        Page<PurchaseOrder> orders = orderRepository.findByUserId(userId, page);
        return DTOUtils.mapPage(orders, OrderDetails.class);
    }

    public void cancelOrderById(Long id) {
        Assert.notNull(id, "order id can not be null");
        PurchaseOrder order = orderRepository.findOne(id);

        if (order == null) {
            throw new ResourceNotFoundException(id);
        }

        if (order.getStatus() != PurchaseOrder.Status.PENDING_PAYMENT
                && order.getStatus() != PurchaseOrder.Status.PAYMENT_FAILED) {
            throw new OrderCanNotBeCancelledException(id);
        }

        orderRepository.updateStatus(id, Status.CANCELED);
    }
    
    public void cancelOrderById(Long id, UserAccount user) {
		Assert.notNull(id, "order id can not be null");
		PurchaseOrder order = orderRepository.findOne(id);
		if (order == null) {
			throw new ResourceNotFoundException(id);
		}

		if (order.getUser().getId().equals(user.getId())
				&& order.getStatus() != PurchaseOrder.Status.PENDING_PAYMENT
				&& order.getStatus() != PurchaseOrder.Status.PAYMENT_FAILED) {
			throw new OrderCanNotBeCancelledException(id);
		}

		orderRepository.updateStatus(id, Status.CANCELED);
	}

	public Page<OrderDetails> findPaidOrdersByProductId(Long productId,
			Pageable page) {
		Assert.notNull(productId, "Product id can not be null");

		if (log.isDebugEnabled()) {
			log.debug("find active Orders by productId@" + productId);
		}
		Page<PurchaseOrder> orders = orderRepository.findPaidOrders(productId,
				page);

        return DTOUtils.mapPage(orders, OrderDetails.class);
    }

    public void requestRepaymentByOrderId(Long id) {
        Assert.notNull(id, "order id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("reuqest repayment by order Id @" + id);
        }

        PurchaseOrder order = orderRepository.findOne(id);

        if (order == null) {
            throw new ResourceNotFoundException(id);
        }


        if ((order.getStatus() != Status.REPAYMENT_FAILED)) {

            if (order.getProduct().getType() == Product.Type.DEMAND) {

                if (order.getStatus() != Status.PAID
                        && order.getStatus() != Status.INTEREST_ACCRUED) {
                    throw new RepaymentNotAllowedException();
                }

            } else {
                throw new RepaymentNotAllowedException();
            }

        }


        order.setStatus(Status.IN_REPAYMENT);

        orderRepository.save(order);

        gatewayService.sendRepaymentRequest(order);
    }

    public void preparePaymentByOrderId(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("prepare payment by order id @" + id);
        }

        PurchaseOrder ord = orderRepository.findOne(id);

        if (ord == null) {
            throw new ResourceNotFoundException("order is not found by id");
        }

        if (ord.getProduct().getStatus() == Product.Status.SOLD_OUT) {
            throw new SoldOutException(ord.getProduct().getId());
        } else if (ord.getAmount().divide(ord.getProduct().getUnitPrice()).intValue() > ord.getProduct().getLeftCount()) {
            throw new OutOfStockException(ord.getProduct().getId());
        }
    }

}
