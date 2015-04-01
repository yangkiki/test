/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.api.report;

import com.fenglianfinance.bill.model.ApiConstants;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fenglianfinance.bill.report.service.ReportService;
import com.fenglianfinance.bill.service.SummarizedStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hantsy
 */
@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_API_REPORT)
public class ReportController {

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    @Inject
    ReportService reportService;

    @Inject
    SummarizedStatisticsService summarizedStatisticsService;

    @RequestMapping(value = "registeredusers", method = RequestMethod.GET)
    public ModelAndView getRegisteredUsers(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation user registration report...");
        }
        return new ModelAndView("userRegistrationView", "model", d);
    }

    @RequestMapping(value = "rechargeenterprise", method = RequestMethod.GET)
    public ModelAndView getRechargeEnterprise(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d, HttpServletRequest request,
            HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation Enterprise recharge daily report...");
        }
        return new ModelAndView("rechargeEnterpriseView", "model", d);
    }

    @RequestMapping(value = "fundingenterprise", method = RequestMethod.GET)
    public ModelAndView getFundingEnterprise(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation Enterprise funding daily report...");
        }
        return new ModelAndView("fundingEnterpriseView", "model", d);
    }

    @RequestMapping(value = "summarizedstatistics", method = RequestMethod.GET)
    public ModelAndView getSummarizedstatistics(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d, HttpServletRequest request,
            HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation Enterprise recharge daily report...");
        }
        Map<String, LocalDate> condition = new HashMap<>();
        condition.put("start", d);
        condition.put("end", d);

        return new ModelAndView("summarizedStatisticsView", "model", condition);
    }

    @RequestMapping(value = "repayment", method = RequestMethod.GET)
    public ModelAndView getRepayment(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation Enterprise funding daily report...");
        }
        return new ModelAndView("repayMentView", "model", d);
    }

    @RequestMapping(value = "userreceivables", method = RequestMethod.GET)
    public ModelAndView getUserReceivables(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation userReceivables daily report...");
        }
        Map<String, LocalDate> condition = new HashMap<>();
        condition.put("start", d);
        condition.put("end", d);

        return new ModelAndView("userReceivablesView", "model", condition);
    }

    @RequestMapping(value = "rechargeuser", method = RequestMethod.GET)
    public ModelAndView getRechargeUser(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation Enterprise recharge daily report...");
        }
        return new ModelAndView("rechargeUserView", "model", d);
    }

    @RequestMapping(value = "repaymentuser", method = RequestMethod.GET)
    public ModelAndView getRepayMentUser(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation User repayment daily report...");
        }
        return new ModelAndView("repayMentUserView", "model", d);
    }

    @RequestMapping(value = "usersinvestments", method = RequestMethod.GET)
    public ModelAndView getUsersInvestments(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation userReceivables daily report...");
        }
        Map<String, LocalDate> condition = new HashMap<>();
        condition.put("start", d);
        condition.put("end", d);

        return new ModelAndView("usersInvestmentView", "model", condition);
    }

    @RequestMapping(value = "currentstock", method = RequestMethod.GET)
    public ModelAndView getCurrentStock(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation product current stock daily report...");
        }
        return new ModelAndView("currentStockView", "model", d);
    }

    @RequestMapping(value = "withdrawals", method = RequestMethod.GET)
    public ModelAndView getWithdrawals(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate d,
            HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("generation withdrawalsView  daily report...");
        }
        return new ModelAndView("withdrawalsView", "model", d);
    }

}
