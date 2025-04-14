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
@RequestMapping("/detalle-orden-compra")
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
        return detalleOrdenCompraService.list().stream().map(detalleOrdenCompra -> {
            DetalleOrdenCompraDTO dto = new DetalleOrdenCompraDTO();
            dto.setId(detalleOrdenCompra.getId());
            dto.setOrdenCompraId(detalleOrdenCompra.getOrdenCompra().getId());
            dto.setMateriaPrimaId(detalleOrdenCompra.getMateriaPrima().getId());
            dto.setCantidad(detalleOrdenCompra.getCantidad());
            dto.setPrecioUnitario(detalleOrdenCompra.getPrecioUnitario());
            dto.setDescuento(detalleOrdenCompra.getDescuento());
            dto.setImpuesto(detalleOrdenCompra.getImpuesto());
            dto.setSubtotal(detalleOrdenCompra.getSubtotal());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DetalleOrdenCompraDTO listarPorId(@PathVariable("id") Long id) {
        DetalleOrdenCompra detalleOrdenCompra = detalleOrdenCompraService.listId(id);
        DetalleOrdenCompraDTO dto = new DetalleOrdenCompraDTO();
        dto.setId(detalleOrdenCompra.getId());
        dto.setOrdenCompraId(detalleOrdenCompra.getOrdenCompra().getId());
        dto.setMateriaPrimaId(detalleOrdenCompra.getMateriaPrima().getId());
        dto.setCantidad(detalleOrdenCompra.getCantidad());
        dto.setPrecioUnitario(detalleOrdenCompra.getPrecioUnitario());
        dto.setDescuento(detalleOrdenCompra.getDescuento());
        dto.setImpuesto(detalleOrdenCompra.getImpuesto());
        dto.setSubtotal(detalleOrdenCompra.getSubtotal());
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
