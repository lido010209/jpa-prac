package com.example.jpa.q1;

import com.example.jpa.q1.model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @GetMapping("/create")
    public String createView(Model model){
        model.addAttribute("articles", articleService.allArticle());
        return "comment/create.html";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("articleId")
            Long articleId
    ){
        Comment newComment= commentService.createComment(content, writer, articleId);
        return String.format("redirect:/comment/%d",newComment.getId());
    }

    @GetMapping
    public String readAllComments(Model model){
        model.addAttribute("commentList", commentService.allComments());
        model.addAttribute("articles", articleService.allArticle());
        return "comment/home.html";
    }
    @GetMapping("{id}")
    public String readOneComment(
            @PathVariable("id")
            Long id,
            Model model){
        model.addAttribute("articles", articleService.allArticle());
//        model.addAttribute("selectArticle", commentService.oneComment(id).getArticle().getId());
        model.addAttribute("comment", commentService.oneComment(id));
        return "comment/read.html";
    }

    @GetMapping("/{id}/update")
    public String updateView(
            @PathVariable("id")
                    Long id,
            Model model){
        model.addAttribute("articles", articleService.allArticle());
        model.addAttribute("comment", commentService.oneComment(id));
        return "comment/update.html";
    }
    @PostMapping("/{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer,
            @RequestParam("articleId")
            Long articleId
    ){
        commentService.updateComment(id, content, writer, articleId);
        return String.format("redirect:/comment/%d",id);
    }

    @GetMapping("/{id}/delete")
    public String deleteView(
            @PathVariable("id")
            Long id,
            Model model){
        model.addAttribute("articles", articleService.allArticle());
        model.addAttribute("comment", commentService.oneComment(id));
        return "comment/delete.html";
    }
    @PostMapping("/{id}/delete")
    public String update(
            @PathVariable("id")
            Long id
    ){
        commentService.deleteComment(id);
        return "redirect:/comment";
    }

}
