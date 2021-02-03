package com.dddeurope.niuz.finance;

public class Contract {
    private final String authorId;
    private final String contractType;
    private final Integer rate;
    private final String bankAccount;
    private final String authorName;

    public Contract(String authorId, String contractType, Integer rate, String bankAccount, String authorName) {

        this.authorId = authorId;
        this.contractType = contractType;
        this.rate = rate;
        this.bankAccount = bankAccount;
        this.authorName = authorName;
    }

    public boolean IsOwnedBy(String authorId) {
        return this.authorId.equals(authorId);
    }

    public Payment GeneratePayment(String description) {
        return new Payment(rate, bankAccount, authorName, description);
    }

    public boolean paysBySubmission() {
        return contractType.equals("pay-by-submission");
    }

    public boolean paysByPublication() {
        return contractType.equals("pay-by-publication");
    }
}
