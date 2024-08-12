package com.example.jpa.q3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String homePage;

    @OneToMany(mappedBy = "shop")
    private List<Item> listItem;

}
