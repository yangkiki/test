/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.service;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.PurchaseOrder;
import com.moxian.ng.domain.TransactionLog;
import com.moxian.ng.domain.TransactionReconciliation;
import com.moxian.ng.domain.TransactionType;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.gateway.common.GatewayService;
import com.moxian.ng.messaging.api.MessagingConstants;
import com.moxian.ng.model.TransactionLogDetails;
import com.moxian.ng.model.TransactionLogFixRequest;
import com.moxian.ng.model.TransactionReconciliationDetails;
import com.moxian.ng.repository.OrderRepository;
import com.moxian.ng.repository.TransactionLogRepository;
import com.moxian.ng.repository.TransactionLogSpecifications;
import com.moxian.ng.repository.TransactionReconciliationRepository;
import com.moxian.ng.repository.TransactionReconciliationSpecifications;
import com.moxian.ng.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.inject.Inject;

import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hantsy
 */
@Service
@Transactional
public class AcctService {

    private static final Logger log = LoggerFactory.getLogger(AcctService.class);

    @Inject
    private TransactionLogRepository tlogRepository;

    @Inject
    private OrderRepository orderRepository;
    
    @Inject
    private GatewayService gatewayService;
    
    @Inject
    private UserRepository userRepository;

    @Inject
    private TransactionReconciliationRepository reconciliationRepository;

    @Inject
    private RabbitTemplate rabbitTemplate;

    public Page<TransactionLogDetails> findLogsByCriteria(
            String status,//
            String type,//
            LocalDate checkDate,//
            Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("call findLogsByCriteria ");
        }

        Page<TransactionLog> logs = tlogRepository.findAll(TransactionLogSpecifications.filterByTypeAndDateRange(status, type, checkDate), page);

        return DTOUtils.mapPage(logs, TransactionLogDetails.class);
    }

    public void executeManualFix(TransactionLogFixRequest req) {
        rabbitTemplate.convertAndSend(MessagingConstants.EXCHANGE_JOB, MessagingConstants.ROUTING_JOB, req);
    }

    public Page<TransactionLogDetails> findTransferLogsByCriteria(String fromAcctId, TransactionType type, LocalDate beginDate, LocalDate endDate, Pageable page) {

        Page<TransactionLog> logs = tlogRepository.findAll(
                TransactionLogSpecifications.filterTransferLogsByTypeAndDateRange(fromAcctId, type, beginDate, endDate), page);

        return DTOUtils.mapPage(logs, TransactionLogDetails.class);
    }

    public Page<TransactionReconciliationDetails> findTransReconciliaitonsByCriteria(String type, String status, LocalDate beginDate, LocalDate endDate, Pageable page) {
        Page<TransactionReconciliation> logs = reconciliationRepository.findAll(
                TransactionReconciliationSpecifications.filterByCriteria(type, status, beginDate, endDate), page);

        return DTOUtils.mapPage(logs, TransactionReconciliationDetails.class);
    }

    public void fixTransactionLogStatus(Long id, Long userId) {
        Assert.notNull(id, "transaction log id can not be null");
        TransactionLog tranlog = tlogRepository.findOne(id);

        if (tranlog == null) {
            throw new ResourceNotFoundException(id);
        }

        if (tranlog.getQueryResult() != TransactionLog.QueryResult.STATUS_MATCHED) {
            tranlog.setStatus(tranlog.getAcctStatus());

            if (tranlog.getStatus() == TransactionLog.Status.FAILED && tranlog.getType() == TransactionType.REPAYMENT) {
                PurchaseOrder order = tranlog.getOrder();
                order.setStatus(PurchaseOrder.Status.IN_REPAYMENT);

                orderRepository.save(order);

                gatewayService.sendRepaymentRequest(order);
            }
            
            tranlog.setQueryResult(TransactionLog.QueryResult.STATUS_MATCHED);
            tranlog.setFixedBy(userRepository.findOne(userId));
            tranlog.setFixedDate(LocalDateTime.now());
            tlogRepository.save(tranlog);
        }
    }

}
