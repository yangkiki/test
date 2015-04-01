package com.moxian.ng.api;

import com.moxian.ng.model.ApiErrors;
import com.moxian.ng.model.ResponseMessage;
import com.moxian.ng.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import javax.inject.Inject;
import org.springframework.context.MessageSource;

/**
 * Called when an exception occurs during request processing. Transforms the exception message into JSON format.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
    
    @Inject 
    private MessageSource messageSource;

    //    @ExceptionHandler(value = { AuthenticationException.class })
    //    @ResponseBody
    //    public ResponseEntity<AlertMessage> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
    //        if (log.isDebugEnabled()) {
    //            log.debug("handling authentication exception...");
    //        }
    //        return new ResponseEntity<>(new AlertMessage(AlertMessage.Type.danger, ex.getMessage()),
    //                HttpStatus.UNAUTHORIZED);
    //    }
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest req) {

    }

    @ExceptionHandler(value = { InvalidRequestException.class })
    public ResponseEntity<ResponseMessage> handleInvalidRequestException(InvalidRequestException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling InvalidRequestException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.INVALID_REQUEST,
                messageSource.getMessage( ApiErrors.INVALID_REQUEST, new String[]{}, null));

        BindingResult result = ex.getErrors();

        List<FieldError> fieldErrors = result.getFieldErrors();

        if (!fieldErrors.isEmpty()) {
            for (FieldError e : fieldErrors) {
                alert.addError(e.getField(), e.getCode(), e.getDefaultMessage());
            }
        }

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { UsernameExistedException.class })
    public ResponseEntity<ResponseMessage> handleUsernameExistedException(UsernameExistedException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling UsernameExistedException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.USERNAME_EXISTED, 
                messageSource.getMessage( ApiErrors.USERNAME_EXISTED, new String[]{}, null));

        alert.addError("username", "exist", "username is exist");

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(value = { MobileNumberExistedException.class })
    public ResponseEntity<ResponseMessage> handleMobileNumberExistedException(MobileNumberExistedException ex, WebRequest req) {
    	if (log.isDebugEnabled()) {
            log.debug("handling handleMobileNumberExistedException...");
        }
    	ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.MOBILENUMBER_EXISTED, 
                messageSource.getMessage( ApiErrors.MOBILENUMBER_EXISTED, new String[]{}, null));
    	
    	alert.addError("mobileNumber", "exist", "mobileNumber is exist");
    	
    	return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(value = { MobileNumberNotBelongToUserException.class })
    public ResponseEntity<ResponseMessage> handleMobileNumberNotBelongToUserException(MobileNumberNotBelongToUserException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling MobileNumberNotBelongToUserException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.MOBILE_NUMBER_NOT_BELONG_TO_USER, 
                messageSource.getMessage( ApiErrors.MOBILE_NUMBER_NOT_BELONG_TO_USER, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { ProductNameExistedException.class })
    public ResponseEntity<ResponseMessage> handleProductNameExistedException(ProductNameExistedException ex,
            WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling ProductNameExistedException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.PRODUCTNAME_EXISTED, 
                messageSource.getMessage( ApiErrors.PRODUCTNAME_EXISTED, new String[]{}, null));

        alert.addError("productname", "exist", "productname is exist");

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    
    @ExceptionHandler(value = { EnterpriseExistedException.class })
    public ResponseEntity<ResponseMessage> handleEnterpriseExistedException(EnterpriseExistedException ex,
            WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling EnterpriseExistedException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.ENTERPRISENAME_EXISTED, 
                messageSource.getMessage( ApiErrors.ENTERPRISENAME_EXISTED, new String[]{}, null));

        alert.addError("enterprisename", "exist", "enterprisename is exist");

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<ResponseMessage> handleSysIllegalArgumentException(IllegalArgumentException ex,
            WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling SysIllegalArgumentException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.ILLEGALARGUMENT, 
                messageSource.getMessage( ApiErrors.ILLEGALARGUMENT, new String[]{}, null));

        alert.addError("illegalArgument", "illegalArgument", "illegalArgument");

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { ProductPromotedExistedException.class })
    public ResponseEntity<ResponseMessage> handleProductPromotedExistedException(ProductPromotedExistedException ex,
            WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling ProductPromotedExistedException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.PRODUCTPROMOTED_EXISTED, 
                messageSource.getMessage( ApiErrors.PRODUCTPROMOTED_EXISTED, new String[]{}, null));

        alert.addError("Promoted", "exist", "Promoted is exist");

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { CaptchaMismatchedException.class })
    public ResponseEntity<ResponseMessage> handleCaptchaMismatchedException(CaptchaMismatchedException ex,
            WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling CaptchaMismatchedException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger,
                ApiErrors.CAPTCHA_MISMATCHED, 
                messageSource.getMessage( ApiErrors.CAPTCHA_MISMATCHED, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { PendingOrderExistedException.class })
    public ResponseEntity<ResponseMessage> handlePendingOrderExistedException(PendingOrderExistedException ex,
            WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling PendingOrderExistedException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger,
                ApiErrors.PENDING_ORDER_EXISTED,
                messageSource.getMessage( ApiErrors.PENDING_ORDER_EXISTED, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    
    @ExceptionHandler(value = { RepaymentNotAllowedException.class })
    public ResponseEntity<ResponseMessage> handleRepaymentNotAllowedException(RepaymentNotAllowedException ex,
            WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling RepaymentNotAllowedException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.REPAYMENT_NOT_ALLOWED,
                messageSource.getMessage( ApiErrors.REPAYMENT_NOT_ALLOWED, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { BankNameException.class })
    public ResponseEntity<ResponseMessage> handleBankNameException(BankNameException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling BankNameException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.BANK_NAME_EXISTED,
                messageSource.getMessage( ApiErrors.BANK_NAME_EXISTED, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { OutOfStockException.class })
      public ResponseEntity<ResponseMessage> handleOutOfStockException(OutOfStockException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling OutOfStockException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.OUT_OF_STOCK,
                messageSource.getMessage( ApiErrors.OUT_OF_STOCK, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
      
       @ExceptionHandler(value = { SoldOutException.class })
      public ResponseEntity<ResponseMessage> handleSoldOutException(SoldOutException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling SoldOutException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.SOLD_OUT,
                messageSource.getMessage( ApiErrors.SOLD_OUT, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { ProductNotOnSaleException.class })
    public ResponseEntity<ResponseMessage> handleProductNotOnSaleException(ProductNotOnSaleException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling ProductNotOnSaleException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.NOT_ON_SALE,
                messageSource.getMessage( ApiErrors.NOT_ON_SALE, new String[]{}, null));

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { BillSerialNumberException.class })
    public ResponseEntity<ResponseMessage> handleBillSerialNumberExceptionn(BillSerialNumberException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling BillSerialNumberException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger, 
                ApiErrors.BILLSERIALNUMBER_EXISTED,
                messageSource.getMessage( ApiErrors.BILLSERIALNUMBER_EXISTED, new String[]{}, null));

        alert.addError("billSerialnumber", "exist", "billSerialnumber is exist");

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = { UsernameNotFoundException.class })
    public ResponseEntity<ResponseMessage> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling UsernameNotFoundException...");
        }

        ResponseMessage alert = new ResponseMessage(ResponseMessage.Type.danger, ApiErrors.USERNAME_NOT_FOUND, messageSource.getMessage( ApiErrors.USERNAME_NOT_FOUND, new String[]{}, null));
        alert.addError("userName", "notFound", "UsernameNotFoundException...");
        
        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(value = { AuthenticationException.class })
    public ResponseEntity<ResponseMessage> handleAuthenticationException(AuthenticationException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling AuthenticationException...");
        }

        ResponseMessage alert = new ResponseMessage(ResponseMessage.Type.danger, ApiErrors.AUTHENTICATION_ERR, messageSource.getMessage( ApiErrors.AUTHENTICATION_ERR, new String[]{}, null));
        alert.addError("UserNameOrPasswordError", "failure", "AuthenticationException...");
        
        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
