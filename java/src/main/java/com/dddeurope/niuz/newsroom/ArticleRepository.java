package com.dddeurope.niuz.newsroom;

public interface ArticleRepository {
    void save(Article article);
    Article getByArticleId(String articleId);
}
