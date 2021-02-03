package com.dddeurope.niuz.events;

public class ContractSigned {
    private final String authorId;
    private final String contractType;
    private final Integer rate;
    private final String bankAccount;
    private final String authorName;

    public ContractSigned(String authorId, String contractType, Integer rate, String bankAccount, String authorName) {
        this.authorId = authorId;
        this.contractType = contractType;
        this.rate = rate;
        this.bankAccount = bankAccount;
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContractType() {
        return contractType;
    }

    public Integer getRate() {
        return rate;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getAuthorName() {
        return authorName;
    }
}
