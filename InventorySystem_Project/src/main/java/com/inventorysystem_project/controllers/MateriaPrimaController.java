package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.MateriaPrimaDTO;
import com.inventorysystem_project.entities.MateriaPrima;
import com.inventorysystem_project.serviceinterfaces.IMateriaPrimaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materia-prima")
public class MateriaPrimaController {

    @Autowired
    private IMateriaPrimaService materiaPrimaService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody MateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        MateriaPrima materiaPrima = m.map(dto, MateriaPrima.class);
        materiaPrimaService.insert(materiaPrima);
    }

    @GetMapping("Listar")
    public List<MateriaPrimaDTO> listar() {
        return materiaPrimaService.list().stream().map(materiaPrima -> {
            MateriaPrimaDTO dto = new MateriaPrimaDTO();
            dto.setId(materiaPrima.getId());
            dto.setNombre(materiaPrima.getNombre());
            dto.setDescripcion(materiaPrima.getDescripcion());
            dto.setPrecioUnitario(materiaPrima.getPrecioUnitario());
            dto.setUnidad(materiaPrima.getUnidad());
            dto.setImagen(materiaPrima.getImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MateriaPrimaDTO listarPorId(@PathVariable("id") Long id) {
        MateriaPrima materiaPrima = materiaPrimaService.listId(id);
        MateriaPrimaDTO dto = new MateriaPrimaDTO();
        dto.setId(materiaPrima.getId());
        dto.setNombre(materiaPrima.getNombre());
        dto.setDescripcion(materiaPrima.getDescripcion());
        dto.setPrecioUnitario(materiaPrima.getPrecioUnitario());
        dto.setUnidad(materiaPrima.getUnidad());
        dto.setImagen(materiaPrima.getImagen());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        materiaPrimaService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody MateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        MateriaPrima materiaPrima = m.map(dto, MateriaPrima.class);
        materiaPrimaService.insert(materiaPrima);
    }
}
