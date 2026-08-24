package com.hospital.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hospital.model.Billing;

public class BillingRepository {

    private Map<String, List<Billing>> billingsByPatient = new HashMap<>();

    public void addBilling(Billing billing) {

        String patientId = billing.getPatientId();

        if (!billingsByPatient.containsKey(patientId)) {
            billingsByPatient.put(patientId, new ArrayList<>());
        }

        billingsByPatient.get(patientId).add(billing);
    }

    public List<Billing> getBillingsByPatientId(String patientId) {

        List<Billing> billings = billingsByPatient.get(patientId);

        if (billings == null) {
            return new ArrayList<>();
        }

        return billings;
    }
    public Billing findByBillId(String billId) {

        for (List<Billing> billings : billingsByPatient.values()) {

            for (Billing billing : billings) {

                if (billing.getBillId().equals(billId)) {
                    return billing;
                }
            }
        }

        return null;
    }
}