package com.dddeurope.niuz.finance;

import java.util.Objects;

public class PaymentDto {
    private final int amount;
    private final String bankAccount;
    private final String recipient;
    private final String description;

    public PaymentDto(int amount, String bankAccount, String recipient, String description) {
        this.amount = amount;
        this.bankAccount = bankAccount;
        this.recipient = recipient;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "amount=" + amount +
                ", bankAccount='" + bankAccount + '\'' +
                ", recipient='" + recipient + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        return amount == that.amount && Objects.equals(bankAccount, that.bankAccount) && Objects.equals(recipient, that.recipient) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, bankAccount, recipient, description);
    }
}
