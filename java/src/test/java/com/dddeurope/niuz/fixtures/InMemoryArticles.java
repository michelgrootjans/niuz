package com.dddeurope.niuz.fixtures;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.repositories.ArticleRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryArticles implements ArticleRepository {
    final Map<String, Article> articles = new HashMap<>();

    @Override
    public void save(Article article) {
        articles.put(article.getId(), article);
    }

    @Override
    public Article getByArticleId(String articleId) {
        return articles.get(articleId);
    }}
