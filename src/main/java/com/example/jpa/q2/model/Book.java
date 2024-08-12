package com.example.jpa.q2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Setter
    private String title;
    private String summary;
    private Double rating;

    @ManyToOne
    private Author author;

}
