package com.example.jpa.q3;

import com.example.jpa.q3.model.Item;
import com.example.jpa.q3.model.Shop;
import com.example.jpa.q3.repo.ItemRepository;
import com.example.jpa.q3.repo.ShopRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("item")
public class ItemController {
    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    public ItemController(ItemRepository itemRepository, ShopRepository shopRepository) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
    }

    @GetMapping
    public String readAllItems(Model model){
        model.addAttribute("listShop", shopRepository.findAll());
        model.addAttribute("listItem", itemRepository.findAll());
        return "item/home.html";
    }
    @GetMapping("{id}")
    public String readOneItem(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("listShop", shopRepository.findAll());
        model.addAttribute("item", itemRepository.findById(id).orElse(null));
        return "item/read.html";
    }

    @GetMapping("/create")
    public String createView(Model model){
        model.addAttribute("listShop", shopRepository.findAll());
        return "item/create.html";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("price")
            String price,
            @RequestParam("shopId")
            Long shopId){
        Item newItem = new Item();
        newItem.setName(name); newItem.setPrice(price);
        newItem.setShop(shopRepository.findById(shopId).orElseThrow());
        itemRepository.save(newItem);
        return String.format("redirect:/item/%d",newItem.getId());
    }

    //Update
    @GetMapping("{id}/update")
    public String updateView(@PathVariable("id") Long id, Model model){
        model.addAttribute("listShop", shopRepository.findAll());
        model.addAttribute("item", itemRepository.findById(id).orElseThrow());
        return "item/update.html";

    }
    @PostMapping("{id}/update")
    public String create(@PathVariable("id") Long id,
                         @RequestParam("name")
                         String name,
                         @RequestParam("price")
                             String price,
                         @RequestParam("shopId")
                             Long shopId){
        Item newItem = itemRepository.findById(id).orElseThrow();
        newItem.setName(name); newItem.setPrice(price);
        newItem.setShop(shopRepository.findById(shopId).orElseThrow());
        itemRepository.save(newItem);
        return String.format("redirect:/item/%d",id);
    }

    //Delete
    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable("id") Long id, Model model){
        model.addAttribute("listShop", shopRepository.findAll());
        model.addAttribute("item", itemRepository.findById(id).orElseThrow());
        return "item/delete.html";
    }
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id){
        itemRepository.deleteById(id);
        return "redirect:/item";
    }
}
