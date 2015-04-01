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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fenglianfinance.bill.domain.BackLogItem;
import com.fenglianfinance.bill.domain.BackLogItemSearchCriteria;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BackLogAmountDetails;
import com.fenglianfinance.bill.model.BackLogAmountTotalDetails;
import com.fenglianfinance.bill.model.BackLogItemBatchForm;
import com.fenglianfinance.bill.model.BackLogItemDetails;
import com.fenglianfinance.bill.model.BackLogItemForm;
import com.fenglianfinance.bill.service.BackLogService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_BACKLOG)
public class BackLogController {

    private static final Logger log = LoggerFactory.getLogger(BackLogController.class);

    @Inject
    private BackLogService backLogService;

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createBackLogItem(@RequestBody BackLogItemForm form,
            UriComponentsBuilder uriComponentsBuilder) {
        if (log.isDebugEnabled()) {
            log.debug("save backLogItem data @ {}", form);
        }

        BackLogItemDetails saved = backLogService.saveBackLogItem(form);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_BACKLOG + "/{id}")
                .buildAndExpand(saved.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST, params = {"action=ADDBATCH"})
    @ResponseBody
    @Deprecated
    public ResponseEntity<Void> createBackLogItemBatch(@RequestBody BackLogItemBatchForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save backLogItem data @ {}", form);
        }
        backLogService.saveBackLogItemBatch(form);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    @ResponseBody
    public Page<BackLogItemDetails> allBackLogItem(@RequestBody BackLogItemSearchCriteria criteria, //
            @PageableDefault(value = 10) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("fetch by BackLogItemSearchCriteria @ {} Pageable @ {} ", criteria, page);
        }

        Page<BackLogItemDetails> backLogItems = backLogService.filterBackLogItemByCriteria(criteria, page);

        if (log.isDebugEnabled()) {
            log.debug("count of users @" + backLogItems.getTotalElements());
        }

        return backLogItems;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST, params = {"action=Unassigned"})
    @ResponseBody
    public Page<BackLogItemDetails> allUnassignedBackLogItem(@RequestBody BackLogItemSearchCriteria criteria, //
            @PageableDefault(value = 10) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("fetch by BackLogItemSearchCriteria @ {} Pageable @ {} ", criteria, page);
        }
        criteria.setStatus(BackLogItem.Status.UNASSIGNED.toString());
        criteria.setActive("true");
        Page<BackLogItemDetails> backLogItems = backLogService.filterBackLogItemByCriteria(criteria, page);

        if (log.isDebugEnabled()) {
            log.debug("count of users @" + backLogItems.getTotalElements());
        }

        return backLogItems;
    }

    @RequestMapping(value = {"/recount"}, method = RequestMethod.GET)
    @ResponseBody
    public BackLogAmountDetails recount() {

        BackLogAmountDetails backLogItems = backLogService.recountBackLogAmount();

        if (log.isDebugEnabled()) {
            log.debug("count of backlogAmount @" + backLogItems);
        }

        return backLogItems;
    }

    @RequestMapping(value = {"/getStatistics"}, method = RequestMethod.GET)
    @ResponseBody
    public BackLogAmountTotalDetails getStatistics() {

        BackLogAmountTotalDetails backLogItems = backLogService.getStatistics();

        if (log.isDebugEnabled()) {
            log.debug("rdies  of backlogAmount @" + backLogItems);
        }

        return backLogItems;
    }

}
