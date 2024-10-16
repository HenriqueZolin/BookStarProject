package com.example.bookstar.service;

import com.example.bookstar.dao.UsuarioDao;
import com.example.bookstar.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<Usuario> registerUsuario(Usuario usuario) {
        if(usuarioDao.findByUsername(usuario.getUsername()).getId() > 0){
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioDao.save(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
