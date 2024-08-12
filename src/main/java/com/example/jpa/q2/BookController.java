package com.example.jpa.q2;

import com.example.jpa.q2.model.Author;
import com.example.jpa.q2.model.Book;
import com.example.jpa.q2.repo.AuthorRepository;
import com.example.jpa.q2.repo.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String readAllBooks(Model model){
        model.addAttribute("listAuthor", authorRepository.findAll());
        model.addAttribute("listBook", bookRepository.findAll());
        return "book/home.html";
    }

    @GetMapping("{id}")
    public String readOneBook(@PathVariable("id") Long id, Model model){
        model.addAttribute("listAuthor", authorRepository.findAll());
        model.addAttribute("book", bookRepository.findById(id).orElseThrow());
        return "book/read.html";
    }

    @GetMapping("create")
    public String createView(Model model){
        model.addAttribute("listAuthor", authorRepository.findAll());
        return "book/create.html";
    }
    @PostMapping("create")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("summary")
            String summary,
            @RequestParam("rating")
            Double rating,
            @RequestParam("authorId")
            Long authorId
    ){
        Book newBook= new Book();
        newBook.setTitle(title); newBook.setSummary(summary); newBook.setRating(rating);
        newBook.setAuthor(authorRepository.findById(authorId).orElseThrow());
        bookRepository.save(newBook);
        return String.format("redirect:/book/%d", newBook.getId());
    }

    //Update
    @GetMapping("{id}/update")
    public String updateView(@PathVariable("id") Long id, Model model){
        model.addAttribute("listAuthor", authorRepository.findAll());
        model.addAttribute("book", bookRepository.findById(id).orElseThrow());
        return "book/update.html";
    }
    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id") Long id,
            @RequestParam("title")
            String title,
            @RequestParam("summary")
            String summary,
            @RequestParam("rating")
            Double rating,
            @RequestParam("authorId")
            Long authorId
    ){
        Book newBook= bookRepository.findById(id).orElseThrow();
        newBook.setTitle(title); newBook.setSummary(summary); newBook.setRating(rating);
        newBook.setAuthor(authorRepository.findById(authorId).orElseThrow());
        bookRepository.save(newBook);
        return String.format("redirect:/book/%d", id);
    }

    //Delete
    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable("id") Long id, Model model){
        model.addAttribute("listAuthor", authorRepository.findAll());
        model.addAttribute("book", bookRepository.findById(id).orElseThrow());
        return "book/delete.html";
    }
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id){
        bookRepository.deleteById(id);
        return "redirect:/book";
    }

}
