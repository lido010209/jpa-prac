package com.example.jpa.q1.repo;

import com.example.jpa.q1.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository
extends JpaRepository<Article, Long> {

}
