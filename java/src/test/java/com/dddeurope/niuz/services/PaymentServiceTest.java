package com.dddeurope.niuz.services;

import com.dddeurope.niuz.dtos.PaymentDto;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {
    private PaymentRepository payments;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        payments = mock(PaymentRepository.class);
        paymentService = new PaymentService(payments);
    }

    @Test
    void getPayments() {
        when(payments.getByBankAccount("123-4567-89")).thenReturn(Arrays.asList(
                new Payment(100, "123-4567-89", "Freddy Kruger", "payment for: headline"))
        );
        assertThat(paymentService.getByBankAccount("123-4567-89")).containsExactly(
                new PaymentDto(100, "123-4567-89", "Freddy Kruger", "payment for: headline")
        );

    }
}
