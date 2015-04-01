package com.fenglianfinance.bill.api.bank;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BankDetails;
import com.fenglianfinance.bill.model.BankForm;
import com.fenglianfinance.bill.service.BankService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT)
public class BankMgtController {

    private static final Logger log = LoggerFactory.getLogger(BankMgtController.class);

    @Inject
    private BankService bankService;

    @RequestMapping(value = {"/banks"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<BankDetails>> getAllBanks(
            @RequestParam("q") String q,
            @RequestParam("active") boolean status,
            @PageableDefault(page = 0, size = 10, sort = {"createdDate"}, direction = Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("call getAllBanks");
        }

        Page<BankDetails> result = bankService.findBankDetailsByKeyword(q, status, page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/banks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createBank(@RequestBody BankForm form, UriComponentsBuilder builder) {
        if (log.isDebugEnabled()) {
            log.debug("create a new Bank@" + form);
        }

        BankDetails saved = bankService.saveBank(form);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/banks/{id}").buildAndExpand(saved.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/banks/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BankDetails> getBank(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("get banksinfo by id @" + id);
        }

        BankDetails bank = bankService.findBankById(id);

        if (log.isDebugEnabled()) {
            log.debug("get Bank @" + bank);
        }

        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @RequestMapping(value = "/banks/{id}", method = RequestMethod.PUT, params = "!action")
    @ResponseBody
    public ResponseEntity<Void> updateBank(@PathVariable("id") Long id, @RequestBody BankForm form) {
        if (log.isDebugEnabled()) {
            log.debug("update a new Bank@" + form);
        }

        bankService.updateBank(id, form);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/banks/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deactivateBank(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete Bank by id @" + id);
        }

        bankService.updateBankStatus(id, false);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/banks/{id}", method = RequestMethod.PUT, params = "action=ACTIVATE")
    @ResponseBody
    public ResponseEntity<Void> activateBank(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("UNLOCK Bank by id @" + id);
        }

        bankService.updateBankStatus(id, true);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
