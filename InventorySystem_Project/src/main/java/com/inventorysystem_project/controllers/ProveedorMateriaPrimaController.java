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
@RequestMapping("/proveedor-materia-prima")
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
        return proveedorMateriaPrimaService.list().stream().map(proveedorMateriaPrima -> {
            ProveedorMateriaPrimaDTO dto = new ProveedorMateriaPrimaDTO();
            dto.setId(proveedorMateriaPrima.getId());
            dto.setMateriaPrimaId(proveedorMateriaPrima.getMateriaPrima().getId());
            dto.setProveedorId(proveedorMateriaPrima.getProveedor().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProveedorMateriaPrimaDTO listarPorId(@PathVariable("id") Long id) {
        ProveedorMateriaPrima proveedorMateriaPrima = proveedorMateriaPrimaService.listId(id);
        ProveedorMateriaPrimaDTO dto = new ProveedorMateriaPrimaDTO();
        dto.setId(proveedorMateriaPrima.getId());
        dto.setMateriaPrimaId(proveedorMateriaPrima.getMateriaPrima().getId());
        dto.setProveedorId(proveedorMateriaPrima.getProveedor().getId());
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
