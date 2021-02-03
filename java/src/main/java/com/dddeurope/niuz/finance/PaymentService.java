package com.dddeurope.niuz.finance;

import com.dddeurope.niuz.events.ArticlePublished;
import com.dddeurope.niuz.events.ArticleSubmitted;
import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.Topic;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private final ContractRepository contracts;
    private final PaymentRepository payments;

    public PaymentService(ContractRepository contracts, PaymentRepository payments, Topic topic) {
        this.contracts = contracts;
        this.payments = payments;
        topic.subscribe(ContractSigned.class, this::createContract);
        topic.subscribe(ArticleSubmitted.class, this::pay);
        topic.subscribe(ArticlePublished.class, this::pay);
    }

    private void createContract(ContractSigned event) {
        contracts.add(new Contract(event.getAuthorId(), event.getContractType(), event.getRate(), event.getBankAccount(), event.getAuthorName()));
    }

    private void pay(ArticleSubmitted event) {
        for (Contract contract : contracts.ownedBy(event.getAuthorId())) {
            if(contract.paysBySubmission()) payments.save(contract.GeneratePayment(event.getHeadline()));
        }
    }

    private void pay(ArticlePublished event) {
        for (Contract contract : contracts.ownedBy(event.getAuthorId())) {
            if(contract.paysByPublication()) payments.save(contract.GeneratePayment(event.getHeadline()));
        }
    }

    public List<PaymentDto> getByBankAccount(String bankAccount) {
        return payments.getByBankAccount(bankAccount).stream()
                       .map(this::map)
                       .collect(Collectors.toList());
    }

    private PaymentDto map(Payment payment) {
        return new PaymentDto(payment.getAmount(), payment.getBankAccount(), payment.getRecipientName(), payment.getDescription());
    }
}
