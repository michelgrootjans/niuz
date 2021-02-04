package com.dddeurope.niuz.hr;

import com.dddeurope.niuz.commands.CommandHandler;
import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.EventPublisher;

public class AuthorService implements CommandHandler<HireAuthor> {
    private final EventPublisher publisher;

    public AuthorService(EventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean canHandle(Object command) {
        return command instanceof HireAuthor;
    }

    @Override
    public void handle(HireAuthor command) {
        publisher.publish(new ContractSigned(command.getAuthorId(), command.getContractType(), command.getRate(), command.getBankAccount(), command.getAuthorName()));
    }
}
