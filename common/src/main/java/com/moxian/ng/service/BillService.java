/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.moxian.ng.DTOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.moxian.ng.domain.Bank;
import com.moxian.ng.domain.Bill;
import com.moxian.ng.domain.BillSearchCriteria;
import com.moxian.ng.domain.Enterprise;
import com.moxian.ng.exception.BillSerialNumberException;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.BillDetails;
import com.moxian.ng.model.BillForm;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.repository.BackLogItemRepository;
import com.moxian.ng.repository.BankRepository;
import com.moxian.ng.repository.BillRepository;
import com.moxian.ng.repository.BillSpecifications;
import com.moxian.ng.repository.EnterpriseRepository;
import com.moxian.ng.repository.UserRepository;

/**
 *
 * @author gao
 */
@Service
@Transactional
public class BillService {

    public final static Logger log = LoggerFactory.getLogger(BillService.class);

    @Inject
    private BillRepository billRepository;

    @Inject
    private BankRepository bankRepository;

    @Inject
    private EnterpriseRepository enterpriseRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private BackLogItemRepository backLogItemRepository;

    public BillDetails saveBill(BillForm form) {
        Assert.notNull(form, " @@ BillForm is null");
        Assert.notNull(form.getAcceptingBankFK(), " @@ form.AcceptingBank is null");
        Assert.notNull(form.getEnterpriseFK(), " @@  form.Enterprise is null");
        Assert.notNull(form.getPayeeBankFK(), " @@ form.BillTicketClerk is null");

        Assert.notNull(form.getAcceptingBankFK().getId(), " @@ form.AcceptingBank.id is null");
        Assert.notNull(form.getEnterpriseFK().getId(), " @@  form.Enterprise.id is null");
        Assert.notNull(form.getPayeeBankFK().getId(), " @@ form.BillTicketClerk.id is null");

        if (log.isDebugEnabled()) {
            log.debug("saving Bill@" + form);
        }

        Bill billCheck = billRepository.findBySerialNumber(form.getSerialNumber());

        if (billCheck != null) {
            throw new BillSerialNumberException();
        }

        Bill bill = DTOUtils.map(form, Bill.class);

        Bank acceptingBank = bankRepository.findOne(form.getAcceptingBankFK().getId());

        if (null == acceptingBank) {
            throw new ResourceNotFoundException(" @@ bank can't find by bankId :" + form.getAcceptingBankFK().getId());
        }

        bill.setAcceptingBank(acceptingBank);

        Enterprise lastestEndorsementEnterprise = enterpriseRepository.findOne(form.getEnterpriseFK().getId());

        if (null == lastestEndorsementEnterprise) {
            throw new ResourceNotFoundException(" @@ Enterprise can't find by enterpriseId :"
                    + form.getEnterpriseFK().getId());
        }

        bill.setEnterprise(lastestEndorsementEnterprise);

        Bank payeeBank = bankRepository.findOne(form.getPayeeBankFK().getId());

        if (null == payeeBank) {
            throw new ResourceNotFoundException(" @@ bank can't find by bankId :" + form.getPayeeBankFK().getId());
        }

        bill.setPayeeBank(payeeBank);

        bill.setStatus(Bill.Status.PENDING);
        
        
        Long usance=ChronoUnit.DAYS.between( bill.getInvoiceDate(),bill.getExpirationDate());
        
        bill.setUsance(usance.intValue());
        
        BigDecimal invoiceInterestAmount=null;
        
        invoiceInterestAmount=bill.getDenomination().multiply(new BigDecimal(Double.valueOf(bill.getInvoiceRate())/100*usance/360)) ;
        
        invoiceInterestAmount=invoiceInterestAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        bill.setInvoiceInterestAmount(invoiceInterestAmount);

        Bill saved = billRepository.save(bill);

        if (log.isDebugEnabled()) {
            log.debug("saved Bill @" + saved);
        }

        return DTOUtils.map(saved, BillDetails.class);
    }

    public Page<BillDetails> findBills(BillSearchCriteria criteria, Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("findBills by criteria @ {} , page@ {} ", criteria, page);
        }

        Page<Bill> bills = billRepository.findAll(BillSpecifications.filterBillBySerialNumber(criteria), page);

        if (log.isDebugEnabled()) {
            log.debug("total elements@" + bills.getTotalElements());
        }

        return DTOUtils.mapPage(bills, BillDetails.class);
    }

    public void updateBill(Long id, BillForm form) {

        Assert.notNull(form, " @@ BillForm is null");
        Assert.notNull(form.getAcceptingBankFK(), " @@ form.AcceptingBank is null");
        Assert.notNull(form.getEnterpriseFK(), " @@  form.Enterprise is null");
        Assert.notNull(form.getPayeeBankFK(), " @@ form.BillTicketClerk is null");

        Assert.notNull(form.getAcceptingBankFK().getId(), " @@ form.AcceptingBank.id is null");
        Assert.notNull(form.getEnterpriseFK().getId(), " @@  form.Enterprise.id is null");
        Assert.notNull(form.getPayeeBankFK().getId(), " @@ form.BillTicketClerk.id is null");

        if (log.isDebugEnabled()) {
            log.debug("find bill by id @ {}", id);
        }

        Bill bill = billRepository.findOne(id);
        if (bill == null) {
            throw new ResourceNotFoundException(id);
        }
        
        if(bill.getStatus().equals(Bill.Status.DEPRECATED)){
            bill.setStatus(Bill.Status.PENDING);
        }
        
        if(!bill.getSerialNumber().equals(form.getSerialNumber())){
            
            Bill billCheck = billRepository.findBySerialNumber(form.getSerialNumber());
            if (billCheck != null) {
                throw new BillSerialNumberException();
            }
            
        }

        DTOUtils.mapTo(form, bill);

        Bank acceptingBank = bankRepository.findOne(form.getAcceptingBankFK().getId());

        if (null == acceptingBank) {
            throw new ResourceNotFoundException(" @@ bank can't find by bankId :" + form.getAcceptingBankFK().getId());
        }

        bill.setAcceptingBank(acceptingBank);

        Enterprise lastestEndorsementEnterprise = enterpriseRepository.findOne(form.getEnterpriseFK().getId());

        if (null == lastestEndorsementEnterprise) {
            throw new ResourceNotFoundException(" @@ Enterprise can't find by enterpriseId :"
                    + form.getEnterpriseFK().getId());
        }

        bill.setEnterprise(lastestEndorsementEnterprise);

        Bank payeeBank = bankRepository.findOne(form.getPayeeBankFK().getId());

        if (null == payeeBank) {
            throw new ResourceNotFoundException(" @@ bank can't find by bankId :" + form.getPayeeBankFK().getId());
        }

        bill.setPayeeBank(payeeBank);
        
        Long usance=ChronoUnit.DAYS.between( bill.getInvoiceDate(),bill.getExpirationDate());
        
        bill.setUsance(usance.intValue());
        
        BigDecimal invoiceInterestAmount=null;
        
        invoiceInterestAmount=bill.getDenomination().multiply(new BigDecimal(Double.valueOf(bill.getInvoiceRate())/100*usance/360)) ;
        
        invoiceInterestAmount=invoiceInterestAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        bill.setInvoiceInterestAmount(invoiceInterestAmount);

        Bill billSaved = billRepository.save(bill);

        if (log.isDebugEnabled()) {
            log.debug("updated bill @" + billSaved);
        }
    }

    public void deactivateBill(Long id) {
        Assert.notNull(id, "bill id can not be null");
        billRepository.updateActiveStatus(id, false);
    }

    public void activateBill(Long id) {
        Assert.notNull(id, "bill id can not be null");
        billRepository.updateActiveStatus(id, true);
    }

    public BillDetails findBillById(Long id) {
        Assert.notNull(id, "bill id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find user by id @ {}", id);
        }
        Bill bill = billRepository.findOne(id);

        if (bill == null) {
            throw new ResourceNotFoundException(id);
        }
        BillDetails billDetails= DTOUtils.map(bill, BillDetails.class);
        billDetails.setReviewTime(bill.getLastModifiedDate());
        if(null!=bill.getLastModifiedBy()){
            billDetails.setReviewer(DTOUtils.map(bill.getLastModifiedBy(), UserAccountDetails.class));
        }
         
         return billDetails; 
    }
    
    public void deprecatedBill(Long id,Bill.Status status ,String rejectionCause){
        Assert.notNull(id, "bill id can not be null");
        Assert.notNull(status, "bill status can not be null");
        if (log.isDebugEnabled()) {
            log.debug("update bill  status rejectionCause by id @ {}  status @ {}  rejectionCause {}", id, status,rejectionCause);
        }
        billRepository.updateBillStatusAndRejectionCause(id, status, rejectionCause);
    }

    public void updateBillStatus(Long id, Bill.Status status) {
        Assert.notNull(id, "bill id can not be null");
        Assert.notNull(status, "bill status can not be null");
        if (log.isDebugEnabled()) {
            log.debug("update user status by id @ {}  status @ {}", id, status);
        }
        billRepository.updateBillStatus(id, status);
    }

    @Deprecated
    public Page<BillDetails> findBillsNotInBackLogItem(String serialNumber, Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("findBills by serialNumber@" + serialNumber + ", page@" + page);
        }

        List<Long> billIds = new ArrayList<>();

        billIds = backLogItemRepository.findBillNotInBackLogItem();

        Page<Bill> bills =
                billRepository.findAll(BillSpecifications.filterBillByNotExistInBacklogItem(serialNumber, billIds),
                        page);

        if (log.isDebugEnabled()) {
            log.debug("total elements@" + bills.getTotalElements());
        }

        return DTOUtils.mapPage(bills, BillDetails.class);
    }

    public BillDetails findBillBySerialNumber(String serialNumber) {

        if (log.isDebugEnabled()) {
            log.debug("check by serialNumber@ {}", serialNumber);
        }

        Bill bill = billRepository.findBySerialNumber(serialNumber);

        if (bill == null) {
            throw new ResourceNotFoundException(serialNumber);
        }
        return DTOUtils.map(bill, BillDetails.class);
    }

}
