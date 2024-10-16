package com.example.bookstar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String genre;
    private float rate;
    private String resume;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
}
