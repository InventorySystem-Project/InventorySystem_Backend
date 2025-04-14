package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.DetalleOrdenCompraDTO;
import com.inventorysystem_project.entities.DetalleOrdenCompra;
import com.inventorysystem_project.serviceinterfaces.IDetalleOrdenCompraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detalle-ordenes-compra")
public class DetalleOrdenCompraController {

    @Autowired
    private IDetalleOrdenCompraService detalleOrdenCompraService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody DetalleOrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();
        DetalleOrdenCompra detalleOrdenCompra = m.map(dto, DetalleOrdenCompra.class);
        detalleOrdenCompraService.insert(detalleOrdenCompra);
    }

    @GetMapping("Listar")
    public List<DetalleOrdenCompraDTO> listar() {
        return detalleOrdenCompraService.list().stream().map(detalle -> {
            DetalleOrdenCompraDTO dto = new DetalleOrdenCompraDTO();
            dto.setId(detalle.getId());
            dto.setCantidad(detalle.getCantidad());
            dto.setPrecioUnitario(detalle.getPrecioUnitario());
            dto.setProductoId(detalle.getProducto().getId());
            dto.setOrdenCompraId(detalle.getOrdenCompra().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DetalleOrdenCompraDTO listarPorId(@PathVariable("id") Long id) {
        DetalleOrdenCompra detalle = detalleOrdenCompraService.listId(id);
        DetalleOrdenCompraDTO dto = new DetalleOrdenCompraDTO();
        dto.setId(detalle.getId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setProductoId(detalle.getProducto().getId());
        dto.setOrdenCompraId(detalle.getOrdenCompra().getId());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        detalleOrdenCompraService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody DetalleOrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();
        DetalleOrdenCompra detalleOrdenCompra = m.map(dto, DetalleOrdenCompra.class);
        detalleOrdenCompraService.insert(detalleOrdenCompra);
    }
}

