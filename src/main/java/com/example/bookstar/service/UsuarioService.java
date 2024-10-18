package com.example.bookstar.service;

import com.example.bookstar.dao.UsuarioDao;
import com.example.bookstar.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioDao usuarioDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Inicializa o encoder aqui
    }

    public ResponseEntity<Usuario> registerUsuario(Usuario usuario) {
        if(usuarioDao.findByUsername(usuario.getUsername()) == null ){
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioDao.save(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>()); // Adicione roles se necessário
    }
}
