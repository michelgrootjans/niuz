package com.dddeurope.niuz.hr;

import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.EventPublisher;

public class AuthorService {
    private final EventPublisher publisher;

    public AuthorService(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void hire(String authorId, String authorName, String bankAccount, String contractType, int rate) {
        publisher.publish(new ContractSigned(authorId, contractType, rate, bankAccount, authorName));
    }
}
