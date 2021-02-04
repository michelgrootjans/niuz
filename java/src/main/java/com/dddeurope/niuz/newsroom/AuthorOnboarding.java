package com.dddeurope.niuz.newsroom;

import com.dddeurope.niuz.events.ContractSigned;
import com.dddeurope.niuz.events.Topic;

public class AuthorOnboarding {
    private final AuthorRepository authors;

    public AuthorOnboarding(AuthorRepository authors, Topic topic) {
        this.authors = authors;
        topic.subscribe(ContractSigned.class, this::onboard);
    }

    private void onboard(ContractSigned event) {
        authors.save(new Author(event.getAuthorId(), event.getAuthorName()));
    }
}
