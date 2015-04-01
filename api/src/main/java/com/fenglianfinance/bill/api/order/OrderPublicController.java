package com.fenglianfinance.bill.api.order;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.FuzzyInfoUtils;
import com.fenglianfinance.bill.exception.ResourceNotFoundException;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.OrderDetails;
import com.fenglianfinance.bill.service.OrderService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_API_ORDER)
public class OrderPublicController {

    private static final Logger log = LoggerFactory.getLogger(OrderPublicController.class);

    @Inject
    private OrderService orderService;

    @RequestMapping(value = {"/{sn}"}, method = RequestMethod.GET, params = "by=SN")
    @ResponseBody
    public ResponseEntity<OrderDetails> getOrderBySerialNumber(@PathVariable("sn") String serialNumber) {

        if (log.isDebugEnabled()) {
            log.debug("get order details by serialNumber @" + serialNumber);
        }

        OrderDetails order = orderService.findOrderBySerialNumber(serialNumber);

        if (!order.isActive()) {
            throw new ResourceNotFoundException(serialNumber);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
	@RequestMapping(value = { "/{productId}" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Page<OrderDetails>> getOrdersByProductId(
			@PathVariable("productId") Long productId,
			@PageableDefault(page = 0, size = 10, sort = "placedDate", direction = Sort.Direction.DESC) Pageable page) {

		if (log.isDebugEnabled()) {
			log.debug("get order details by productId @" + productId);
		}

		Page<OrderDetails> orders = orderService
				.findPaidOrdersByProductId(productId, page);
		
		for (OrderDetails order : orders.getContent()) {
	    	order.getUser().setName(FuzzyInfoUtils.fuzzyUserName(order.getUser().getName()));	//
		}

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}
