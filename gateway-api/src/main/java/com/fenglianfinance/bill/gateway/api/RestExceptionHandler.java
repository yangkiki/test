package com.fenglianfinance.bill.gateway.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fenglianfinance.bill.model.ApiErrors;
import com.fenglianfinance.bill.model.ResponseMessage;

/**
 * Called when an exception occurs during request processing. Transforms the
 * exception message into JSON format.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest req) {

    }

    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<ResponseMessage> handleInvalidRequestException(InvalidRequestException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling InvalidRequestException...");
        }

        ResponseMessage alert = new ResponseMessage(ResponseMessage.Type.danger, ApiErrors.INVALID_REQUEST);

        BindingResult result = ex.getErrors();

        List<FieldError> fieldErrors = result.getFieldErrors();

        if (!fieldErrors.isEmpty()) {
            for (FieldError e : fieldErrors) {
                alert.addError(e.getField(), e.getCode(), e.getDefaultMessage());
            }
        }

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {PaymentGatewayException.class})
    public ResponseEntity<ResponseMessage> handlePaymentGatewayException(PaymentGatewayException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling PaymentGatewayException...");
        }

        ResponseMessage alert = new ResponseMessage(ResponseMessage.Type.danger, ApiErrors.PAYMENT_GATEWAY_EXCEPTION);

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {OutOfStockException.class})
    public ResponseEntity<ResponseMessage> handleOutOfStockException(OutOfStockException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling OutOfStockException...");
        }

        ResponseMessage alert = new ResponseMessage(ResponseMessage.Type.danger, ApiErrors.OUT_OF_STOCK);

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {OrderOwnerMismatchedExceptin.class})
    public ResponseEntity<ResponseMessage> handleOrderOwnerMismatchedExceptin(OrderOwnerMismatchedExceptin ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling OrderOwnerMismatchedExceptin...");
        }

        ResponseMessage alert = new ResponseMessage(ResponseMessage.Type.danger, ApiErrors.ORDER_OWNER_MISMATCHED);

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
