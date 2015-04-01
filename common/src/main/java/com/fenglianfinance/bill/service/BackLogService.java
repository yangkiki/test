/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fenglianfinance.bill.DTOUtils;
import com.fenglianfinance.bill.domain.BackLog;
import com.fenglianfinance.bill.domain.BackLogAmount;
import com.fenglianfinance.bill.domain.BackLogAmountTotal;
import com.fenglianfinance.bill.domain.BackLogItem;
import com.fenglianfinance.bill.domain.BackLogItemSearchCriteria;
import com.fenglianfinance.bill.domain.Bill;
import com.fenglianfinance.bill.domain.SimpleBackLogAmount;
import com.fenglianfinance.bill.domain.SimpleOrderAmount;
import com.fenglianfinance.bill.exception.BillExistedException;
import com.fenglianfinance.bill.exception.ResourceNotFoundException;
import com.fenglianfinance.bill.model.BackLogAmountDetails;
import com.fenglianfinance.bill.model.BackLogAmountTotalDetails;
import com.fenglianfinance.bill.model.BackLogDetails;
import com.fenglianfinance.bill.model.BackLogForm;
import com.fenglianfinance.bill.model.BackLogItemBatchForm;
import com.fenglianfinance.bill.model.BackLogItemDetails;
import com.fenglianfinance.bill.model.BackLogItemForm;
import com.fenglianfinance.bill.repository.BackLogItemRepository;
import com.fenglianfinance.bill.repository.BackLogRepository;
import com.fenglianfinance.bill.repository.BillRepository;
import com.fenglianfinance.bill.repository.BillSpecifications;
import com.fenglianfinance.bill.repository.OrderRepository;

/**
 *
 * @author gao
 */
@Service
@Transactional
public class BackLogService {

    public final static Logger log = LoggerFactory.getLogger(BackLogService.class);

    private static final String BACKLOGTOTAL_SN_CACHE = "BL:SN:BACKLOGTOTAL";

    @Inject
    private BackLogRepository backLogRepository;

    @Inject
    private BackLogItemRepository backLogItemRepository;

    @Inject
    private BillRepository billRepository;

    @Inject
    private RedisTemplate<Object, Object> redisTemplate;

    @Inject
    private OrderRepository orderRepository;

    public BackLogDetails saveBackLog(BackLogForm form) {
        Assert.notNull(form, " @@ BackLogForm is null");

        if (log.isDebugEnabled()) {
            log.debug("saving BackLogForm@ {}", form);
        }

        BackLog backlog = DTOUtils.map(form, BackLog.class);

        BackLog saved = backLogRepository.save(backlog);

        if (log.isDebugEnabled()) {
            log.debug("saved BackLogForm @ {}", saved);
        }

        return DTOUtils.map(saved, BackLogDetails.class);
    }

    public BackLogItemDetails saveBackLogItem(BackLogItemForm form) {
        Assert.notNull(form, " @@ BackLogItemForm is null");

        if (log.isDebugEnabled()) {
            log.debug("saving BackLogItemForm@ {}", form);
        }

        BackLogItem checkBackLogItem = backLogItemRepository.checkBackLogItemExistTheBill(form.getBillId());

        if (checkBackLogItem != null) {
            throw new BillExistedException(form.getBillId());
        }

        BackLogItem backLogItem = DTOUtils.map(form, BackLogItem.class);

        Bill bill = billRepository.findOne(form.getBillId());

        if (null == bill) {
            throw new ResourceNotFoundException(" @@ Bill can't find by BillId :" + form.getBillId());
        }

        backLogItem.setBill(bill);

        BackLog backLog = null;

        if (null == form.getBackLogId()) {
            backLog = new BackLog();
            backLog = backLogRepository.save(backLog);
        } else {

            backLog = backLogRepository.findOne(form.getBackLogId());

            if (null == backLog) {

                backLog = new BackLog();

                backLog = backLogRepository.save(backLog);

                // throw new ResourceNotFoundException(" @@ BackLog can't find by BackLogId :" +
                // form.getBackLogId());
            }
        }

        backLogItem.setBackLog(backLog);

        Long usance = ChronoUnit.DAYS.between(backLogItem.getCompletedDate(), bill.getExpirationDate());

        Double temp = (1 + Double.valueOf(backLogItem.getAnnualInterestRate()) / 100 * usance / 360);

        if (log.isDebugEnabled()) {
            log.debug(" temp value  @ {}", temp);
        }

        BigDecimal financingAmount = bill.getDenomination().divide(new BigDecimal(temp), BigDecimal.ROUND_HALF_UP);

        financingAmount = financingAmount.setScale(2, BigDecimal.ROUND_HALF_UP);

        backLogItem.setFinancingAmount(financingAmount);

        backLogItem.setValid(BackLogItem.Valid.VALID);

        BackLogAmountTotal backLogAmountTotalRedis = getAmountFromRedis();

        setAmountToRedis(backLogAmountTotalRedis, backLogItem);

        BackLogItem saved = backLogItemRepository.save(backLogItem);

        if (log.isDebugEnabled()) {
            log.debug("saved BackLogItemForm  @ {}", saved);
        }

        return DTOUtils.map(saved, BackLogItemDetails.class);
    }

    private void setAmountToRedis(BackLogAmountTotal backLogAmountTotalRedis, BackLogItem backLogItem) {

        BigDecimal totalBillAmount = backLogAmountTotalRedis.getTotalBillAmount();
        //BigDecimal totalFinancingAmount = backLogAmountTotalRedis.getTotalFinancingAmount();

        HashMap<String, BackLogAmount> backLogAmountMap = backLogAmountTotalRedis.getBackLogAmountMap();

        BackLogAmount currentBacklogAmount = backLogAmountMap.get(backLogItem.getType().toString());

        if (currentBacklogAmount == null) {
            currentBacklogAmount = new BackLogAmount();
        }

        totalBillAmount = totalBillAmount.add(backLogItem.getBill().getDenomination());
        //totalFinancingAmount = totalFinancingAmount.add(backLogItem.getFinancingAmount());

        currentBacklogAmount.setBillAmount(currentBacklogAmount.getBillAmount().add(
                backLogItem.getBill().getDenomination()));
        //currentBacklogAmount.setFinancingAmount(currentBacklogAmount.getFinancingAmount().add(backLogItem.getFinancingAmount()));
        currentBacklogAmount.setType(backLogItem.getType());

        backLogAmountMap.put(backLogItem.getType().toString(), currentBacklogAmount);

        backLogAmountTotalRedis.setTotalBillAmount(totalBillAmount);
        //backLogAmountTotalRedis.setTotalFinancingAmount(totalFinancingAmount);
        backLogAmountTotalRedis.setBackLogAmountMap(backLogAmountMap);

        redisTemplate.opsForValue().set(BACKLOGTOTAL_SN_CACHE, backLogAmountTotalRedis);
    }

    private BackLogAmountTotal getAmountFromRedis() {
        BackLogAmountTotal backLogAmountTotalRedis =
                (BackLogAmountTotal) redisTemplate.opsForValue().get(BACKLOGTOTAL_SN_CACHE);

        if (backLogAmountTotalRedis == null) {
            backLogAmountTotalRedis = new BackLogAmountTotal();
        }

        return backLogAmountTotalRedis;
    }

    public BackLogAmountDetails recountBackLogAmount() {

        BackLogAmountDetails details = new BackLogAmountDetails();

        List<SimpleBackLogAmount> listSum = backLogItemRepository.sumFinancingAmountByType();

        List<SimpleOrderAmount> orderSum = orderRepository.sumOrderAmountByType();

        BigDecimal demandAmount = BigDecimal.ZERO;
        BigDecimal fixedAmount = BigDecimal.ZERO;
        BigDecimal singleAmount = BigDecimal.ZERO;

        for (SimpleBackLogAmount simpleBackLogAmount : listSum) {
            if (log.isDebugEnabled()) {
                log.debug(" BackLog Amount count @@@@@@@ {} ", simpleBackLogAmount);
            }

            if (simpleBackLogAmount.getType().equals(BackLogItem.Type.DEMAND)) {
                demandAmount = simpleBackLogAmount.getAmount();
            } else if (simpleBackLogAmount.getType().equals(BackLogItem.Type.FIXED)) {
                fixedAmount = simpleBackLogAmount.getAmount();
            } else if (simpleBackLogAmount.getType().equals(BackLogItem.Type.SINGLE)) {
                singleAmount = simpleBackLogAmount.getAmount();
            }
        }

        for (SimpleOrderAmount simpleOrderAmount : orderSum) {
            if (log.isDebugEnabled()) {
                log.debug(" order amount count @@@@@@@ {} ", simpleOrderAmount);
            }

            if (simpleOrderAmount.getConvertType().equals(BackLogItem.Type.DEMAND.toString())) {
                demandAmount = demandAmount.subtract(simpleOrderAmount.getAmount());
            } else if (simpleOrderAmount.getConvertType().equals(BackLogItem.Type.FIXED.toString())) {
                fixedAmount = fixedAmount.subtract(simpleOrderAmount.getAmount());
            } else if (simpleOrderAmount.getConvertType().equals(BackLogItem.Type.SINGLE.toString())) {
                singleAmount = singleAmount.subtract(simpleOrderAmount.getAmount());
            }

        }

        // setAmountToRedis(BackLogItem.Type.DEMAND.toString(), demandAmount);
        // setAmountToRedis(BackLogItem.Type.FIXED.toString(), fixedAmount);
        // setAmountToRedis(BackLogItem.Type.SINGLE.toString(), singleAmount);

        return details;

    }

    public BackLogAmountTotalDetails getStatistics() {

        BackLogAmountTotal details = getAmountFromRedis();

        return DTOUtils.map(details, BackLogAmountTotalDetails.class);

    }

    public void saveBackLogItemBatch(BackLogItemBatchForm form) {
        Assert.notNull(form, " @@ BackLogItemForm is null");
        Assert.notNull(form.getBillIds(), " @@ BillIds  is null");

        if (log.isDebugEnabled()) {
            log.debug("saving BackLogItemForm@ {}", form);
        }
        List<Long> billIds = form.getBillIds();

        BackLogItemForm backForm = new BackLogItemForm();
        backForm.setBackLogId(form.getBackLogId());
        backForm.setType(form.getType());

        for (Long billId : billIds) {
            backForm.setBillId(billId);
            saveBackLogItem(backForm);
        }

    }

    public boolean checkBackLogItemExistTheBill(Long billId) {

        Assert.notNull(billId, " @@ billId is null");

        if (log.isDebugEnabled()) {
            log.debug("check Bill Id @ {}", billId);
        }

        boolean flag = false;

        BackLogItem backLogItem = backLogItemRepository.checkBackLogItemExistTheBill(billId);

        if (backLogItem != null) {
            flag = true;
        }

        return flag;
    }

    public Page<BackLogItemDetails> filterBackLogItemByCriteria(BackLogItemSearchCriteria criteria, Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("findBills by BackLogItemSearchCriteria @ {}", criteria);
        }

        Page<BackLogItem> backLogItems =
                backLogItemRepository.findAll(BillSpecifications.filterBackLogItemByCriteria(criteria), page);

        if (log.isDebugEnabled()) {
            log.debug("total elements@" + backLogItems.getTotalElements());
        }

        return DTOUtils.mapPage(backLogItems, BackLogItemDetails.class);
    }

    public void updateBackLogItemStatus(Long id, BackLogItem.Status status) {
        if (log.isDebugEnabled()) {
            log.debug(" update BackLogItem status by id @ {}", id);
        }

        backLogItemRepository.updateStatus(id, status);

    }

    public BackLogItem findBackLogItemById(Long id) {
        Assert.notNull(id, " BackLogItem id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find BackLogItem by id @ {}", id);
        }
        BackLogItem backlogitem = backLogItemRepository.findOne(id);

        if (backlogitem == null) {
            throw new ResourceNotFoundException(id);
        }
        return backlogitem;
    }
}
