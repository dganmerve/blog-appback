package com.md.blogapp.service;

import com.md.blogapp.entity.Article;
import com.md.blogapp.exception.ArticleNotFoundException;
import com.md.blogapp.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    @Autowired
    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }
    public List<Article> getAllarticles(){
        return articleRepository.findAll();
    }
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    // Yeni bir makale oluşturma
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }
    public Article updateArticle(Long id, Article updatedArticle) {
        Optional<Article> existingArticle = articleRepository.findById(id);

        if (existingArticle.isPresent()) {
            Article article = existingArticle.get();
            article.setTitle(updatedArticle.getTitle());
            article.setContent(updatedArticle.getContent());
            return articleRepository.save(article);
        } else {
            throw new ArticleNotFoundException("Makale bulunamadı: " + id);
        }
    }

    // Bir makaleyi silme
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
