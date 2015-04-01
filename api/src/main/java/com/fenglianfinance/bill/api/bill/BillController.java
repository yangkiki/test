package com.fenglianfinance.bill.api.bill;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fenglianfinance.bill.domain.Bill;
import com.fenglianfinance.bill.domain.BillSearchCriteria;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BillDetails;
import com.fenglianfinance.bill.model.BillForm;
import com.fenglianfinance.bill.model.BooleanValue;
import com.fenglianfinance.bill.model.OrderDetails;
import com.fenglianfinance.bill.service.BackLogService;
import com.fenglianfinance.bill.service.BillService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_BILLES)
public class BillController {

    private static final Logger log = LoggerFactory.getLogger(BillController.class);

    @Inject
    private BillService billService;

    @Inject
    private BackLogService backLogService;

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createBill(@RequestBody BillForm form, UriComponentsBuilder uriComponentsBuilder) {
        if (log.isDebugEnabled()) {
            log.debug("save bill data @ {}", form);
        }

        BillDetails saved = billService.saveBill(form);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_BILLES + "/{id}")
                .buildAndExpand(saved.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    @ResponseBody
    public Page<BillDetails> allBills(@RequestBody BillSearchCriteria criteria,//
            @PageableDefault(value = 10) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("fetch all bills ...@ criteria : {} page: {}" ,criteria,page);
        }
        Page<BillDetails> bills = billService.findBills(criteria, page);

        if (log.isDebugEnabled()) {
            log.debug("count of bills @" + bills.getTotalElements());
            log.debug(" bills @ {}", bills.getContent());
        }

        return bills;
    }
    
    @RequestMapping(value = {""}, method = RequestMethod.GET,params = {"action=OPERATE"})
    @ResponseBody
    @Deprecated
    public Page<BillDetails> allBillsNotInBackLogItem(@RequestParam("q") String q, @PageableDefault(value = 10) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("fetch all users...@ q:" + q + ", page:" + page);
        }
        Page<BillDetails> bills = billService.findBillsNotInBackLogItem(q, page);

        if (log.isDebugEnabled()) {
            log.debug("count of users @" + bills.getTotalElements());
            log.debug(" users @ {}", bills.getContent());
        }

        return bills;
    }
    
    

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> updateBill(@PathVariable("id") Long id, @RequestBody BillForm form) {
        if (log.isDebugEnabled()) {
            log.debug("udpate bill @" + id + " data @" + form);
        }
        billService.updateBill(id, form);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}",}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BillDetails> getBill(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get bill data by id @ {}", id);
        }

        BillDetails bill = billService.findBillById(id);

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @RequestMapping(value = { "/{id}"}, method = RequestMethod.PUT,
            params = {"action=APPROVED"})
    @ResponseBody
    public ResponseEntity<Void> updateBillStatusApproved(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("udpate bill @" + id);
        }
        billService.updateBillStatus(id, Bill.Status.APPROVED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT,
            params = {"action=DEPRECATED"})
    @ResponseBody
    public ResponseEntity<Void> updatebillstatusDeprecated(@PathVariable("id") Long id,@RequestParam("rejectionCause") String r) {
        if (log.isDebugEnabled()) {
            log.debug("udpate bill @" + id);
        }
        
        billService.deprecatedBill(id, Bill.Status.DEPRECATED,r);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET,
            params = {"action=CHECKBILL"})
    @ResponseBody
    public ResponseEntity<Boolean> checkBill(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("check  bill id {} @" , id);
        }

        boolean flag = backLogService.checkBackLogItemExistTheBill(id);

        return new ResponseEntity<>(flag, HttpStatus.OK);
    }
    
//    @RequestMapping(value = {""}, method = RequestMethod.GET,
//            params = {"action=CHECKSERIALNUMBER"})
//    @ResponseBody
//    public ResponseEntity<Boolean> checkBillSerialNumber(@RequestParam("serialNumber") String serialNumber) {
//        if (log.isDebugEnabled()) {
//            log.debug("check  bill serialNumber  {} @" , serialNumber);
//        }
//        boolean flag = billService.findBillBySerialNumber(serialNumber);
//
//        return new ResponseEntity<>(Boolean.valueOf(true), HttpStatus.OK);
//    }
    
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT, params = {"action=ACTIVATE"})
    @ResponseBody
    public ResponseEntity<Void> activateBill(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("activate user @" + id + "");
        }
        billService.activateBill(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT, params = {"action=DEACTIVATE"})
    @ResponseBody
    public ResponseEntity<Void> deactivateBill(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("deactivate user @" + id + "");
        }
        billService.deactivateBill(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = {"/{sn}"}, method = RequestMethod.GET, params = "action=CHECK_EXISTENCE")
    @ResponseBody
    public ResponseEntity<BooleanValue> checkBillExistenceBySerialNumber(@PathVariable("sn") String serialNumber) {

        if (log.isDebugEnabled()) {
            log.debug("check order existence by serial number @" + serialNumber);
        }

        BillDetails bill = billService.findBillBySerialNumber(serialNumber);

        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/{sn}"}, method = RequestMethod.GET, params = "by=SN")
    @ResponseBody
    public ResponseEntity<BillDetails> getBillBySerialNumber(@PathVariable("sn") String serialNumber) {

        if (log.isDebugEnabled()) {
            log.debug("get order details by serialNumber @" + serialNumber);
        }

        BillDetails bill = billService.findBillBySerialNumber(serialNumber);

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
    
    

}
