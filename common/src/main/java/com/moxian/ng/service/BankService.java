/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.service;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.Bank;
import com.moxian.ng.exception.BankNameException;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.BankDetails;
import com.moxian.ng.model.BankForm;
import com.moxian.ng.repository.BankRepository;
import com.moxian.ng.repository.BankSpecifications;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author hantsy
 */
@Service
@Transactional
public class BankService {

    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    @Inject
    private BankRepository bankRepository;

    public Page<BankDetails> findBankDetailsByKeyword(String q, boolean status, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("search banks by keyword@" + q + ", status @" + status);
        }

        Page<Bank> banks = bankRepository.findAll(BankSpecifications.filterBanksByKeywordAndStatus(q, status),
                page);

        if (log.isDebugEnabled()) {
            log.debug("get banks size @" + banks.getTotalElements());
        }

        return DTOUtils.mapPage(banks, BankDetails.class);
    }

    public BankDetails saveBank(BankForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save bank @" + form);
        }

        if (bankRepository.findByName(form.getName()) != null) {
            throw new BankNameException();
        }

        Bank bank = DTOUtils.map(form, Bank.class);

        Bank saved = bankRepository.save(bank);

        if (log.isDebugEnabled()) {
            log.debug("saved bank id is @" + saved.getId());
        }

        return DTOUtils.map(bank, BankDetails.class);

    }

    public void updateBank(Long id, BankForm form) {
        Assert.notNull(id, "bank id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("update bank @" + form);
        }
        final Bank existed = bankRepository.findByName(form.getName());

        if (existed != null && id.longValue() != existed.getId()) {
            throw new BankNameException();
        }

        Bank bank = bankRepository.findOne(id);
        DTOUtils.mapTo(form, bank);

        Bank saved = bankRepository.save(bank);

        if (log.isDebugEnabled()) {
            log.debug("updated bank@" + saved);
        }
    }

    public BankDetails findBankById(Long id) {
        Assert.notNull(id, "bank id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("find bank by id@" + id);
        }

        Bank bank = bankRepository.findOne(id);

        if (bank == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(bank, BankDetails.class);
    }

    public void deleteBank(Long id) {
        Assert.notNull(id, "bank id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("delete bank by id @" + id);
        }

        Bank bank = bankRepository.findOne(id);

        if (bank == null) {
            throw new ResourceNotFoundException(id);
        }

        bankRepository.delete(bank);
    }

    public void updateBankStatus(Long id, Boolean status) {
        bankRepository.updateStatus(id, status);
    }
}
