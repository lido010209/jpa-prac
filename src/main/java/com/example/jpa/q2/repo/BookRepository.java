package com.example.jpa.q2.repo;

import com.example.jpa.q2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository
extends JpaRepository<Book, Long> {

}
