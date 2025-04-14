package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.ProveedorDTO;
import com.inventorysystem_project.entities.Proveedor;
import com.inventorysystem_project.serviceinterfaces.IProveedorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody ProveedorDTO dto) {
        ModelMapper m = new ModelMapper();
        Proveedor proveedor = m.map(dto, Proveedor.class);
        proveedorService.insert(proveedor);
    }

    @GetMapping("Listar")
    public List<ProveedorDTO> listar() {
        return proveedorService.list().stream().map(proveedor -> {
            ProveedorDTO dto = new ProveedorDTO();
            dto.setId(proveedor.getId());
            dto.setNombre(proveedor.getNombre());
            dto.setDireccion(proveedor.getDireccion());
            dto.setActivo(proveedor.getActivo());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProveedorDTO listarPorId(@PathVariable("id") Long id) {
        Proveedor proveedor = proveedorService.listId(id);
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setDireccion(proveedor.getDireccion());
        dto.setActivo(proveedor.getActivo());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        proveedorService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody ProveedorDTO dto) {
        ModelMapper m = new ModelMapper();
        Proveedor proveedor = m.map(dto, Proveedor.class);
        proveedorService.insert(proveedor);
    }
}

