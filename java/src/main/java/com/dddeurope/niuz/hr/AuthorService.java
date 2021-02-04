package com.dddeurope.niuz.hr;

import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.EventPublisher;

public class AuthorService {
    private final EventPublisher publisher;

    public AuthorService(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void hire(String authorId, String authorName, String bankAccount, String contractType, int rate) {
        HireAuthor command = new HireAuthor(authorId, authorName, contractType, rate, bankAccount);
        publisher.publish(new ContractSigned(command.getAuthorId(), command.getContractType(), command.getRate(), command.getBankAccount(), command.getAuthorName()));
    }
}
