package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.OrdenCompraDTO;
import com.inventorysystem_project.entities.OrdenCompra;
import com.inventorysystem_project.serviceinterfaces.IOrdenCompraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    private IOrdenCompraService ordenCompraService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody OrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();
        OrdenCompra ordenCompra = m.map(dto, OrdenCompra.class);
        ordenCompraService.insert(ordenCompra);
    }

    @GetMapping("Listar")
    public List<OrdenCompraDTO> listar() {
        return ordenCompraService.list().stream().map(ordenCompra -> {
            OrdenCompraDTO dto = new OrdenCompraDTO();
            dto.setId(ordenCompra.getId());
            dto.setEmpresaId(ordenCompra.getEmpresa().getId());
            dto.setProveedorId(ordenCompra.getProveedor().getId());
            dto.setFechaEmision(ordenCompra.getFechaEmision());
            dto.setTotal(ordenCompra.getTotal());
            dto.setCodigoOrden(ordenCompra.getCodigoOrden());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrdenCompraDTO listarPorId(@PathVariable("id") Long id) {
        OrdenCompra ordenCompra = ordenCompraService.listId(id);
        OrdenCompraDTO dto = new OrdenCompraDTO();
        dto.setId(ordenCompra.getId());
        dto.setEmpresaId(ordenCompra.getEmpresa().getId());
        dto.setProveedorId(ordenCompra.getProveedor().getId());
        dto.setFechaEmision(ordenCompra.getFechaEmision());
        dto.setTotal(ordenCompra.getTotal());
        dto.setCodigoOrden(ordenCompra.getCodigoOrden());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        ordenCompraService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody OrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();
        OrdenCompra ordenCompra = m.map(dto, OrdenCompra.class);
        ordenCompraService.insert(ordenCompra);
    }
}
