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
public class MerchantTransferQueryResult implements Serializable {
    private static final long serialVersionUID = 1L;
//{
//	"respCode":"000",
//	"respDesc":"成功",
//	"cmdId":"TrfReconciliation",
//	"pageNum":"1",
//	"pageSize":"10",
//	"totalItems":"1",
//	"endDate":"20150315",
//	"beginDate":"20150110",
//	"trfReconciliationDtoList":
//	[
//		{
//			"ordId":"00000000000000000812",
//			"ordDate":"20150213",
//			"merCustId":"6000060000736315",
//			"investCustId":"6000060000775816",
//			"borrCustId":"6000060000775816",
//			"transAmt":"100.00",
//			"transStat":"S",
//			"pnrDate":"20150213",
//			"pnrSeqId":"3782838293929",
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

    private List<Item> trfReconciliationDtoList = new ArrayList<>();

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

    public List<Item> getTrfReconciliationDtoList() {
        return trfReconciliationDtoList;
    }

    public void setTrfReconciliationDtoList(List<Item> trfReconciliationDtoList) {
        this.trfReconciliationDtoList = trfReconciliationDtoList;
    }
    
    

    @Override
    public String toString() {
        return "MerchantTransferQueryResult{" + "respCode=" + respCode + ", respDesc=" + respDesc + ", cmdId=" + cmdId + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalItems=" + totalItems + ", endDate=" + endDate + ", beginDate=" + beginDate + ", trfReconciliationDtoList=" + trfReconciliationDtoList + '}';
    }

    public static class Item {

        private String ordId;
        private String ordDate;
        private String merCustId;
        private String investCustId;
        private String borrCustId;
        private String transAmt;
        private String pnrDate;
        private String pnrSeqId;
        private String transStat;

        public String getMerCustId() {
            return merCustId;
        }

        public void setMerCustId(String merCustId) {
            this.merCustId = merCustId;
        }

        public String getPnrDate() {
            return pnrDate;
        }

        public void setPnrDate(String pnrDate) {
            this.pnrDate = pnrDate;
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

        public String getTransStat() {
            return transStat;
        }

        public void setTransStat(String transStat) {
            this.transStat = transStat;
        }

        public String getOrdDate() {
            return ordDate;
        }

        public void setOrdDate(String ordDate) {
            this.ordDate = ordDate;
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

        @Override
        public String toString() {
            return "Item{" + "ordId=" + ordId + ", ordDate=" + ordDate + ", merCustId=" + merCustId + ", investCustId=" + investCustId + ", borrCustId=" + borrCustId + ", transAmt=" + transAmt + ", pnrDate=" + pnrDate + ", pnrSeqId=" + pnrSeqId + ", transStat=" + transStat + '}';
        }
    }
}
