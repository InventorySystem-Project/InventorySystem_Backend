package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.UserDTO;
import com.inventorysystem_project.entities.Users;
import com.inventorysystem_project.serviceinterfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("Registrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registrar(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        Users user = m.map(dto, Users.class);
        userService.insert(user);
    }

    @GetMapping("Listar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDTO> listar() {
        return userService.list().stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setPassword(user.getPassword());
            dto.setEnabled(user.getEnabled());
            dto.setRoles(user.getRoles().stream().map(r -> r.getRol()).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO listarPorId(@PathVariable("id") Long id) {
        Users user = userService.listId(id);
        UserDTO dto = new UserDTO();
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
    public void modificar(@RequestBody UserDTO dto) {
        ModelMapper m = new ModelMapper();
        Users user = m.map(dto, Users.class);
        userService.insert(user);
    }
}