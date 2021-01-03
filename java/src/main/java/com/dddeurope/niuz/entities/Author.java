package com.dddeurope.niuz.entities;

import java.util.Objects;

public class Author {
    private final String id;
    private final String name;
    private final String bankAccount;

    public Author(String id, String name, String bankAccount) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
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
}
