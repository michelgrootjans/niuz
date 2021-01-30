package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.entities.Author;
import com.dddeurope.niuz.entities.Payment;
import com.dddeurope.niuz.repositories.ArticleRepository;
import com.dddeurope.niuz.repositories.AuthorRepository;
import com.dddeurope.niuz.repositories.PaymentRepository;

public class ArticleService {
    private final AuthorRepository authors;
    private final ArticleRepository articles;
    private final PaymentRepository payments;

    public ArticleService(AuthorRepository authors, ArticleRepository articles, PaymentRepository payments) {
        this.authors = authors;
        this.articles = articles;
        this.payments = payments;
    }

    public void submit(String articleId, String authorId, String headline) {
        articles.save(new Article(articleId, authorId, headline));

        Author author = authors.getByAuthorId(authorId);
        if (author.paysBySubmission()) {
            payments.save(new Payment(author.getRate(), author.getBankAccount(), author.getName(), headline));
        }
    }
}
