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
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.fenglianfinance.bill.model.WithdrawalsDetails;
import com.fenglianfinance.bill.report.service.ReportService;

public class WithdrawalsView extends AbstractExcelView {

    private static final Logger log = LoggerFactory.getLogger(WithdrawalsView.class);

    private ReportService reportService;

    public WithdrawalsView(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("building WithdrawalsView...");
        }

        LocalDate d = (LocalDate) map.get("model");

        List<WithdrawalsDetails> withdrawalsDetails = reportService.findWithdrawalsViewByDate(d);

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell styleCell = getCell(sheet, 0, 0);
        HSSFCellStyle style = styleCell.getCellStyle();
        style.setFillBackgroundColor(new HSSFColor.WHITE().getIndex());

        int i = 1;
        for (WithdrawalsDetails details : withdrawalsDetails) {
            HSSFRow row = sheet.getRow(i);

            if (row == null) {
                row = sheet.createRow(i);
            }

            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String fee = "";

            if (details.getFee() != null) {
                fee = details.getFee().toString();
            }

            String transactedDate = "";

            if (details.getTransactedDate() != null) {
                transactedDate = details.getTransactedDate().format(date);
            }

            setCellValue(sheet, style, i, 0, details.getUsername(), row);
            setCellValue(sheet, style, i, 1, details.getName(), row);
            setCellValue(sheet, style, i, 2, String.format("%10.2f", details.getAmount()), row);
            setCellValue(sheet, style, i, 3, fee, row);
            setCellValue(sheet, style, i, 4, transactedDate, row);

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
