package com.dddeurope.niuz.finance;

import com.dddeurope.niuz.finance.Payment;

import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);
    List<Payment> getByBankAccount(String bankAccount);
}
