package com.dddeurope.niuz.services;

import com.dddeurope.niuz.dtos.PaymentDto;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.repositories.PaymentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private final PaymentRepository payments;

    public PaymentService(PaymentRepository payments) {
        this.payments = payments;
    }

    public List<PaymentDto> getByBankAccount(String bankAccount) {
        return payments.getByBankAccount(bankAccount).stream()
                       .map(this::map)
                       .collect(Collectors.toList());
    }

    private PaymentDto map(Payment payment) {
        return new PaymentDto(payment.getAmount(), payment.getBankAccount(), payment.getRecipientName(), payment.getDescription());
    }
}
