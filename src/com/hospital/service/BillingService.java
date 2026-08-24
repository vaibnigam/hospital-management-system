package com.hospital.service;

import java.util.List;

import com.hospital.model.Billing;
import com.hospital.model.BillingStatus;
import com.hospital.repository.BillingRepository;

public class BillingService {

    private BillingRepository billingRepository;

    private PatientService patientService;

    private int nextBillId = 1;

    public BillingService(PatientService patientService) {

        this.billingRepository = new BillingRepository();

        this.patientService = patientService;
    }

    public Billing generateBill(String patientId, double amount) {

        if (patientService.searchPatientById(patientId) == null) {
            return null;
        }

        if (amount <= 0) {
            return null;
        }

        String billId = "B" + nextBillId;

        nextBillId++;

        Billing billing = new Billing(patientId, amount);

        billing.setBillId(billId);

        billingRepository.addBilling(billing);

        return billing;
    }

    public boolean markAsPaid(String billId) {

        Billing billing = billingRepository.findByBillId(billId);

        if (billing == null) {
            return false;
        }

        if (billing.getStatus() == BillingStatus.PAID) {
            return false;
        }

        billing.setStatus(BillingStatus.PAID);

        return true;
    }

    public List<Billing> getBillingHistory(String patientId) {

        return billingRepository.getBillingsByPatientId(patientId);
    }
}