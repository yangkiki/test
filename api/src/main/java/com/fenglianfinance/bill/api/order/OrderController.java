package com.fenglianfinance.bill.api.order;

import com.fenglianfinance.bill.api.security.CurrentUser;
import com.fenglianfinance.bill.model.OrderSearchCriteria;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.exception.InvalidRequestException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.ApiErrors;
import com.fenglianfinance.bill.model.OrderDetails;
import com.fenglianfinance.bill.model.OrderForm;
import com.fenglianfinance.bill.model.OrderSN;
import com.fenglianfinance.bill.service.OrderService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = ApiConstants.URI_API + ApiConstants.URI_API_ORDER)
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderService orderService;

    @RequestMapping(value = {}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<OrderDetails>> getAllOrders(
            @RequestParam("type") String type,//
            @CurrentUser UserAccount user,//
            @PageableDefault(page = 0, size = 10, sort = {"placedDate"}, direction = Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("get all orders by criteria@" + type);
        }

        OrderSearchCriteria criteria = new OrderSearchCriteria();
        criteria.setActive("true");
        criteria.setType(type);

        Page<OrderDetails> result = orderService.findOrders(criteria, user.getId(), page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = {}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<OrderSN> createOrder(//
            @RequestBody OrderForm form, //
            @CurrentUser UserAccount user, //
            BindingResult errors,//
            UriComponentsBuilder uriComponentsBuilder) {

        if (log.isDebugEnabled()) {
            log.debug("placing order... @" + user + ", order @" + form);
        }

        if (errors.hasErrors()) {
            throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
        }

        OrderSN sn = orderService.placeOrder(user.getId(), form);
        return new ResponseEntity<>(sn, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{sn}"}, method = RequestMethod.GET, params = "by=SN")
    @ResponseBody
    public ResponseEntity<OrderDetails> getOrderBySerialNumber(@PathVariable("sn") String serialNumber) {

        if (log.isDebugEnabled()) {
            log.debug("get order data by serialNumber @" + serialNumber);
        }

        OrderDetails product = orderService.findOrderBySerialNumber(serialNumber);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, params = "!by", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<OrderDetails> getOrder(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get order data by id @" + id);
        }

        OrderDetails product = orderService.findOrderById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, params = "by=PREPARE_PAYMENT", method = RequestMethod.GET)
    public ResponseEntity<OrderDetails> preparePayment(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("request repayment by order id @" + id);
        }

        orderService.preparePaymentByOrderId(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, params = "action=REQUEST_REPAYMENT", method = RequestMethod.PUT)
    public ResponseEntity<OrderDetails> requestRepayment(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("request repayment by order id @" + id);
        }

        orderService.requestRepaymentByOrderId(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    public ResponseEntity<OrderDetails> cancelOrder(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("cancel order by id @" + id);
        }

        orderService.cancelOrderById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
