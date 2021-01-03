package com.dddeurope.niuz.repositories;

import com.dddeurope.niuz.entities.Article;

public interface ArticleRepository {
    void save(Article article);
    Article getByArticleId(String articleId);
}
