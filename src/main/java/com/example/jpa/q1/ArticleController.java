package com.example.jpa.q1;

import com.example.jpa.q1.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article")
public class ArticleController {
    private final CommentService commentService;
    private final ArticleService articleService;

    public ArticleController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @GetMapping("/create")
    public String createView(){
        return "article/create.html";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer
    ){
        Article newArticle = articleService.createArticle(title, content, writer);
        return String.format("redirect:/article/%d",newArticle.getId());
    }

    @GetMapping
    public String readAllArticles(Model model){
        model.addAttribute("articles", articleService.allArticle());
        return "article/home.html";
    }
    @GetMapping("{id}")
    public String readOneArticle(
            @PathVariable("id")
            Long id,
            Model model){
        model.addAttribute("article", articleService.oneArticle(id));
        return "article/read.html";
    }

    @GetMapping("/{id}/update")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model){
        model.addAttribute("article", articleService.oneArticle(id));
        return "article/update.html";
    }
    @PostMapping("/{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("writer")
            String writer
    ){
        articleService.updateArticle(id, title, content, writer);
        return String.format("redirect:/article/%d",id);
    }

    @GetMapping("/{id}/delete")
    public String deleteView(
            @PathVariable("id")
            Long id,
            Model model){
        model.addAttribute("article", articleService.oneArticle(id));
        return "article/delete.html";
    }
    @PostMapping("/{id}/delete")
    public String update(
            @PathVariable("id")
            Long id
    ){
        articleService.delete(id);
        return "redirect:/article";
    }
}
