package com.dddeurope.niuz.entities;

import java.util.Objects;

public class Author {
    private final String id;
    private final String name;
    private final String bankAccount;
    private final String contractType;

    public Author(String id, String name, String bankAccount, String contractType) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
        this.contractType = contractType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public boolean paysBySubmission() {
        return "pay-by-submission".equals(contractType);
    }

    public boolean paysByPublication() {
        return "pay-by-publication".equals(contractType);
    }
}
