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
public class WithdrawQueryResult implements Serializable {
//{
//	"respCode":"000",
//	"respDesc":"成功",
//	"pageNum":"1",
//	"pageSize":"10",
//	"totalItems":"13",
//	"endDate":"20150215",
//	"beginDate":"20150210",
//	"cmdId":"CashReconciliation",
//	"cashReconciliationDtoList":
//	[
//		{
//			"usrCustId":"6000060000876235",
//			"merCustId":"6000060000736315",
//			"feeCustId":null,
//			"cardId":"6222020000000000",
//			"servFee":"",
//			"pnrDate":"20150215",
//			"servFeeAcctId":"",
//			"ordId":"37825525103180953343",
//			"transAmt":"21.00",
//			"pnrSeqId":"0000035140",
//			"feeAmt":"2.01",
//			"transStat":"S",
//			"feeAcctId":null
//		}
//	]
//}

    private String respCode;
    private String respDesc;
    private String cmdId;
    private String pageNum;
    private String pageSize;
    private String totalItems;
    private String endDate;
    private String beginDate;

    private List<Item> cashReconciliationDtoList = new ArrayList<>();

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

    public List<Item> getCashReconciliationDtoList() {
        return cashReconciliationDtoList;
    }

    public void setCashReconciliationDtoList(List<Item> cashReconciliationDtoList) {
        this.cashReconciliationDtoList = cashReconciliationDtoList;
    }

    @Override
    public String toString() {
        return "WithdrawQueryResult{" + "respCode=" + respCode + ", respDesc=" + respDesc + ", cmdId=" + cmdId + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalItems=" + totalItems + ", endDate=" + endDate + ", beginDate=" + beginDate + ", cashReconciliationDtoList=" + cashReconciliationDtoList + '}';
    }

    public static class Item {

        private String usrCustId;
        private String merCustId;
        private String feeCustId;
        private String cardId;
        private String servFee;
        private String pnrDate;
        private String servFeeAcctId;
        private String ordId;
        private String transAmt;

        private String pnrSeqId;
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

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getServFee() {
            return servFee;
        }

        public void setServFee(String servFee) {
            this.servFee = servFee;
        }

        public String getPnrDate() {
            return pnrDate;
        }

        public void setPnrDate(String pnrDate) {
            this.pnrDate = pnrDate;
        }

        public String getServFeeAcctId() {
            return servFeeAcctId;
        }

        public void setServFeeAcctId(String servFeeAcctId) {
            this.servFeeAcctId = servFeeAcctId;
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

        public String getPnrSeqId() {
            return pnrSeqId;
        }

        public void setPnrSeqId(String pnrSeqId) {
            this.pnrSeqId = pnrSeqId;
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
            return "Item{" + "usrCustId=" + usrCustId + ", merCustId=" + merCustId + ", feeCustId=" + feeCustId + ", cardId=" + cardId + ", servFee=" + servFee + ", pnrDate=" + pnrDate + ", servFeeAcctId=" + servFeeAcctId + ", ordId=" + ordId + ", transAmt=" + transAmt + ", pnrSeqId=" + pnrSeqId + ", feeAmt=" + feeAmt + ", transStat=" + transStat + ", feeAcctId=" + feeAcctId + '}';
        }

    }

}
