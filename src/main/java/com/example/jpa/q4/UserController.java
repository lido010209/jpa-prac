package com.example.jpa.q4;

import com.example.jpa.q4.model.Account;
import com.example.jpa.q4.repo.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("lu")
public class UserController {
    private final AccountRepository accountRepo;
    private final ServiceUser serviceUser;
    public UserController(AccountRepository accountRepo, ServiceUser serviceUser) {
        this.accountRepo = accountRepo;
        this.serviceUser = serviceUser;
    }

    @GetMapping
    public String home(){
        return "user/home.html";
    }


    //Sign in
    @GetMapping("/sign-in")
    public String createView(){
        return "user/createAccount.html";
    }
    @PostMapping("/sign-in")
    public String createAccount(
            @RequestParam("fullName")
            String fullName,
            @RequestParam("username")
            String username,
            @RequestParam("email")
            String email,
            @RequestParam("password")
            String password,
            @RequestParam("address")
            String address,
            @RequestParam("phone")
            String phone
    ){
        Account account= new Account(fullName, username, email, password, address, phone);
        System.out.println("실행되나?");
        accountRepo.save(account);
        return "redirect:/lu/login";
    }

    //Page login
    @GetMapping("/login")
    public String loginAccount(){
        return "user/login.html";
    }
    @PostMapping("login")
    public String login(
            @RequestParam("username")
            String username,
            @RequestParam("password")
            String password
    ){
        Long userId = serviceUser.findIdByUsernameAndPassword(username, password);
        return String.format("redirect:/lu/%s", userId);
    }

    //After login
    @GetMapping("{id}")
    public String user(@PathVariable("id") Long id,  Model model){
        model.addAttribute("account", accountRepo.findById(id).orElseThrow());
        return "user/home-user.html";
    }

    //Update account
    @GetMapping("{id}/edit-account")
    public String updateDeleteAccount(@PathVariable("id") Long id, Model model){
        model.addAttribute("account", accountRepo.findById(id).orElseThrow());
        return "user/updateDeleteAccount.html";
    }

    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("fullName")
            String fullName,
            @RequestParam("username")
            String username,
            @RequestParam("email")
            String email,
            @RequestParam("password")
            String password,
            @RequestParam("address")
            String address,
            @RequestParam("phone")
            String phone
    ){
        Account account= accountRepo.findById(id).orElseThrow();
        account.setFullName(fullName); account.setUsername(username); account.setEmail(email);
        account.setPassword(password); account.setAddress(address); account.setPhone(phone);
        accountRepo.save(account);
        return String.format("redirect:/lu/%s",id);
    }

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable("id")
            Long id
    ){
        accountRepo.deleteById(id);
        return "redirect:/lu";
    }


}
