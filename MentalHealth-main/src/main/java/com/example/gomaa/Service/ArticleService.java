package com.example.gomaa.Service;

import com.example.gomaa.Repository.ArticleRepository;
import com.example.gomaa.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public List<Article> getAllArticles() {
        return repository.findAll();
    }

    public Article saveArticle(Article article) {
        return repository.save(article);
    }
}
