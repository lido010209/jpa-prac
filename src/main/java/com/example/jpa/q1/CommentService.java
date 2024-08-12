package com.example.jpa.q1;

import com.example.jpa.q1.model.Comment;
import com.example.jpa.q1.repo.ArticleRepository;
import com.example.jpa.q1.repo.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public Comment createComment(String content, String writer, Long articleId){
        Comment comment = new Comment();
        comment.setWriter(writer); comment.setContent(content); comment.setArticle(articleRepository.findById(articleId).orElse(null));
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> allComments(){
        return commentRepository.findAll();
    }
    public Comment oneComment(Long id){
        return commentRepository.findById(id).orElseThrow();
    }

    public void updateComment(Long id, String content, String writer, Long articleId){
        Comment comment= this.oneComment(id);
        comment.setWriter(writer); comment.setContent(content); comment.setArticle(articleRepository.findById(articleId).orElse(null));
        commentRepository.save(comment);
    }
    public void deleteComment(Long id){
        commentRepository.delete(this.oneComment(id));
    }
}
