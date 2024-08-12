package com.example.jpa.q3.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String desc;
    private Integer addPrice;

    @ManyToOne
    private Item item;
}
