package com.example.bookstar.dao;

import com.example.bookstar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
