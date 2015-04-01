/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hantsy
 */
public class RechargeQueryResult implements Serializable {
//{
//	"respCode":"000",
//	"respDesc":"成功",
//	"pageNum":"1",
//	"pageSize":"10",
//	"totalItems":"191",
//	"endDate":"20150215",
//	"beginDate":"20150210",
//	"cmdId":"SaveReconciliation",
//	"saveReconciliationDtoList":
//		[
//			{
//				"ordId":"86669084393082024896",
//				"transAmt":"1.00",
//				"usrCustId":"6000060000866781",
//				"openAcctId":"",
//				"feeAmt":"0.00",
//				"merCustId":"6000060000736315",
//				"ordDate":"20150215",
//				"openBankId":"CIB",
//				"feeCustId":"6000060000736315",
//				"transStat":"S",
//				"feeAcctId":"MDT000002",
//				"gateBusiId":"B2C"
//			}
//		]
//}

    private String respCode;
    private String respDesc;
    private String cmdId;
    private String pageNum;
    private String pageSize;
    private String totalItems;
    private String endDate;
    private String beginDate;

    private List<Item> saveReconciliationDtoList = new ArrayList<>();

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public List<Item> getSaveReconciliationDtoList() {
        return saveReconciliationDtoList;
    }

    public void setSaveReconciliationDtoList(List<Item> saveReconciliationDtoList) {
        this.saveReconciliationDtoList = saveReconciliationDtoList;
    }

    @Override
    public String toString() {
        return "RechargeQueryResult{" + "respCode=" + respCode + ", respDesc=" + respDesc + ", cmdId=" + cmdId + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalItems=" + totalItems + ", endDate=" + endDate + ", beginDate=" + beginDate + ", saveReconciliationDtoList=" + saveReconciliationDtoList + '}';
    }

    public static class Item {

        private String ordId;
        private String merCustId;
        private String usrCustId;
        private String feeCustId;
        private String openAcctId;
        private String openBankId;

        private String ordDate;
        private String transAmt;

        private String gateBusiId;
        private String feeAmt;
        private String transStat;
        private String feeAcctId;

        public String getUsrCustId() {
            return usrCustId;
        }

        public void setUsrCustId(String usrCustId) {
            this.usrCustId = usrCustId;
        }

        public String getMerCustId() {
            return merCustId;
        }

        public void setMerCustId(String merCustId) {
            this.merCustId = merCustId;
        }

        public String getFeeCustId() {
            return feeCustId;
        }

        public void setFeeCustId(String feeCustId) {
            this.feeCustId = feeCustId;
        }

        public String getOpenAcctId() {
            return openAcctId;
        }

        public void setOpenAcctId(String openAcctId) {
            this.openAcctId = openAcctId;
        }

        public String getOpenBankId() {
            return openBankId;
        }

        public void setOpenBankId(String openBankId) {
            this.openBankId = openBankId;
        }

        public String getOrdDate() {
            return ordDate;
        }

        public void setOrdDate(String ordDate) {
            this.ordDate = ordDate;
        }

        public String getGateBusiId() {
            return gateBusiId;
        }

        public void setGateBusiId(String gateBusiId) {
            this.gateBusiId = gateBusiId;
        }

        public String getOrdId() {
            return ordId;
        }

        public void setOrdId(String ordId) {
            this.ordId = ordId;
        }

        public String getTransAmt() {
            return transAmt;
        }

        public void setTransAmt(String transAmt) {
            this.transAmt = transAmt;
        }

        public String getFeeAmt() {
            return feeAmt;
        }

        public void setFeeAmt(String feeAmt) {
            this.feeAmt = feeAmt;
        }

        public String getTransStat() {
            return transStat;
        }

        public void setTransStat(String transStat) {
            this.transStat = transStat;
        }

        public String getFeeAcctId() {
            return feeAcctId;
        }

        public void setFeeAcctId(String feeAcctId) {
            this.feeAcctId = feeAcctId;
        }

        @Override
        public String toString() {
            return "Item{" 
                    + "ordId=" + ordId 
                    + ", merCustId=" + merCustId 
                    + ", usrCustId=" + usrCustId 
                    + ", feeCustId=" + feeCustId 
                    + ", openAcctId=" + openAcctId 
                    + ", openBankId=" + openBankId 
                    + ", ordDate=" + ordDate 
                    + ", transAmt=" + transAmt 
                    + ", gateBusiId=" + gateBusiId 
                    + ", feeAmt=" + feeAmt 
                    + ", transStat=" + transStat 
                    + ", feeAcctId=" + feeAcctId + '}';
        }

    }

}
