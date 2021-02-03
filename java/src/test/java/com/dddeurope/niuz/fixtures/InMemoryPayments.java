package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.finance.Payment;
import com.dddeurope.niuz.finance.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryPayments implements PaymentRepository {
    final List<Payment> payments = new ArrayList<>();

    @Override
    public void save(Payment payment) {
        payments.add(payment);
    }

    @Override
    public List<Payment> getByBankAccount(String bankAccount) {
        return payments.stream()
                       .filter(p -> Objects.equals(p.getBankAccount(), bankAccount))
                       .collect(Collectors.toList());
    }}
