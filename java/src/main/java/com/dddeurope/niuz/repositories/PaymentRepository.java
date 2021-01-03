package com.dddeurope.niuz.repositories;

import com.dddeurope.niuz.entities.Payment;

import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);
    List<Payment> getByBankAccount(String bankAccount);
}
