package com.fenglianfinance.bill.gateway.common;

import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.payment.model.AddBidInfoRequest;
import com.fenglianfinance.bill.payment.model.MerchantTransferQueryResult;
import com.fenglianfinance.bill.payment.model.MerchantTransferRequest;
import com.fenglianfinance.bill.payment.model.PaymentQueryResult;
import com.fenglianfinance.bill.payment.model.RechargeQueryResult;
import com.fenglianfinance.bill.payment.model.RepaymentRequest;
import com.fenglianfinance.bill.payment.model.TransactionLogResult;
import com.fenglianfinance.bill.payment.model.TransactionQueryRequest;
import com.fenglianfinance.bill.payment.model.WithdrawQueryResult;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fenglianfinance.bill.repository.OrderRepository;
import com.fenglianfinance.bill.repository.TransactionLogRepository;
import java.math.BigDecimal;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class GatewayTemplate implements GatewayOperations, EnvironmentAware {

    private static final Logger log = LoggerFactory
            .getLogger(GatewayTemplate.class);

    private static final String GW_API_URL = "gwApiUrl";

    private final RestTemplate restTemplate;

    private OrderRepository orderRepository;

    private TransactionLogRepository transactionLogRepository;

    public void setOrderRepository(OrderRepository orderRepository, TransactionLogRepository transactionLogRepository) {
        this.orderRepository = orderRepository;
        this.transactionLogRepository = transactionLogRepository;
    }

    private Environment env;

    public GatewayTemplate(OrderRepository orderRepository) {
        CloseableHttpClient httpClient = HttpClients.createMinimal();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        clientHttpRequestFactory.setReadTimeout(60000);
        clientHttpRequestFactory.setConnectTimeout(60000);
        this.restTemplate = new RestTemplate(clientHttpRequestFactory);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter(Charset
                .forName("UTF-8")));

        this.restTemplate.setMessageConverters(messageConverters);

        this.orderRepository = orderRepository;
    }

    @Override
    public void repay(RepaymentRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("send repayment request@" + request);
        }

        String url = this.env.getProperty(GW_API_URL) + "Repayment";

        if (log.isDebugEnabled()) {
            log.debug("repayment url @" + url);
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        //ordId
//ordDate
//outCustId
//subOrdId
//subOrdDate
//outAcctId
//transAmt
//fee
//inCustId
//inAcctId
//feeObjFlag
        params.add("ordId", request.getOrdId());
        params.add("ordDate", request.getOrdDate());
        params.add("outCustId", request.getOutCustId());
        // params.add("outAcctId", request.getOutAcctId());
        params.add("inCustId", request.getInCustId());
        // params.add("inAcctId", request.getInAcctId());
        params.add("transAmt", request.getTransAmt());
        params.add("fee", request.getFee());
        params.add("feeObjFlag", request.getFeeObjFlag());
        params.add("subOrdId", request.getSubOrdId());
        params.add("subOrdDate", request.getSubOrdDate());

        // params.add("sendDateTime", sdf.format(new Date()));
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        ResponseEntity<String> result = null;
        try {
            result = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(params, headers), String.class);
        } catch (RestClientException e) {
            log.error("rest client exception caught@" + e);
            PurchaseOrder order = orderRepository.findBySerialNumber(request.getSubOrdId());
            order.setStatus(PurchaseOrder.Status.REPAYMENT_FAILED);
            orderRepository.save(order);
        }

        if (log.isDebugEnabled()) {
            log.debug("response body of repayment request@" + result);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public Page<TransactionLogResult> query(TransactionQueryRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("send query request@" + request);
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("beginDate", request.getBeginDate());
        params.add("endDate", request.getEndDate());
        params.add("queryTransType", request.getQueryTransType());
        params.add("pageNum",request.getPageNum());
        params.add("pageSize",request.getPageSize());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON));
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));

        if ("LOANS".equals(request.getQueryTransType()) || "REPAYMENT".equals(request.getQueryTransType())) {
            return processPaymentQuery(params, headers);
        } else if ("RECHARGE".equals(request.getQueryTransType())) {
            return processRechargeQuery(params, headers);
        } else if ("WITHDRAW".equals(request.getQueryTransType())) {
            return processWithdrawQuery(params, headers);
        } else if ("MERCHANT_TRANSFER".equals(request.getQueryTransType())) {
            return processMerchantTransfer(params, headers);
        }

        return null;

    }

  @Override
  public void merchantTransfer(MerchantTransferRequest request) {

    {
      if (log.isDebugEnabled()) {
        log.debug("send MerchantTransferRequest request@ {}" , request);
      }

      String url = this.env.getProperty(GW_API_URL) + "Transfer";

      if (log.isDebugEnabled()) {
        log.debug(" MerchantTransferRequest url @" + url);
      }

      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

      params.add("ordId", request.getOrdId());
      params.add("outCustId", request.getOutCustId());
      params.add("outAcctId", request.getOutAcctId());
      params.add("transAmt", request.getTransAmt());
      params.add("inCustId", request.getInCustId());
      params.add("inAcctId", request.getInAcctId());


      HttpHeaders headers = new HttpHeaders();

      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
      headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
      ResponseEntity<String> result = null;
      try {
        result = restTemplate.exchange(url, HttpMethod.POST,
                                       new HttpEntity<>(params, headers), String.class);
      } catch (RestClientException e) {
        log.error("rest client exception caught@" + e);
      }

      if (log.isDebugEnabled()) {
        log.debug("response body of MerchantTransferRequest request@" + result);
      }
    }

  }

  @Override
  public void addBidInfo(AddBidInfoRequest request) {

    {

      {
        if (log.isDebugEnabled()) {
          log.debug("send AddBidInfoRequest request@ {}" , request);
        }

        String url = this.env.getProperty(GW_API_URL) + "AddBidInfo";

        if (log.isDebugEnabled()) {
          log.debug(" AddBidInfoRequest url @" + url);
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("proId", request.getProId());
        params.add("borrCustId", request.getBorrCustId());
        params.add("borrTotAmt", request.getBorrTotAmt());
        params.add("yearRate", request.getYearRate());
        params.add("retType", request.getRetType());
        params.add("bidStartDate", request.getBidStartDate());
        params.add("bidEndDate", request.getBidEndDate());
        params.add("retAmt", request.getRetAmt());
        params.add("retDate", request.getRetDate());
        params.add("guarCompId", request.getGuarCompId());
        params.add("guarAmt", request.getGuarAmt());
        params.add("proArea", request.getProArea());
        params.add("reqExt", request.getReqExt());
        params.add("redirectUrl", request.getRedirectUrl());


        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        ResponseEntity<String> result = null;
        try {
          result = restTemplate.exchange(url, HttpMethod.POST,
                                         new HttpEntity<>(params, headers), String.class);
        } catch (RestClientException e) {
          log.error("rest client exception caught@" + e);
        }

        if (log.isDebugEnabled()) {
          log.debug("response body of AddBidInfo request@" + result);
        }
      }

    }

  }

  private Page<TransactionLogResult> processPaymentQuery(MultiValueMap<String, String> params, HttpHeaders headers) {
        Page<TransactionLogResult> logs = null;

        ResponseEntity<PaymentQueryResult> result = null;
        String url = this.env.getProperty(GW_API_URL) + "Reconciliation";
        if (log.isDebugEnabled()) {
            log.debug("transaction query url @" + url);

        }
        try {
            result = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(params, headers), PaymentQueryResult.class
            );
        } catch (RestClientException e) {
            log.error("rest client exception caught@" + e);
        }
        if (log.isDebugEnabled()) {
            log.debug("response body@" + result);
        }
        if (result == null) {
            return null;
        } else {

            PaymentQueryResult res = result.getBody();

            //PaymentQueryResult convert to paged Transaction Log Details
            //build List<TransactionLogResult> logLists
            List<TransactionLogResult> list = new ArrayList<>();

            List paramList = params.get("queryTransType");

            String type = "";

            if (paramList != null && !paramList.isEmpty()) {
                String tmpStr = (String) paramList.get(0);

                if ("LOANS".equals(tmpStr)) {
                    type = TransactionType.PAYMENT.toString();
                } else if ("REPAYMENT".equals(tmpStr)) {
                    type = TransactionType.REPAYMENT.toString();
                }
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            for (PaymentQueryResult.Item item : res.getReconciliationDtoList()) {
                TransactionLogResult _log = new TransactionLogResult();
                _log.setSerialNumber(item.getOrdId());
                _log.setType(type);
                _log.setStatus(item.getTransStat());
                _log.setAmount(new BigDecimal(item.getTransAmt()));
                _log.setFromCustId(item.getInvestCustId());
                _log.setToCustId(item.getBorrCustId());
                _log.setTransactedDate(LocalDate.parse(item.getPnrDate(), formatter));
                _log.setCreatedDate(LocalDateTime.now());
                list.add(_log);
            }

            //
            //
            //
            logs = new PageImpl<>(
                    list,
                    new PageRequest(Integer.parseInt(res.getPageNum()), Integer.parseInt(res.getPageSize())),
                    Long.parseLong(res.getTotalItems()));
        }

        return logs;
    }

    private Page<TransactionLogResult> processRechargeQuery(MultiValueMap<String, String> params, HttpHeaders headers) {
        Page<TransactionLogResult> logs = null;

        ResponseEntity<RechargeQueryResult> result = null;
        String url = this.env.getProperty(GW_API_URL) + "SaveReconciliation";
        if (log.isDebugEnabled()) {
            log.debug("transaction query url @" + url);

        }
        try {
            result = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(params, headers), RechargeQueryResult.class
            );
        } catch (RestClientException e) {
            log.error("rest client exception caught@" + e);
        }
        if (log.isDebugEnabled()) {
            log.debug("response body@" + result);
        }
        if (result == null) {
            return null;
        } else {
            RechargeQueryResult res = result.getBody();

            List<TransactionLogResult> list = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            //TODO Convert to list
            for (RechargeQueryResult.Item item : res.getSaveReconciliationDtoList()) {
                TransactionLogResult _log = new TransactionLogResult();
                _log.setSerialNumber(item.getOrdId());
                _log.setType(TransactionType.RECHARGE.toString());
                _log.setStatus(item.getTransStat());
                _log.setAmount(new BigDecimal(item.getTransAmt()));
                _log.setToCustId(item.getUsrCustId());
                _log.setTransactedDate(LocalDate.parse(item.getOrdDate(), formatter));
                _log.setCreatedDate(LocalDateTime.now());
                _log.setFee(new BigDecimal(item.getFeeAmt()));
                list.add(_log);
            }

            //
            //
            //
            logs = new PageImpl<>(
                    list,
                    new PageRequest(Integer.parseInt(res.getPageNum()), Integer.parseInt(res.getPageSize())),
                    Long.parseLong(res.getTotalItems()));

        }

        return logs;
    }

    private Page<TransactionLogResult> processWithdrawQuery(MultiValueMap<String, String> params, HttpHeaders headers) {
        Page<TransactionLogResult> logs = null;

        ResponseEntity<WithdrawQueryResult> result = null;
        String url = this.env.getProperty(GW_API_URL) + "CashReconciliation";
        if (log.isDebugEnabled()) {
            log.debug("transaction query url @" + url);

        }
        try {
            result = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(params, headers), WithdrawQueryResult.class
            );
        } catch (RestClientException e) {
            log.error("rest client exception caught@" + e);
        }
        if (log.isDebugEnabled()) {
            log.debug("response body@" + result);
        }
        if (result == null) {
            return null;
        } else {
            WithdrawQueryResult res = result.getBody();

            List<TransactionLogResult> list = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            for (WithdrawQueryResult.Item item : res.getCashReconciliationDtoList()) {
                TransactionLogResult _log = new TransactionLogResult();
                _log.setSerialNumber(item.getOrdId());
                _log.setType(TransactionType.WITHDRAW.toString());
                _log.setStatus(item.getTransStat());
                _log.setAmount(new BigDecimal(item.getTransAmt()));
                _log.setToCustId(item.getUsrCustId());
                _log.setTransactedDate(LocalDate.parse(item.getPnrDate(), formatter));
                _log.setCreatedDate(LocalDateTime.now());
                _log.setFee(new BigDecimal(item.getFeeAmt()));
                list.add(_log);
            }

            //
            //
            //
            logs = new PageImpl<>(
                    list,
                    new PageRequest(Integer.parseInt(res.getPageNum()), Integer.parseInt(res.getPageSize())),
                    Long.parseLong(res.getTotalItems()));
        }

        return logs;
    }

    private Page<TransactionLogResult> processMerchantTransfer(MultiValueMap<String, String> params, HttpHeaders headers) {
        Page<TransactionLogResult> logs = null;

        ResponseEntity<MerchantTransferQueryResult> result = null;
        String url = this.env.getProperty(GW_API_URL) + "TrfReconciliation";
        if (log.isDebugEnabled()) {
            log.debug("transaction query url @" + url);

        }
        try {
            result = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(params, headers), MerchantTransferQueryResult.class
            );
        } catch (RestClientException e) {
            log.error("rest client exception caught@" + e);
        }
        if (log.isDebugEnabled()) {
            log.debug("response body@" + result);
        }
        if (result == null) {
            return null;
        } else {
            MerchantTransferQueryResult res = result.getBody();

            List<TransactionLogResult> list = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            for (MerchantTransferQueryResult.Item item : res.getTrfReconciliationDtoList()) {
                TransactionLogResult _log = new TransactionLogResult();
                _log.setSerialNumber(item.getOrdId());
                _log.setType(TransactionType.MERCHANT_TRANSFER.toString());
                _log.setStatus(item.getTransStat());
                _log.setAmount(new BigDecimal(item.getTransAmt()));
                
                if (StringUtils.hasText(item.getInvestCustId())) {
                    _log.setToCustId(item.getInvestCustId());
                } else if(StringUtils.hasText(item.getBorrCustId())) {
                    _log.setToCustId(item.getBorrCustId());
                }
                
                _log.setFromCustId(item.getMerCustId());
                //_log.setFromAcctId(item.get);
                _log.setTransactedDate(LocalDate.parse(item.getPnrDate(), formatter));
                _log.setCreatedDate(LocalDateTime.now());
                //_log.setFee(new BigDecimal(item.getFeeAmt()));
                list.add(_log);
            }

            //
            //
            //
            logs = new PageImpl<>(
                    list,
                    new PageRequest(Integer.parseInt(res.getPageNum()), Integer.parseInt(res.getPageSize())),
                    Long.parseLong(res.getTotalItems()));
        }

        return logs;
    }

}
