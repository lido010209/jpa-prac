package com.example.jpa.q3;

import com.example.jpa.q3.model.Item;
import com.example.jpa.q3.model.Option;
import com.example.jpa.q3.repo.ItemRepository;
import com.example.jpa.q3.repo.OptionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("option")
public class OptionController {
    private final OptionRepository optionRepository;
    private final ItemRepository itemRepository;

    public OptionController(OptionRepository optionRepository, ItemRepository itemRepository) {
        this.optionRepository = optionRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String readAllOptions(Model model){
        model.addAttribute("listItem", itemRepository.findAll());
        model.addAttribute("listOption", optionRepository.findAll());
        return "option/home.html";
    }
    @GetMapping("{id}")
    public String readOneOption(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("listItem", itemRepository.findAll());
        model.addAttribute("option", optionRepository.findById(id).orElse(null));
        return "option/read.html";
    }

    @GetMapping("/create")
    public String createView(Model model){
        model.addAttribute("listItem", itemRepository.findAll());
        model.addAttribute("listOption", optionRepository.findAll());
        return "option/create.html";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("desc")
            String desc,
            @RequestParam("addPrice")
            Integer addPrice,
            @RequestParam("itemId")
            Long itemId){
        Option newOption = new Option();
        newOption.setDesc(desc); newOption.setAddPrice(addPrice);
        newOption.setItem(itemRepository.findById(itemId).orElseThrow());
        return String.format("redirect:/option/%d",newOption.getId());
    }

    //Update
    @GetMapping("{id}/update")
    public String updateView(@PathVariable("id") Long id, Model model){
        model.addAttribute("listItem", itemRepository.findAll());
        model.addAttribute("option", optionRepository.findById(id).orElseThrow());
        return "option/update.html";

    }
    @PostMapping("{id}/update")
    public String create(@PathVariable("id") Long id,
                         @RequestParam("desc")
                         String desc,
                         @RequestParam("addPrice")
                             Integer addPrice,
                         @RequestParam("itemId")
                             Long itemId){
        Option newOption = optionRepository.findById(id).orElseThrow();
        newOption.setDesc(desc); newOption.setAddPrice(addPrice);
        newOption.setItem(itemRepository.findById(itemId).orElseThrow());
        return String.format("redirect:/option/%d",id);
    }

    //Delete
    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable("id") Long id, Model model){
        model.addAttribute("listItem", itemRepository.findAll());
        model.addAttribute("option", optionRepository.findById(id).orElseThrow());
        return "option/delete.html";
    }
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id){
        optionRepository.deleteById(id);
        return "redirect:/option";
    }
}
