/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.report.view;

import com.fenglianfinance.bill.model.UserAccountDetails;
import com.fenglianfinance.bill.report.service.ReportService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * @author hantsy
 */

public class UserRegistrationView extends AbstractExcelView {

  private static final Logger log = LoggerFactory.getLogger(UserRegistrationView.class);

  private ReportService reportService;

  public UserRegistrationView(ReportService reportService) {
    this.reportService = reportService;
  }

  @Override
  protected void buildExcelDocument(Map<String, Object> map,
                                    org.apache.poi.hssf.usermodel.HSSFWorkbook workbook,
                                    HttpServletRequest reuqest,
                                    HttpServletResponse response) throws Exception {

    if (log.isDebugEnabled()) {
      log.debug("building UserRegistrationView...");
    }

//        List<UserAccountDetails> users = (List<UserAccountDetails>) map.get("model");
    LocalDate d = (LocalDate) map.get("model");

    List<UserAccountDetails> users = reportService.findResigeredUsersByDate(d);

    HSSFSheet sheet = workbook.getSheetAt(0);

    HSSFCell styleCell = getCell(sheet, 0, 0);
    HSSFCellStyle style = styleCell.getCellStyle();
    style.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);



    int i = 1;
    for (UserAccountDetails details : users) {

      int j=0;

      HSSFRow row = sheet.getRow(i);

      if (row == null) {
        row = sheet.createRow(i);
      }

      DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");

      setCellValue(sheet, style, i, j++, details.getUsername(), row);
      setCellValue(sheet, style, i, j++, details.getName(), row);
      setCellValue(sheet, style, i, j++, details.getMobileNumber(), row);
      setCellValue(sheet, style, i, j++, details.getCreatedDate().format(date), row);
      setCellValue(sheet, style, i, j++, details.getCreatedDate().format(time), row);
      setCellValue(sheet, style, i, j++, "", row);
      setCellValue(sheet, style, i, j++, "", row);

      //finally increase the counter.
      i++;
    }
  }

  private void setCellValue(HSSFSheet sheet, HSSFCellStyle style, int row, int col, String value,
                            HSSFRow hRow) {
    HSSFCell cell = getCell(sheet, row, col);
    if (cell == null) {
      cell = hRow.createCell(col);
    }
    cell.setCellStyle(style);
    setText(cell, value);
  }
}
