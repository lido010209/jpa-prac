package com.example.jpa.q1;

import com.example.jpa.q1.model.Article;
import com.example.jpa.q1.repo.ArticleRepository;
import com.example.jpa.q1.repo.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }


    public Article createArticle(String title, String content, String writer){
        Article newArticle= new Article();
        newArticle.setTitle(title); newArticle.setContent(content); newArticle.setWriter(writer);
        articleRepository.save(newArticle);
        return newArticle;
    }
    public List<Article> allArticle(){
        return articleRepository.findAll();
    }
    public Article oneArticle(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public void updateArticle(Long id, String title, String content, String writer){
        Article newArticle = this.oneArticle(id);
        newArticle.setTitle(title); newArticle.setContent(content); newArticle.setWriter(writer);
        articleRepository.save(newArticle);
    }
    public void delete(Long id){
        Article article= this.oneArticle(id);
        articleRepository.delete(article);
    }
}
