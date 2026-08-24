package com.hospital.model;

public class Billing {
    private String billId;
    private String patientId;
    private double amount;
    private BillingStatus status;

    public Billing(String patientId, double amount) {
        this.patientId = patientId;
        this.amount = amount;
        this.status = BillingStatus.PENDING;
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


	@Override
    public String toString() {
        return "Billing [billId=" + billId + ", patientId=" + patientId + ", amount=" + amount + ", status=" + status + "]";
    }
}