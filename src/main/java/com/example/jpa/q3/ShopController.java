package com.example.jpa.q3;

import com.example.jpa.q3.model.Shop;
import com.example.jpa.q3.repo.ShopRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("shop")
public class ShopController {
    private final ShopRepository repository;

    public ShopController(ShopRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String readAllShops(Model model){
        model.addAttribute("listShop", repository.findAll());
        return "shop/home.html";
    }
    @GetMapping("{id}")
    public String readOneShop(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("shop", repository.findById(id).orElse(null));
        return "shop/read.html";
    }

    @GetMapping("/create")
    public String createView(){
        return "shop/create.html";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("homePage")
            String homePage){
        Shop newShop= new Shop();
        newShop.setName(name); newShop.setHomePage(homePage);
        repository.save(newShop);
        return String.format("redirect:/shop/%d",newShop.getId());
    }

    //Update
    @GetMapping("{id}/update")
    public String updateView(@PathVariable("id") Long id, Model model){
        model.addAttribute("shop", repository.findById(id).orElseThrow());
        return "shop/update.html";

    }
    @PostMapping("{id}/update")
    public String create(@PathVariable("id") Long id,
                         @RequestParam("name")
            String name,
            @RequestParam("homePage")
            String homePage){
        Shop newShop= repository.findById(id).orElseThrow();
        newShop.setName(name); newShop.setHomePage(homePage);
        repository.save(newShop);
        return String.format("redirect:/shop/%d",id);
    }

    //Delete
    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable("id") Long id, Model model){
        model.addAttribute("shop", repository.findById(id).orElseThrow());
        return "shop/delete.html";

    }
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id){
        repository.deleteById(id);
        return "redirect:/shop";
    }
}
