package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.ProveedorMateriaPrimaDTO;
import com.inventorysystem_project.entities.ProveedorMateriaPrima;
import com.inventorysystem_project.serviceinterfaces.IProveedorMateriaPrimaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proveedores-materias-primas")
public class ProveedorMateriaPrimaController {

    @Autowired
    private IProveedorMateriaPrimaService proveedorMateriaPrimaService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody ProveedorMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        ProveedorMateriaPrima proveedorMateriaPrima = m.map(dto, ProveedorMateriaPrima.class);
        proveedorMateriaPrimaService.insert(proveedorMateriaPrima);
    }

    @GetMapping("Listar")
    public List<ProveedorMateriaPrimaDTO> listar() {
        return proveedorMateriaPrimaService.list().stream().map(proveedorMateria -> {
            ProveedorMateriaPrimaDTO dto = new ProveedorMateriaPrimaDTO();
            dto.setId(proveedorMateria.getId());
            dto.setProveedorId(proveedorMateria.getProveedor().getId());
            dto.setMateriaPrimaId(proveedorMateria.getMateriaPrima().getId());
            dto.setPrecio(proveedorMateria.getPrecio());
            dto.setActivo(proveedorMateria.getActivo());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProveedorMateriaPrimaDTO listarPorId(@PathVariable("id") Long id) {
        ProveedorMateriaPrima proveedorMateriaPrima = proveedorMateriaPrimaService.listId(id);
        ProveedorMateriaPrimaDTO dto = new ProveedorMateriaPrimaDTO();
        dto.setId(proveedorMateriaPrima.getId());
        dto.setProveedorId(proveedorMateriaPrima.getProveedor().getId());
        dto.setMateriaPrimaId(proveedorMateriaPrima.getMateriaPrima().getId());
        dto.setPrecio(proveedorMateriaPrima.getPrecio());
        dto.setActivo(proveedorMateriaPrima.getActivo());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        proveedorMateriaPrimaService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody ProveedorMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        ProveedorMateriaPrima proveedorMateriaPrima = m.map(dto, ProveedorMateriaPrima.class);
        proveedorMateriaPrimaService.insert(proveedorMateriaPrima);
    }
}

