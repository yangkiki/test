package com.fenglianfinance.bill.report.view;

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

import com.fenglianfinance.bill.report.model.FundingDetails;
import com.fenglianfinance.bill.report.service.ReportService;

public class FundingEnterpriseView extends AbstractExcelView {

    private static final Logger log = LoggerFactory.getLogger(FundingEnterpriseView.class);

    private ReportService reportService;

    private MessageSource messageSource;

    public FundingEnterpriseView(ReportService reportService, MessageSource messageSource) {
        this.reportService = reportService;
        this.messageSource = messageSource;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("building FundingEnterpriseView...");
        }

        LocalDate d = (LocalDate) map.get("model");

        List<FundingDetails> fundingDetails = reportService.findFundingEnterpriseByDate(d);

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell styleCell = getCell(sheet, 0, 0);
        HSSFCellStyle style = styleCell.getCellStyle();
        style.setFillBackgroundColor(new HSSFColor.WHITE().getIndex());

        int i = 1;
        for (FundingDetails details : fundingDetails) {
            HSSFRow row = sheet.getRow(i);

            if (row == null) {
                row = sheet.createRow(i);
            }

            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");

            String repaymentDeadline = "";

            if (details.getRepaymentDeadline() != null) {
                repaymentDeadline = details.getRepaymentDeadline().format(date);
            }

            String price = String.format("%10.2f", details.getTotalAmount().subtract(details.getFee()));

            setCellValue(sheet, style, i, 0, details.getEnterpriseName(), row);
            setCellValue(sheet, style, i, 1, "", row);
            setCellValue(sheet, style, i, 2, details.getName(), row);
            setCellValue(sheet, style, i, 3, messageSource.getMessage(details.getType(), new String[] {}, null), row);
            setCellValue(sheet, style, i, 4, details.getAnnualPercentageRate() + "%", row);
            setCellValue(sheet, style, i, 5, price, row);
            setCellValue(sheet, style, i, 6, details.getFee() + "", row);
            setCellValue(sheet, style, i, 7, String.format("%10.2f", details.getTotalAmount()), row);
            setCellValue(sheet, style, i, 8, details.getDuration() + "", row);
            setCellValue(sheet, style, i, 9, repaymentDeadline, row);

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
