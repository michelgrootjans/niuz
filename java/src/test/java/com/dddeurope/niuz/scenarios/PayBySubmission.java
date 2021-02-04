package com.dddeurope.niuz.scenarios;

import com.dddeurope.niuz.finance.PaymentDto;
import com.dddeurope.niuz.hr.HireAuthor;
import com.dddeurope.niuz.website.TeaserDto;
import com.dddeurope.niuz.fixtures.TestFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PayBySubmission {
    private TestFacade app;

    @BeforeEach
    void setUp() {
        app = new TestFacade();
    }

    @Test
    void submitArticle() {
        app.dispatch(new HireAuthor("author-1", "Freddy Kruger", "pay-by-submission", 50, "123-4567-89"));
        app.submit("article-1", "author-1", "headline");

        assertThat(app.get("homepage")).isEmpty();
        assertThat(app.getByBankAccount("123-4567-89")).containsExactly(
                new PaymentDto(50, "123-4567-89", "Freddy Kruger", "headline")
        );
    }

    @Test
    void publishArticle() {
        app.dispatch(new HireAuthor("author-1", "Freddy Kruger", "pay-by-submission", 50, "123-4567-89"));
        app.submit("article-1", "author-1", "headline");
        app.publish("article-1");

        assertThat(app.get("homepage")).containsExactly(
                new TeaserDto("headline", "Freddy Kruger")
        );
        assertThat(app.getByBankAccount("123-4567-89")).containsExactly(
                new PaymentDto(50, "123-4567-89", "Freddy Kruger", "headline")
        );
    }
}
