package com.example.jpa.q4;

import com.example.jpa.q4.repo.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ManageController {
    private final AccountRepository accountRepo;

    public ManageController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping("manage")
    public String home(Model model){
        model.addAttribute("listAccount", accountRepo.findAll());
        return "manage/home.html";
    }
    @GetMapping("manage/{id}")
    public String manageOneAccount(@PathVariable("id") Long id, Model model){
        model.addAttribute("account", accountRepo.findById(id).orElseThrow());
        return "manage/detail.html";
    }

//    @PostMapping("create-account")
//    public String create(
//            @RequestParam("fullName")
//            String fullName,
//            @RequestParam("username")
//            String username,
//            @RequestParam("email")
//            String email,
//            @RequestParam("password")
//            String password,
//            @RequestParam("address")
//            String address,
//            @RequestParam("phone")
//            String phone
//    ){
//        Account newAccount= new Account(fullName, username, email, password, address, phone);
//        accountRepo.save(newAccount);
//        return String.format("redirect:/manage-user/%s", newAccount.getId());
//    }

//    @PostMapping("{id}/update")
//    public String update(
//            @PathVariable("id")
//            Long id,
//            @RequestParam("fullName")
//            String fullName,
//            @RequestParam("username")
//            String username,
//            @RequestParam("email")
//            String email,
//            @RequestParam("password")
//            String password,
//            @RequestParam("address")
//            String address,
//            @RequestParam("phone")
//            String phone
//    ){
//        Account account= accountRepo.findById(id).orElseThrow();
//        account.setFullName(fullName); account.setUsername(username); account.setEmail(email);
//        account.setPassword(password); account.setAddress(address); account.setPhone(phone);
//        accountRepo.save(account);
//        return String.format("redirect:/manage-user/%s", id);
//    }

//    @PostMapping("{id}/delete")
//    public String deleteAccount(@PathVariable("id") Long id){
//        accountRepo.deleteById(id);
//        return "redirect:/manage-user";
//    }
}
