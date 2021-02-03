package com.dddeurope.niuz.finance;

import java.util.Objects;

public class Payment {
    private final int amount;
    private final String bankAccount;
    private final String recipientName;
    private final String description;

    public Payment(int amount, String bankAccount, String recipientName, String description) {
        this.amount = amount;
        this.bankAccount = bankAccount;
        this.recipientName = recipientName;
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", bankAccount='" + bankAccount + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return amount == payment.amount && Objects.equals(bankAccount, payment.bankAccount) && Objects.equals(recipientName, payment.recipientName) && Objects.equals(description, payment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, bankAccount, recipientName, description);
    }
}
