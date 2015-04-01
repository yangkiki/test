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
public class PaymentQueryResult implements Serializable {
//    {
//	"respCode":"000",
//	"respDesc":"成功",
//	"queryTransType":"LOANS",
//	"cmdId":"Reconciliation",
//	"pageNum":"1",
//	"pageSize":"10",
//	"totalItems":"254",
//	"endDate":"20150215",
//	"beginDate":"20150210",
//	"reconciliationDtoList":
//		[
//			{
//				"ordId":"00000000000000000812",
//				"ordDate":"20150213",
//				"merCustId":"6000060000736315",
//				"transAmt":"0.50",
//				"pnrSeqId":"0001073057",
//				"investCustId":"6000060000795493",
//				"borrCustId":"6000060000775816",
//				"transStat":"P",
//				"pnrDate":"20150213",
//				"divDetails":[]
//			}
//		]
//}

    private String respCode;
    private String respDesc;
    private String queryTransType;
    private String cmdId;
    private String pageNum;
    private String pageSize;
    private String totalItems;
    private String endDate;
    private String beginDate;

    private List<Item> reconciliationDtoList = new ArrayList<>();

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

    public String getQueryTransType() {
        return queryTransType;
    }

    public void setQueryTransType(String queryTransType) {
        this.queryTransType = queryTransType;
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

    public List<Item> getReconciliationDtoList() {
        return reconciliationDtoList;
    }

    public void setReconciliationDtoList(List<Item> reconciliationDtoList) {
        this.reconciliationDtoList = reconciliationDtoList;
    }
    
    public static class Item {

        private String ordId;
        private String ordDate;
        private String merCustId;
        private String transAmt;
        private String pnrSeqId;
        private String investCustId;
        private String borrCustId;
        private String transStat;
        private String pnrDate;
        private List<DivDetails> divDetails=new ArrayList<>();

        public String getOrdId() {
            return ordId;
        }

        public void setOrdId(String ordId) {
            this.ordId = ordId;
        }

        public String getOrdDate() {
            return ordDate;
        }

        public void setOrdDate(String ordDate) {
            this.ordDate = ordDate;
        }

        public String getMerCustId() {
            return merCustId;
        }

        public void setMerCustId(String merCustId) {
            this.merCustId = merCustId;
        }

        public String getTransAmt() {
            return transAmt;
        }

        public void setTransAmt(String transAmt) {
            this.transAmt = transAmt;
        }

        public String getPnrSeqId() {
            return pnrSeqId;
        }

        public void setPnrSeqId(String pnrSeqId) {
            this.pnrSeqId = pnrSeqId;
        }

        public String getInvestCustId() {
            return investCustId;
        }

        public void setInvestCustId(String investCustId) {
            this.investCustId = investCustId;
        }

        public String getBorrCustId() {
            return borrCustId;
        }

        public void setBorrCustId(String borrCustId) {
            this.borrCustId = borrCustId;
        }

        public String getTransStat() {
            return transStat;
        }

        public void setTransStat(String transStat) {
            this.transStat = transStat;
        }

        public String getPnrDate() {
            return pnrDate;
        }

        public void setPnrDate(String pnrDate) {
            this.pnrDate = pnrDate;
        }

        public List<DivDetails> getDivDetails() {
            return divDetails;
        }

        public void setDivDetails(List<DivDetails> divDetails) {
            this.divDetails = divDetails;
        }

        @Override
        public String toString() {
            return "Item{" + "ordId=" + ordId + ", ordDate=" + ordDate + ", merCustId=" + merCustId + ", transAmt=" + transAmt + ", pnrSeqId=" + pnrSeqId + ", investCustId=" + investCustId + ", borrCustId=" + borrCustId + ", transStat=" + transStat + ", pnrDate=" + pnrDate + ", divDetails=" + divDetails + '}';
        }
        
            
    }

    public static class DivDetails {

        private String divCustId;
        private String divAcctId;
        private String divAmt;

        public String getDivCustId() {
            return divCustId;
        }

        public void setDivCustId(String divCustId) {
            this.divCustId = divCustId;
        }

        public String getDivAcctId() {
            return divAcctId;
        }

        public void setDivAcctId(String divAcctId) {
            this.divAcctId = divAcctId;
        }

        public String getDivAmt() {
            return divAmt;
        }

        public void setDivAmt(String divAmt) {
            this.divAmt = divAmt;
        }

        @Override
        public String toString() {
            return "DivDetails{" + "divCustId=" + divCustId + ", divAcctId=" + divAcctId + ", divAmt=" + divAmt + '}';
        }
            
    }

}
