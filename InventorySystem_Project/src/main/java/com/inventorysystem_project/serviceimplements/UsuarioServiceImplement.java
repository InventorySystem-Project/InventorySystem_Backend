package com.inventorysystem_project.serviceimplements;

import com.inventorysystem_project.entities.Usuario;
import com.inventorysystem_project.repositories.UsuarioRepository;
import com.inventorysystem_project.serviceinterfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    // Instancia del codificador de contraseñas
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void insert(Usuario user) {
        // Encriptar la contraseña antes de guardarla
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);  // Setea la contraseña encriptada

        userRepository.save(user);  // Guarda el usuario con la contraseña encriptada
    }

    @Override
    public List<Usuario> list() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Usuario listId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
