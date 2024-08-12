package com.example.jpa.q2;

import com.example.jpa.q2.model.Author;
import com.example.jpa.q2.repo.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("author")
public class AuthorController {
    private final AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String readAllAuthors(Model model){
        model.addAttribute("listAuthor", repository.findAll());
        return "author/home.html";
    }

    @GetMapping("{id}")
    public String readOneAuthor(@PathVariable("id") Long id, Model model){
        model.addAttribute("author", repository.findById(id).orElseThrow());
        return "author/read.html";
    }

    @GetMapping("create")
    public String createView(){
        return "author/create.html";
    }
    @PostMapping("create")
    public String createAuthor(
            @RequestParam("name")
                             String name,
                         @RequestParam("debutYear")
                             Integer debutYear){
        Author newAuthor = new Author();
        newAuthor.setName(name); newAuthor.setDebutYear(debutYear);
        repository.save(newAuthor);
        return String.format("redirect:/author/%d", newAuthor.getId());
    }

    @GetMapping("{id}/update")
    public String updateView(@PathVariable("id") Long id, Model model){
        model.addAttribute("author", repository.findById(id).orElseThrow());
        return "author/update.html";
    }
    @PostMapping("{id}/update")
    public String updateAuthor(
            @PathVariable("id") Long id,
            @RequestParam("name")
            String name,
            @RequestParam("debutYear")
            Integer debutYear
    ){
        Author newAuthor = repository.findById(id).orElseThrow();
        newAuthor.setName(name); newAuthor.setDebutYear(debutYear);
        repository.save(newAuthor);
        return String.format("redirect:/author/%d", id);
    }

    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable("id") Long id, Model model){
        model.addAttribute("author", repository.findById(id).orElseThrow());
        return "author/delete.html";
    }
    @PostMapping("{id}/delete")
    public String deleteAuthor(@PathVariable("id") Long id){
        repository.deleteById(id);
        return "redirect:/author";
    }
}
