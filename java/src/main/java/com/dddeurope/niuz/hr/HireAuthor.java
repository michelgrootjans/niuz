package com.dddeurope.niuz.hr;

public class HireAuthor {
    private final String authorId;
    private final String authorName;
    private final String contractType;
    private final int rate;
    private final String bankAccount;

    public HireAuthor(String authorId, String authorName, String contractType, int rate, String bankAccount) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.contractType = contractType;
        this.rate = rate;
        this.bankAccount = bankAccount;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContractType() {
        return contractType;
    }

    public int getRate() {
        return rate;
    }

    public String getBankAccount() {
        return bankAccount;
    }
}
