package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @author wangli@flf77.com
 *
 */
public class PurchaseOrderModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String serialNumber;

	private BigDecimal amount = BigDecimal.ZERO;

	private String status;

	private boolean active = true;

	private BigDecimal accruedInterestAmount = BigDecimal.ZERO;

	private LocalDateTime accruedStartDate;

	private LocalDateTime accruedEndDate;

	private LocalDateTime completedDate;

	private LocalDateTime paidDate;

	private LocalDateTime placedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public BigDecimal getAccruedInterestAmount() {
		return accruedInterestAmount;
	}

	public void setAccruedInterestAmount(BigDecimal accruedInterestAmount) {
		this.accruedInterestAmount = accruedInterestAmount;
	}

	public LocalDateTime getAccruedStartDate() {
		return accruedStartDate;
	}

	public void setAccruedStartDate(LocalDateTime accruedStartDate) {
		this.accruedStartDate = accruedStartDate;
	}

	public LocalDateTime getAccruedEndDate() {
		return accruedEndDate;
	}

	public void setAccruedEndDate(LocalDateTime accruedEndDate) {
		this.accruedEndDate = accruedEndDate;
	}

	public LocalDateTime getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(LocalDateTime completedDate) {
		this.completedDate = completedDate;
	}

	public LocalDateTime getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDateTime paidDate) {
		this.paidDate = paidDate;
	}

	public LocalDateTime getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(LocalDateTime placedDate) {
		this.placedDate = placedDate;
	}

	@Override
	public String toString() {
		return "PurchaseOrderModel [id=" + id + ", serialNumber="
				+ serialNumber + ", amount=" + amount + ", status=" + status
				+ ", active=" + active + ", accruedInterestAmount="
				+ accruedInterestAmount + ", accruedStartDate="
				+ accruedStartDate + ", accruedEndDate=" + accruedEndDate
				+ ", completedDate=" + completedDate + ", paidDate=" + paidDate
				+ ", placedDate=" + placedDate + "]";
	}

}
