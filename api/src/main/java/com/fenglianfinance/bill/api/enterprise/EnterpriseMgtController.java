package com.fenglianfinance.bill.api.enterprise;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

import com.fenglianfinance.bill.domain.Enterprise;
import com.fenglianfinance.bill.domain.support.EnterpriseCriteria;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BankCardInfoDetails;
import com.fenglianfinance.bill.model.EnterpriseDetails;
import com.fenglianfinance.bill.model.EnterpriseForm;
import com.fenglianfinance.bill.model.TransactionLogDetails;
import com.fenglianfinance.bill.service.EnterpriseService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_ENTERPRISE)
public class EnterpriseMgtController {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseMgtController.class);

    @Inject
    private EnterpriseService enterpriseService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<EnterpriseDetails>> getAllEnterprises(@RequestParam("q") String q,
            @RequestParam("active") boolean status, @RequestParam("verifystatus") String vs,
            @PageableDefault(page = 0, size = 10, sort = { "createdDate" }, direction = Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("call getAllEnterprises");
        }

        Page<EnterpriseDetails> result = enterpriseService.findEnterpriseDetailsByKeyword(q, status, vs, page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = { "/menu"},method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<EnterpriseDetails>> getEnterprisesMenu(@PageableDefault(page = 0, size = 10, sort = { "createdDate" }, direction = Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("call getEnterprisesMenu");
        }

        Page<EnterpriseDetails> result = enterpriseService.findEnterpriseDetailsByKeyword(page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/selectmenu" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<EnterpriseDetails>> getEnterpriseMenuByName(@RequestParam("name") String name,
            @PageableDefault(page = 0, size = 100, sort = { "createdDate" }, direction = Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get enterprise data by name @" + name);
        }
        Page<EnterpriseDetails> enterprises = enterpriseService.findEnterpriseByName(name,page);

        return new ResponseEntity<>(enterprises, HttpStatus.OK);
    }

    @RequestMapping(value = { "/search" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Page<EnterpriseDetails>> searchEnterprise(@RequestBody EnterpriseCriteria criteria,
            @PageableDefault(page = 0, size = 100, sort = { "createdDate" }, direction = Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("get all enterprise by  search@" + criteria);
        }

        Page<EnterpriseDetails> result = enterpriseService.findEnterprises(criteria, page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createEnterprise(@RequestBody EnterpriseForm form,
            UriComponentsBuilder uriComponentsBuilder) {

        if (log.isDebugEnabled()) {
            log.debug("save enterprise data @" + form);
        }

        form.setVerifyState(Enterprise.VerifyStatus.PENDING.toString());

        EnterpriseDetails enterprise = enterpriseService.saveEnterprise(form);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder
                .path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_ENTERPRISE + "/{id}")
                .buildAndExpand(enterprise.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> updateEnterprise(@PathVariable("id") Long id, @RequestBody EnterpriseForm form) {

        if (log.isDebugEnabled()) {
            log.debug("EnterpriseForm @" + form);
        }

        enterpriseService.updateEnterprise(id, form);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteEnterprise(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("soft delete enterprise @" + id + ", deactivate enterprise not delete it");
        }

        enterpriseService.deactivateEnterprise(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = { "/normal"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> enableEnterprise(@RequestParam("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("soft enable enterprise @" + id + ", enterprise not enable it");
        }

        enterpriseService.enableEnterprise(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT, params = { "action=APPROVE" })
    @ResponseBody
    public ResponseEntity<Void> updatePassEnterprise(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("id @" + id);
        }

        enterpriseService.updatePassEnterprise(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT, params = { "action=REJECT" })
    @ResponseBody
    public ResponseEntity<Void> updateRejectEnterprise(@PathVariable("id") Long id, @RequestBody EnterpriseForm form) {

        if (log.isDebugEnabled()) {
            log.debug("id @" + id + ",verifyCause @" + form.getVerifyCause());
        }

        enterpriseService.updateRejectEnterprise(id, form.getVerifyCause());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT, params = { "action=DISCARD" })
    @ResponseBody
    public ResponseEntity<Void> updateDiscardEnterprise(@PathVariable("id") Long id, @RequestBody EnterpriseForm form) {

        if (log.isDebugEnabled()) {
            log.debug("id @" + id + ",verifyCause @" + form.getVerifyCause());
        }

        enterpriseService.updateDiscardEnterprise(id, form.getVerifyCause());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT, params = { "action=ACCOUNT" })
    @ResponseBody
    public ResponseEntity<Void> createAccount(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("id @" + id);
        }

        //        enterpriseService.updateDiscardEnterprise(id, form.getVerifyCause());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EnterpriseDetails> getEnterprise(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get enterprise data by id @" + id);
        }

        EnterpriseDetails enterprise = enterpriseService.findEnterpriseById(id);

        return new ResponseEntity<>(enterprise, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = {"/{id}/cards"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BankCardInfoDetails>> boundCards(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("accounting info @" + id);
        }

        List<BankCardInfoDetails> cards = enterpriseService.findBoundCardsById(id);

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}/logs"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<TransactionLogDetails>> transactionLogs(
            @PathVariable("id") Long id,
            @PageableDefault(value = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page
            ) {

        if (log.isDebugEnabled()) {
            log.debug("get transaction logs of@" + id);
        }

        Page<TransactionLogDetails> logs = enterpriseService.findTransactionLogsById(id, page);

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

}
