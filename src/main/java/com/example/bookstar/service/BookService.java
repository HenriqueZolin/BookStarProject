package com.example.bookstar.service;

import com.example.bookstar.dao.BookDao;
import com.example.bookstar.dao.UsuarioDao;
import com.example.bookstar.model.Book;
import com.example.bookstar.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private UsuarioDao usuarioDao;

    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            return new ResponseEntity<>(bookDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Book> getBookById(int id) {
        try {
            return new ResponseEntity<>(bookDao.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Book> addBook(Book book) {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Buscar o usuário no banco de dados usando o username
            Usuario user = usuarioDao.findByUsername(username);

            // Associar o usuário ao livro
            book.setUsuario(user);

            bookDao.save(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Book> updateBook(int id, Book book) {
        try {
            Book bookFromDb = bookDao.findById(id).get();
            bookFromDb.setTitle(book.getTitle());
            bookFromDb.setAuthor(book.getAuthor());
            bookFromDb.setGenre(book.getGenre());
            bookFromDb.setRate(book.getRate());
            bookFromDb.setResume(book.getResume());
            bookDao.save(bookFromDb);
            return new ResponseEntity<>(bookFromDb, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteBook(int id) {
        try {
            bookDao.deleteById(id);
            return new ResponseEntity<>("Book deleted", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Can´t find ID", HttpStatus.BAD_REQUEST);
    }

    public List<Book> getMyBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario usuario = usuarioDao.findByUsername(username);

        return bookDao.findByUsuarioId(usuario.getId());
    }
}
