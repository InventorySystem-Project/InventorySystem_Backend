package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.ProductoTerminadoDTO;
import com.inventorysystem_project.entities.ProductoTerminado;
import com.inventorysystem_project.serviceinterfaces.IProductoTerminadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos-terminados")
public class ProductoTerminadoController {

    @Autowired
    private IProductoTerminadoService productoTerminadoService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody ProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        ProductoTerminado productoTerminado = m.map(dto, ProductoTerminado.class);
        productoTerminadoService.insert(productoTerminado);
    }

    @GetMapping("Listar")
    public List<ProductoTerminadoDTO> listar() {
        return productoTerminadoService.list().stream().map(productoTerminado -> {
            ProductoTerminadoDTO dto = new ProductoTerminadoDTO();
            dto.setId(productoTerminado.getId());
            dto.setNombre(productoTerminado.getNombre());
            dto.setDescripcion(productoTerminado.getDescripcion());
            dto.setTipo(productoTerminado.getTipo());
            dto.setModelo(productoTerminado.getModelo());
            dto.setColor(productoTerminado.getColor());
            dto.setPrecioUnitario(productoTerminado.getPrecioUnitario());
            dto.setPieza(productoTerminado.getPieza());
            dto.setEnabled(productoTerminado.getEnabled());
            dto.setImagen(productoTerminado.getImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductoTerminadoDTO listarPorId(@PathVariable("id") Long id) {
        ProductoTerminado productoTerminado = productoTerminadoService.listId(id);
        ProductoTerminadoDTO dto = new ProductoTerminadoDTO();
        dto.setId(productoTerminado.getId());
        dto.setNombre(productoTerminado.getNombre());
        dto.setDescripcion(productoTerminado.getDescripcion());
        dto.setTipo(productoTerminado.getTipo());
        dto.setModelo(productoTerminado.getModelo());
        dto.setColor(productoTerminado.getColor());
        dto.setPrecioUnitario(productoTerminado.getPrecioUnitario());
        dto.setPieza(productoTerminado.getPieza());
        dto.setEnabled(productoTerminado.getEnabled());
        dto.setImagen(productoTerminado.getImagen());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        productoTerminadoService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody ProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        ProductoTerminado productoTerminado = m.map(dto, ProductoTerminado.class);
        productoTerminadoService.insert(productoTerminado);
    }
}
