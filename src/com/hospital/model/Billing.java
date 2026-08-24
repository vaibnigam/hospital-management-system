package com.hospital.model;

import java.time.LocalDate;

public class Billing {
	private String billId;
	private String patientId;
	private double amount;
	private BillingStatus status;
	private LocalDate billingDate;

	public Billing(String patientId, double amount) {
		this.patientId = patientId;
		this.amount = amount;
		this.status = BillingStatus.PENDING;
		this.billingDate = LocalDate.now();
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public BillingStatus getStatus() {
		return status;
	}

	public void setStatus(BillingStatus status) {
		this.status = status;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	@Override
	public String toString() {
		return "Billing [billId=" + billId + ", patientId=" + patientId + ", amount=" + amount + ", status=" + status
				+ ", billingDate=" + billingDate + "]";
	}
}