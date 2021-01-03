package com.dddeurope.niuz.services;

import com.dddeurope.niuz.entities.Article;
import com.dddeurope.niuz.repositories.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArticleServiceTest {
    private ArticleRepository articles;
    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        articles = mock(ArticleRepository.class);
        articleService = new ArticleService(articles);
    }

    @Test
    void submitArticle() {
        articleService.save("article-1", "author-1", "headline");
        verify(articles).save(new Article("article-1", "author-1", "headline"));
    }
}
