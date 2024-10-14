package com.example.bookstar.controller;

import com.example.bookstar.model.Book;
import com.example.bookstar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("allBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        return bookService.getAllBooks();
    }
}
