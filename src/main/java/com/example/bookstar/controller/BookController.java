package com.example.bookstar.controller;

import com.example.bookstar.model.Book;
import com.example.bookstar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }

    @PostMapping("addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PatchMapping("updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }

    @GetMapping("myBooks")
    public List<Book> getMyBooks(){
        return bookService.getMyBooks();
    }
}
