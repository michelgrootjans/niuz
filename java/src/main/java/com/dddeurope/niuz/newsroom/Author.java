package com.dddeurope.niuz.newsroom;

import java.util.Objects;

public class Author {
    private final String id;
    private final String name;
    private final String bankAccount;
    private final String contractType;
    private final int rate;

    public Author(String id, String name, String bankAccount, String contractType, int rate) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
        this.contractType = contractType;
        this.rate = rate;
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

    public int getRate() {
        return rate;
    }

    public boolean paysBySubmission() {
        return "pay-by-submission".equals(contractType);
    }

    public boolean paysByPublication() {
        return "pay-by-publication".equals(contractType);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", contractType='" + contractType + '\'' +
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
}
