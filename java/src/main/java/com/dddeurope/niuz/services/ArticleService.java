package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.repositories.ArticleRepository;

public class ArticleService {
    private final ArticleRepository articles;

    public ArticleService(ArticleRepository articles) {
        this.articles = articles;
    }

    public void save(String articleId, String authorId, String headline) {
        articles.save(new Article(articleId, authorId, headline));
    }
}
