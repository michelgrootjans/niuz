package com.dddeurope.niuz.finance;

import com.dddeurope.niuz.events.ArticlePublished;
import com.dddeurope.niuz.events.ArticleSubmitted;
import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private final PaymentRepository payments;
    private final ArrayList<Contract> contracts;

    public PaymentService(PaymentRepository payments, Topic topic) {
        this.payments = payments;
        this.contracts = new ArrayList<>();
        topic.subscribe(ContractSigned.class, this::createContract);
        topic.subscribe(ArticleSubmitted.class, this::pay);
        topic.subscribe(ArticlePublished.class, this::pay);
    }

    private void createContract(ContractSigned event) {
        contracts.add(new Contract(event.getAuthorId(), event.getContractType(), event.getRate(), event.getBankAccount(), event.getAuthorName()));
    }

    private void pay(ArticleSubmitted event) {
        for (Contract contract : contractsFor(event.getAuthorId())) {
            if(contract.paysBySubmission()) payments.save(contract.GeneratePayment(event.getHeadline()));
        }
    }

    private void pay(ArticlePublished event) {
        for (Contract contract : contractsFor(event.getAuthorId())) {
            if(contract.paysByPublication()) payments.save(contract.GeneratePayment(event.getHeadline()));
        }
    }

    private List<Contract> contractsFor(String authorId) {
        return this.contracts.stream()
                             .filter(c -> c.IsOwnedBy(authorId))
                             .collect(Collectors.toList());
    }

    public List<PaymentDto> getByBankAccount(String bankAccount) {
        return payments.getByBankAccount(bankAccount).stream()
                       .map(this::map)
                       .collect(Collectors.toList());
    }

    private PaymentDto map(Payment payment) {
        return new PaymentDto(payment.getAmount(), payment.getBankAccount(), payment.getRecipientName(), payment.getDescription());
    }

    private class Contract {
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
}
