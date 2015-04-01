package com.fenglianfinance.bill.report.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.fenglianfinance.bill.report.model.RepayMentDetails;
import com.fenglianfinance.bill.report.service.ReportService;

public class RepayMentView extends AbstractExcelView {

    private static final Logger log = LoggerFactory.getLogger(RepayMentView.class);

    private ReportService reportService;

    private MessageSource messageSource;

    public RepayMentView(ReportService reportService, MessageSource messageSource) {
        super();
        this.reportService = reportService;
        this.messageSource = messageSource;

    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("building RepayMentView...");
        }

        LocalDate d = (LocalDate) map.get("model");

        List<RepayMentDetails> repayMentDetails = reportService.findRepayMent(d);

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell styleCell = getCell(sheet, 0, 0);
        HSSFCellStyle style = styleCell.getCellStyle();
        style.setFillBackgroundColor(new HSSFColor.WHITE().getIndex());

        int i = 1;
        for (RepayMentDetails details : repayMentDetails) {
            HSSFRow row = sheet.getRow(i);

            if (row == null) {
                row = sheet.createRow(i);
            }

            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");

            String s = String.format("%10.2f",
                    (details.getTotalAmount().multiply(new BigDecimal(details.getAnnualPercentageRate()))));

            String repaymentDeadline = "";

            if (details.getRepaymentDeadline() != null) {
                repaymentDeadline = details.getRepaymentDeadline().format(date);
            }

            setCellValue(sheet, style, i, 0, details.getEnterpriseName(), row);
            setCellValue(sheet, style, i, 1, "", row);
            setCellValue(sheet, style, i, 2, details.getName(), row);
            setCellValue(sheet, style, i, 3, messageSource.getMessage(details.getType(), new String[] {}, null), row);
            setCellValue(sheet, style, i, 4, details.getDuration() + "", row);
            setCellValue(sheet, style, i, 5, repaymentDeadline, row);
            setCellValue(sheet, style, i, 6, String.format("%10.2f", details.getTotalAmount()), row);
            setCellValue(sheet, style, i, 7, s, row);
            setCellValue(sheet, style, i, 8, details.getDuration() + "", row);
            //            setCellValue(sheet, style, i, 9, details.getRepaymentDeadline().format(date), row);
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