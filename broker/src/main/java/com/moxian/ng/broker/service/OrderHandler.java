/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.broker.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.inject.Inject;

import com.moxian.ng.model.Constants;
import com.moxian.ng.model.OrderMessage;
import com.moxian.ng.payment.model.PaymentMessage;
import com.moxian.ng.payment.model.RepaymentMessage;
import com.moxian.ng.repository.OrderRepository;
import com.moxian.ng.repository.ProductRepository;
import com.moxian.ng.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.moxian.ng.domain.BackLogAmount;
import com.moxian.ng.domain.BackLogAmountTotal;
import com.moxian.ng.domain.BackLogItem;
import com.moxian.ng.domain.Product;
import com.moxian.ng.domain.PurchaseOrder;
import com.moxian.ng.domain.TransactionLog;
import com.moxian.ng.domain.TransactionType;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.repository.TransactionLogRepository;

/**
 *
 * @author hantsy
 */
@Service
@Transactional
public class OrderHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderHandler.class);

    private static final String BACKLOGTOTAL_SN_CACHE = "BL:SN:BACKLOGTOTAL";

    @Inject
    private UserRepository userRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderSerialNumberGenerator generator;

    @Inject
    private TransactionLogRepository transactionLogRepository;

    @Inject
    private RedisTemplate<Object, Object> redisTemplate;

    public void handleMessage(OrderMessage message) {
         Assert.notNull(message, "message can not be null");
        Assert.notNull(message.getUserId(), "message user id can not be null");
        Assert.notNull(message.getProductId(), "message product id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("order processing...@" + message);
        }

        PurchaseOrder order = new PurchaseOrder();

        order.setStatus(PurchaseOrder.Status.PENDING_PAYMENT);
        order.setAmount(message.getAmount());
        // order.setSerialNumber(generator.nextSerialNumber());
        order.setSerialNumber(message.getOrderSN());

        final Product product = productRepository.findOne(message.getProductId());

        if (log.isDebugEnabled()) {
            log.debug("order of product@" + product);
        }

        // caculate the interest of order, should be completed when the order is checked out.
		if (product.getType() == Product.Type.HOT
				|| product.getType() == Product.Type.NEWBIE
				|| product.getType() == Product.Type.FIXED
				|| product.getType() == Product.Type.WITHFUNDING_DAY
				|| product.getType() == Product.Type.WITHFUNDING_MONTH) {

            LocalDate today = LocalDate.now();
            LocalDate onsaleDate = product.getOnSaleDate().toLocalDate();

            order.setAccruedStartDate(LocalDate.now());
            order.setAccruedEndDate(product.getCompletedDate());

            long days = Duration.between(onsaleDate.atStartOfDay(),today.atStartOfDay()).toDays();
            int effetiveDays = product.getDuration() - (int) days;

            order.setAccruedInterestAmount(order.getAmount()
                    .multiply(new BigDecimal((float) product.getAnnualPercentageRate() / 100))
                    .multiply(new BigDecimal((float) effetiveDays / Constants.YEAR_DAYS)).setScale(
                    2, BigDecimal.ROUND_HALF_UP));

		} else {
			order.setAccruedStartDate(LocalDate.now());
		}

        order.setProduct(product);

        UserAccount user = userRepository.findOne(message.getUserId());
        user.setTotalOrderAmount(user.getTotalOrderAmount().add(order.getAmount()));
        user = userRepository.save(user);

        order.setUser(user);
        order.setPlacedDate(LocalDateTime.now());
        order = orderRepository.save(order);

        if (log.isDebugEnabled()) {
            log.debug("order saved@" + order);
        }
    }

    public void handleMessage(PaymentMessage message) {
        Assert.notNull(message, "message can not be null");
        if (log.isDebugEnabled()) {
            log.debug("payment processing...@" + message);
        }

        TransactionLog tlog = transactionLogRepository.findBySerialNumber(message.getOrdId());

        if (tlog != null) {
            if (log.isDebugEnabled()) {
                log.debug("order transaction log existed...@");
            }

            return;
        }

        PurchaseOrder order = orderRepository.findBySerialNumber(message.getOrderSN());
        
        if (order == null) {
        	log.warn("order is not exists! @" + message.getOrderSN());
        	return;
        }

        UserAccount fromUser = userRepository.findByAccountingInfoAcctCustId(message.getFromCustId());

        UserAccount toUser = userRepository.findByAccountingInfoAcctCustId(message.getToCustId());

         Product product = order.getProduct();

        if (log.isDebugEnabled()) {
            log.debug("order of product@" + product);
        }


        // TODO decrease the sold out counter in product
        int orderCopies = order.getAmount().divide(product.getUnitPrice()).intValue();
        product.setSoldCount(product.getSoldCount() + orderCopies);
        
        int productAllCopies=product.getTotalAmount().divide(product.getUnitPrice()).intValue();
        
        if(product.getSoldCount()==productAllCopies){
            product.setStatus(Product.Status.SOLD_OUT);
        }

        productRepository.save(product);

        // TODO update user purchased order
        fromUser.setTotalPaymentAmount(fromUser.getTotalPaymentAmount().add(order.getAmount()));
        userRepository.save(fromUser);

        order.setStatus(PurchaseOrder.Status.PAID);
        order.setPaidDate(LocalDateTime.now());
        order = orderRepository.save(order);

        updateRedisBacklogAmount(convertProductTypeToBacklogItemType(product.getType()), order.getAmount());

        // write the transaction log now.  
        tlog = new TransactionLog();
        tlog.setAmount(message.getAmount());
        tlog.setFee(message.getFee());
        tlog.setFrom(fromUser);
        tlog.setTo(toUser);
        tlog.setOrder(order);
        tlog.setSerialNumber(message.getOrdId());
        tlog.setTransactedDate(message.getOrdDate());
        tlog.setType(TransactionType.PAYMENT);

        transactionLogRepository.save(tlog);

        if (log.isDebugEnabled()) {
            log.debug("order saved@" + order);
        }
    }

    public void handleMessage(RepaymentMessage message) {
        Assert.notNull(message, "message can not be null");
        if (log.isDebugEnabled()) {
            log.debug("repayment processing...@" + message);
        }

        TransactionLog tlog = transactionLogRepository.findBySerialNumber(message.getOrdId());

        if (tlog != null) {
            if (log.isDebugEnabled()) {
                log.debug("repayment transaction log existed...@");
            }

            return;
        }

        PurchaseOrder order = orderRepository.findBySerialNumber(message.getOrderSN());

        UserAccount fromUser = userRepository.findByAccountingInfoAcctCustId(message.getFromCustId());

        UserAccount toUser = userRepository.findByAccountingInfoAcctCustId(message.getToCustId());

        // final Product product = order.getProduct();
        //
        // if (log.isDebugEnabled()) {
        // log.debug("order of product@" + product);
        // }
        // caculate the interest of order, should be completed when the order is checked out.
        userRepository.save(toUser);

        PurchaseOrder.Status orderStatus=null;
        TransactionLog.Status tranStatus=null;
        if("000".equals(message.getRespCode())){
            orderStatus=PurchaseOrder.Status.REPAYMENT_COMPLETED;
            tranStatus=TransactionLog.Status.SUCCESS;

            updateRedisCompletedOrder(order.getAmount());

        }else{
            orderStatus=PurchaseOrder.Status.REPAYMENT_FAILED;
            tranStatus=TransactionLog.Status.FAILED;
        }

        order.setStatus(orderStatus);
        order.setCompletedDate(LocalDateTime.now());
        order = orderRepository.save(order);



        // write the transaction log now.
        tlog = new TransactionLog();
        tlog.setAmount(message.getAmount());
        tlog.setFee(message.getFee());
        tlog.setFrom(fromUser);
        tlog.setTo(toUser);
        tlog.setOrder(order);
        tlog.setSerialNumber(message.getOrdId());
        tlog.setTransactedDate(message.getOrdDate());
        tlog.setType(TransactionType.REPAYMENT);
        tlog.setStatus(tranStatus);

        transactionLogRepository.save(tlog);

        if (log.isDebugEnabled()) {
            log.debug("repayment for order @" + message.getOrderSN() + " is done!");
        }
    }

    private void setAmountToRedis(BackLogAmountTotal backLogAmountTotalRedis, BigDecimal amount, BackLogItem.Type type) {

        HashMap<String, BackLogAmount> backLogAmountMap = backLogAmountTotalRedis.getBackLogAmountMap();

        BackLogAmount currentBacklogAmount = backLogAmountMap.get(type.toString());

        BigDecimal orderAmount = backLogAmountTotalRedis.getOrderAmount();

        if (currentBacklogAmount == null) {
            currentBacklogAmount = new BackLogAmount();
        }

        currentBacklogAmount.setFinancingAmount(currentBacklogAmount.getFinancingAmount().subtract(amount));
        currentBacklogAmount.setType(type);

        backLogAmountMap.put(type.toString(), currentBacklogAmount);
        backLogAmountTotalRedis.setBackLogAmountMap(backLogAmountMap);

        backLogAmountTotalRedis.setOrderAmount(orderAmount.add(amount));

        redisTemplate.opsForValue().set(BACKLOGTOTAL_SN_CACHE, backLogAmountTotalRedis);
    }

    private BackLogAmountTotal getAmountFromRedis() {
        BackLogAmountTotal backLogAmountTotalRedis
                = (BackLogAmountTotal) redisTemplate.opsForValue().get(BACKLOGTOTAL_SN_CACHE);

        if (backLogAmountTotalRedis == null) {
            backLogAmountTotalRedis = new BackLogAmountTotal();
        }

        return backLogAmountTotalRedis;
    }

    public void updateRedisBacklogAmount(BackLogItem.Type type, BigDecimal amount) {

        BackLogAmountTotal backLogAmountTotalRedis = getAmountFromRedis();

        setAmountToRedis(backLogAmountTotalRedis, amount, type);

    }

    public void updateRedisCompletedOrder(BigDecimal amount) {
        BackLogAmountTotal backLogAmountTotalRedis = getAmountFromRedis();
        BigDecimal completedOrderAmount = backLogAmountTotalRedis.getCompletedOrderAmount();

        backLogAmountTotalRedis.setCompletedOrderAmount(completedOrderAmount.add(amount));

        redisTemplate.opsForValue().set(BACKLOGTOTAL_SN_CACHE, backLogAmountTotalRedis);
    }

    private BackLogItem.Type convertProductTypeToBacklogItemType(Product.Type type) {

        BackLogItem.Type itemType = null;

        switch (type) {
            case NEWBIE:
                itemType = BackLogItem.Type.SINGLE;
                break;
            case HOT:
                itemType = BackLogItem.Type.SINGLE;
                break;
            case DEMAND:
                itemType = BackLogItem.Type.DEMAND;
                break;
            case FIXED:
                itemType = BackLogItem.Type.FIXED;
                break;
			case WITHFUNDING_DAY:
                itemType = BackLogItem.Type.FIXED;
                break;
            case WITHFUNDING_MONTH:
            	itemType = BackLogItem.Type.FIXED;
            	break;
        }

        return itemType;

    }

}
