package com.example.jpa.q3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;

    @ManyToOne
    private Shop shop;
    @OneToMany(mappedBy = "item")
    private List<Option> listOption;
}
