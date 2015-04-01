/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.report.view;

import com.fenglianfinance.bill.report.model.SummarizedStatisticsDetails;
//import com.fenglianfinance.bill.model.UserAccountDetails;
import com.fenglianfinance.bill.report.service.ReportService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hantsy
 */

public class SummarizedStatisticsView extends AbstractExcelView {

    private static final Logger log = LoggerFactory.getLogger(SummarizedStatisticsView.class);

    private ReportService reportService;

    public SummarizedStatisticsView(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook,
            HttpServletRequest reuqest, HttpServletResponse response) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("building UserRegistrationView...");
        }

        //        List<UserAccountDetails> users = (List<UserAccountDetails>) map.get("model");
        Map model = (HashMap) map.get("model");
        LocalDate start = (LocalDate) model.get("start");
        LocalDate end = (LocalDate) model.get("end");

        List<SummarizedStatisticsDetails> summarizedStatisticsDetailses = reportService
                .findSummarizedStatisticsDetailsList(start, end);

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell styleCell = getCell(sheet, 0, 0);
        HSSFCellStyle style = styleCell.getCellStyle();
        style.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        int i = 1;

        for (SummarizedStatisticsDetails details : summarizedStatisticsDetailses) {
            HSSFRow row = sheet.getRow(i);

            int j = 0;

            if (row == null) {
                row = sheet.createRow(i);
            }

            setCellValue(sheet, style, i, j++, details.getSummarizedDate().toString(), row);
            setCellValue(sheet, style, i, j++, details.getUserRegisterationCount() + "", row);
            setCellValue(sheet, style, i, j++, details.getIdCardVerificationCount() + "", row);
            setCellValue(sheet, style, i, j++, details.getInchargeUserCount() + "", row);
            setCellValue(sheet, style, i, j++, String.format("%10.2f", details.getTotalInchargeAmount()), row);
            setCellValue(sheet, style, i, j++, String.format("%10.2f", details.getAvgInchargeAmount()), row);
            setCellValue(sheet, style, i, j++, details.getPaidUserCount() + "", row);
            setCellValue(sheet, style, i, j++, details.getPaymentLogCount() + "", row);
            setCellValue(sheet, style, i, j++, String.format("%10.2f", details.getTotalPaymentAmount()), row);
            setCellValue(sheet, style, i, j++, String.format("%10.2f", details.getAvgPaymentAmount()), row);
            setCellValue(sheet, style, i, j++, details.getAvgPaymentCount() + "", row);
            setCellValue(sheet, style, i, j++, details.getWithdrawUserCount() + "", row);
            setCellValue(sheet, style, i, j++, String.format("%10.2f", details.getTotalWithdrawAmount()), row);
            setCellValue(sheet, style, i, j++, String.format("%10.2f", details.getAvgWithdrawAmount()), row);
            setCellValue(sheet, style, i, j++, details.getUserViewCount() + "", row);
            setCellValue(sheet, style, i, j++, details.getPageViewCount() + "", row);

            //finally increase the counter.
            i++;
        }
    }

    private void setCellValue(HSSFSheet sheet, HSSFCellStyle style, int row, int col, String value, HSSFRow hRow) {
        HSSFCell cell = getCell(sheet, row, col);
        if (cell == null) {
            cell = hRow.createCell(col);
        }
        cell.setCellStyle(style);
        setText(cell, value);
    }
}
