package com.moxian.ng.service;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.support.EnterpriseCriteria;
import com.moxian.ng.exception.EnterpriseExistedException;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.exception.UsernameExistedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.moxian.ng.domain.BankCardInfo;
import com.moxian.ng.domain.Enterprise;
import com.moxian.ng.domain.TransactionLog;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.model.BankCardInfoDetails;
import com.moxian.ng.model.EnterpriseDetails;
import com.moxian.ng.model.EnterpriseForm;
import com.moxian.ng.model.TransactionLogDetails;
import com.moxian.ng.repository.BankCardInfoRepository;
import com.moxian.ng.repository.EnterpriseRepository;
import com.moxian.ng.repository.EnterpriseSpecifications;
import com.moxian.ng.repository.TransactionLogRepository;
import com.moxian.ng.repository.TransactionLogSpecifications;
import com.moxian.ng.repository.UserRepository;

@Service
public class EnterpriseService {

    private final static Logger log = LoggerFactory.getLogger(EnterpriseService.class);

    @Inject
    private EnterpriseRepository enterpriseRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private BankCardInfoRepository cardRepository;

    @Inject
    private TransactionLogRepository transactionLogRepository;

    @Transactional
    public EnterpriseDetails saveEnterprise(EnterpriseForm form) {
        if (log.isDebugEnabled()) {
            log.debug("saving enterprise@" + form);
        }

        if (enterpriseRepository.findByName(form.getName()) != null) {
            throw new EnterpriseExistedException(form.getName());
        }

        Enterprise enterprise = DTOUtils.map(form, Enterprise.class);
        UserAccount userAccount = DTOUtils.map(form.getUserAccount(), UserAccount.class);

        userAccount.setType(UserAccount.Type.ENTERPRISE);
        userAccount.setName(enterprise.getName());
        userAccount.setActive(true);

        if (userRepository.findByUsername(form.getUserAccount().getUsername()) != null) {
            throw new UsernameExistedException(form.getUserAccount().getUsername());
        }
        userRepository.save(userAccount);
        enterprise.setUserAccount(userAccount);

        Enterprise saved = enterpriseRepository.save(enterprise);

        if (log.isDebugEnabled()) {
            log.debug("saved enterprise @" + saved);
        }

        return DTOUtils.map(saved, EnterpriseDetails.class);
    }

    public EnterpriseDetails findEnterpriseById(Long id) {
        Assert.notNull(id, "enterprise id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find enterprise by id @" + id);
        }

        Enterprise enterprise = enterpriseRepository.findOne(id);

        if (enterprise == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(enterprise, EnterpriseDetails.class);
    }

    public EnterpriseDetails findEnterpriseByName(String name) {
        Assert.notNull(name, "enterprise name can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find enterprise by name @" + name);
        }

        Enterprise enterprise = enterpriseRepository.findByName(name);

        if (enterprise == null) {
            throw new ResourceNotFoundException(name);
        }

        return DTOUtils.map(enterprise, EnterpriseDetails.class);
    }

    @Transactional
    public void updateEnterprise(Long id, EnterpriseForm form) {
        Assert.notNull(id, "enterprise id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find enterprise by id @" + id);
        }
        Enterprise enterprise = enterpriseRepository.findOne(id);

        Enterprise compareToenterprise = enterpriseRepository.findByName(form.getName());

        if (compareToenterprise != null && !compareToenterprise.getName().equals(enterprise.getName())) {
            throw new EnterpriseExistedException(form.getName());
        }

        if (enterprise == null) {
            throw new ResourceNotFoundException(id);
        }
        DTOUtils.mapTo(form, enterprise);

        Enterprise saved = enterpriseRepository.save(enterprise);

        if (log.isDebugEnabled()) {
            log.debug("updated Enterprise @" + saved);
        }
    }

    @Transactional
    public void deactivateEnterprise(Long id) {
        Assert.notNull(id, "enterprise id can not be null");
        enterpriseRepository.deactivate(id, false);
    }

    @Transactional
    public void enableEnterprise(Long id) {
        Assert.notNull(id, "enterprise id can not be null");
        enterpriseRepository.deactivate(id, true);
    }

    @Transactional
    public void updatePassEnterprise(Long id) {
        Assert.notNull(id, "enterprise id can not be null");

        enterpriseRepository.pass(id, Enterprise.VerifyStatus.APPROVED);

        if (log.isDebugEnabled()) {
            log.debug("updated Enterprise PASS@" + id);
        }
    }

    @Transactional
    public void updateRejectEnterprise(Long id, String verifyCause) {
        Assert.notNull(id, "enterprise id can not be null");
        Assert.notNull(id, "enterprise verifyCause can not be null");
        enterpriseRepository.reject(id, verifyCause, Enterprise.VerifyStatus.REJECTED);

        if (log.isDebugEnabled()) {
            log.debug("updated Enterprise REJECT@" + id);
        }

    }

    @Transactional
    public void updateDiscardEnterprise(Long id, String verifyCause) {
        Assert.notNull(id, "enterprise id can not be null");
        Assert.notNull(id, "enterprise verifyCause can not be null");
        enterpriseRepository.reject(id, verifyCause, Enterprise.VerifyStatus.DISCARD);

        if (log.isDebugEnabled()) {
            log.debug("updated Enterprise DISCARD@" + id);
        }

    }

    public void addPicture(Long id, String uploadId) {
        Assert.notNull(id, "Enterprise id can not be null");
        Assert.notNull(uploadId, "Enterprise id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("add picture@" + uploadId + " to hotel id@" + id);
        }

        Enterprise enterprise = enterpriseRepository.findOne(id);

        if (enterprise == null) {
            throw new ResourceNotFoundException(id);
        }

        enterprise.getPictures().add(uploadId);
        enterpriseRepository.save(enterprise);

    }

    public void setCoverPicture(Long id, String pictureId) {
        Assert.notNull(id, "enterprise id can not be null");
        Assert.notNull(pictureId, "picture id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("set picture@" + pictureId + " as cover picture of  enterpriseid@" + id);
        }

        Enterprise enterprise = enterpriseRepository.findOne(id);

        if (enterprise == null) {
            throw new ResourceNotFoundException(id);
        }

        enterpriseRepository.save(enterprise);

    }

    public Page<EnterpriseDetails> findEnterpriseDetailsByKeyword(String q, boolean status, String vs, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("search enterprise by name@" + q);
        }

        Page<Enterprise> enterprises = enterpriseRepository.findAll(
                EnterpriseSpecifications.findEnterprisesByName(q, status, vs), page);

        if (log.isDebugEnabled()) {
            log.debug("get enterprise size @" + enterprises.getTotalElements());
        }

        return DTOUtils.mapPage(enterprises, EnterpriseDetails.class);
    }

    public Page<EnterpriseDetails> findEnterpriseDetailsByKeyword(Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("search enterprise by name@");
        }

        Page<Enterprise> enterprises = enterpriseRepository.findAll(
                EnterpriseSpecifications.findEnterprisesByAccountingInfo(), page);

        if (log.isDebugEnabled()) {
            log.debug("get enterprise size @" + enterprises.getTotalElements());
        }

        return DTOUtils.mapPage(enterprises, EnterpriseDetails.class);
    }

    public Page<EnterpriseDetails> findEnterprises(EnterpriseCriteria criteria, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("findEnterprises by @start @" + criteria + ", page@" + page);
        }

        Page<Enterprise> enterprises = enterpriseRepository.findAll(
                EnterpriseSpecifications.searchEnterprises(criteria), page);

        if (log.isDebugEnabled()) {
            log.debug("all enterprises @" + enterprises.getTotalElements());
        }

        return DTOUtils.mapPage(enterprises, EnterpriseDetails.class);
    }

    public Page<EnterpriseDetails> findEnterpriseByName(String name, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("findEnterprises by @start @page@" + page);
        }

        Page<Enterprise> enterprises = enterpriseRepository.findAll(
                EnterpriseSpecifications.findEnterprises(name, "APPROVED", true), page);

        if (log.isDebugEnabled()) {
            log.debug("all enterprises @" + enterprises.getTotalElements());
        }

        return DTOUtils.mapPage(enterprises, EnterpriseDetails.class);
    }

    public List<BankCardInfoDetails> findBoundCardsById(Long id) {
        Assert.notNull(id, " enterprise id can't  be null ");

        if (log.isDebugEnabled()) {
            log.debug("fetch bank cards by enterprse id@" + id);
        }

        Enterprise enterprise = enterpriseRepository.findOne(id);

        if (enterprise == null) {
            throw new ResourceNotFoundException(id);
        }

        if (enterprise.getUserAccount() == null) {
            throw new ResourceNotFoundException("enterprise does not has a user account!");
        }

        List<BankCardInfo> cards = cardRepository.findByUserId(enterprise.getUserAccount().getId());

        if (cards != null && !cards.isEmpty()) {
            return DTOUtils.mapList(cards, BankCardInfoDetails.class);
        }

        return Collections.emptyList();
    }

    public Page<TransactionLogDetails> findTransactionLogsById(Long id, Pageable page) {
        Assert.notNull(id, "enterprise id id can't  be null ");

        if (log.isDebugEnabled()) {
            log.debug("fetch transaction logs by enterprise id@" + id);
        }

        Enterprise enterprise = enterpriseRepository.findOne(id);

        if (enterprise == null) {
            throw new ResourceNotFoundException(id);
        }

        if (enterprise.getUserAccount() == null) {
            throw new ResourceNotFoundException("enterprise does not has a user account!");
        }

        Page<TransactionLog> logs = transactionLogRepository.findAll(
                TransactionLogSpecifications.filterByCriteria(enterprise.getUserAccount().getId(), null), page);

        return DTOUtils.mapPage(logs, TransactionLogDetails.class);
    }
    
    /**
     * 查询所有有效的企业
     * 
     * @return
     * @author wangli@flf77.com
     * @date 2015年2月15日 下午6:04:28
     */
	public List<EnterpriseDetails> findEnterpriseDetails() {
		if (log.isDebugEnabled()) {
			log.debug("search enterprise by name@");
		}

		List<Enterprise> enterprises = enterpriseRepository
				.findByActiveIsTrue();

		if (log.isDebugEnabled()) {
			log.debug("get enterprise size @" + enterprises.size());
		}

		return DTOUtils.mapList(enterprises, EnterpriseDetails.class);
	}
}
