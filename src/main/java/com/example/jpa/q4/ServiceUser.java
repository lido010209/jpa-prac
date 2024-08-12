package com.example.jpa.q4;

import com.example.jpa.q4.repo.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {
    private final AccountRepository repository;

    public ServiceUser(AccountRepository repository) {
        this.repository = repository;
    }

    public Long findIdByUsernameAndPassword(String username, String password){
        return repository.findByUsernameAndPassword(username, password)
                .orElseThrow().getId();
    }
}
