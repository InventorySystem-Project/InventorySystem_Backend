package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.UsuarioDTO;
import com.inventorysystem_project.entities.Usuario;
import com.inventorysystem_project.serviceinterfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private IUsuarioService userService;

    @PostMapping("Registrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registrar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario user = m.map(dto, Usuario.class);
        userService.insert(user);
    }

    @GetMapping("Listar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UsuarioDTO> listar() {
        return userService.list().stream().map(user -> {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            dto.setEnabled(user.getEnabled());
            dto.setRoles(user.getRoles().stream().map(r -> r.getRol()).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UsuarioDTO listarPorId(@PathVariable("id") Long id) {
        Usuario user = userService.listId(id);
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEnabled(user.getEnabled());
        dto.setRoles(user.getRoles().stream().map(r -> r.getRol()).collect(Collectors.toList()));
        return dto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario user = m.map(dto, Usuario.class);
        userService.insert(user);
    }
}