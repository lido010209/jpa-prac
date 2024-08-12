package com.example.jpa.q2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Author {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer debutYear;

    @OneToMany(mappedBy = "author")
    private List<Book> listBook;
}
