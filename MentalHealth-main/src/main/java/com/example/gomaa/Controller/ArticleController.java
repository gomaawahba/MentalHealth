package com.example.gomaa.Controller;

import com.example.gomaa.Service.ArticleService;
import com.example.gomaa.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/self-love/articles")
public class ArticleController {
    private final ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return service.getAllArticles();
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return service.saveArticle(article);
    }
}
