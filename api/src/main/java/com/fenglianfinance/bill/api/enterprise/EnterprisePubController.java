package com.fenglianfinance.bill.api.enterprise;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.domain.support.EnterpriseCriteria;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.EnterpriseDetails;
import com.fenglianfinance.bill.service.EnterpriseService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_ENTERPRISE)
public class EnterprisePubController {

    private static final Logger log = LoggerFactory.getLogger(EnterprisePubController.class);

    @Inject
    private EnterpriseService enterpriseService;

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EnterpriseDetails> getEnterprise(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get enterprise data by id @" + id);
        }

        EnterpriseDetails enterprise = enterpriseService.findEnterpriseById(id);

        return new ResponseEntity<>(enterprise, HttpStatus.OK);
    }

    @RequestMapping(value = { "/{q}" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EnterpriseDetails> getEnterpriseByName(@PathVariable("q") String q) {

        if (log.isDebugEnabled()) {
            log.debug("get enterprise data by name @" + q);
        }

        EnterpriseDetails enterprise = enterpriseService.findEnterpriseByName(q);

        return new ResponseEntity<>(enterprise, HttpStatus.OK);
    }
    
//    @RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<Page<EnterpriseDetails>> getEnterpriseMenuByName(@RequestBody EnterpriseCriteria criteria,
//            @PageableDefault(page = 0, size = 100, sort = { "createdDate" }, direction = Direction.DESC) Pageable page) {
//
//        if (log.isDebugEnabled()) {
//            log.debug("get enterprise data by name @" + criteria.getName());
//        }
//
//        Page<EnterpriseDetails> enterprises = enterpriseService.findEnterpriseByName(criteria, page);
//
//        return new ResponseEntity<>(enterprises, HttpStatus.OK);
//    }
}
