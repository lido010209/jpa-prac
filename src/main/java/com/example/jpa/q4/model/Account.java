package com.example.jpa.q4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String fullName;
    @Setter
    private String username;
    @Setter
    private String email;
    @Setter
    private String password;
    @Setter
    private String address;
    @Setter
    private String phone;

    public Account(String fullName, String username, String email, String password, String address, String phone){
        this.fullName=fullName;
        this.username=username;
        this.email=email;
        this.password=password;
        this.address=address;
        this.phone=phone;
    }
}
