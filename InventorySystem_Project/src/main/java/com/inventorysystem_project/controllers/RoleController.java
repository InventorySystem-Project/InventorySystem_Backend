package com.inventorysystem_project.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.aaw.demo1_202302_si63.dtos.RolDTO;
import pe.edu.upc.aaw.demo1_202302_si63.entities.Role;
import pe.edu.upc.aaw.demo1_202302_si63.serviceinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRolService rolR;
    @PostMapping("Registrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registrar(@RequestBody RolDTO dto){
        ModelMapper m=new ModelMapper();
        Role d=m.map(dto, Role.class);
        rolR.insert(d);
    }
    @GetMapping("Listar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RolDTO> listar(){
        return rolR.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,RolDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("Eliminar/{id}")
    public void eliminar(@PathVariable("id")Integer id){
        rolR.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void modificar(@RequestBody RolDTO dto){
        ModelMapper m=new ModelMapper();
        Role d=m.map(dto, Role.class);
        rolR.insert(d);
    }
}

