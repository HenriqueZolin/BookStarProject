package com.example.bookstar.dao;

import com.example.bookstar.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
    List<Book> findByUsuarioId(Integer id);
}
