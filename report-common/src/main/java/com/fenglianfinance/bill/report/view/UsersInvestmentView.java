/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.report.view;

import com.fenglianfinance.bill.domain.UsersInvestmentReport;
import com.fenglianfinance.bill.report.model.UserReceivablesReport;
import com.fenglianfinance.bill.report.service.ReportService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hantsy
 */

public class UsersInvestmentView extends AbstractExcelView {

    private static final Logger log = LoggerFactory.getLogger(UsersInvestmentView.class);

    private ReportService reportService;

    private MessageSource messageSource;

    public UsersInvestmentView(ReportService reportService, MessageSource messageSource) {
        this.reportService = reportService;
        this.messageSource = messageSource;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook,
            HttpServletRequest reuqest, HttpServletResponse response) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("building UsersInvestmentView ...");
        }

        //        List<UserAccountDetails> users = (List<UserAccountDetails>) map.get("model");
        Map model = (HashMap) map.get("model");
        LocalDate start = (LocalDate) model.get("start");
        LocalDate end = (LocalDate) model.get("end");

        List<UsersInvestmentReport> list = reportService.findUsersInvestmentDailyCount(start.atStartOfDay(),
                end.plus(1, ChronoUnit.DAYS).atStartOfDay());

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell styleCell = getCell(sheet, 0, 0);
        HSSFCellStyle style = styleCell.getCellStyle();
        style.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        int i = 1;

        for (UsersInvestmentReport details : list) {

            HSSFRow row = sheet.getRow(i);

            int j = 0;

            if (row == null) {
                row = sheet.createRow(i);
            }

            log.debug(details.toString());

            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            setCellValue(sheet, style, i, j++, "", row);
            setCellValue(sheet, style, i, j++, details.getProductName(), row);
            setCellValue(sheet, style, i, j++,
                    messageSource.getMessage(details.getType().toString(), new String[] {}, null), row);
            setCellValue(sheet, style, i, j++, details.getDuration() + "", row);
            setCellValue(sheet, style, i, j++, details.getUserName() + "", row);
            setCellValue(sheet, style, i, j++, details.getCardNumber(), row);
            setCellValue(sheet, style, i, j++, details.getMobileNumber(), row);
            setCellValue(sheet, style, i, j++, details.getUserId().toString(), row);
            setCellValue(sheet, style, i, j++, "", row);
            setCellValue(sheet, style, i, j++, "", row);
            setCellValue(sheet, style, i, j++, String.format("%10.2f", details.getAmount()), row);
            setCellValue(sheet, style, i, j++, details.getPaidDate().format(date), row);

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
