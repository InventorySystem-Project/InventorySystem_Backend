package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.VentaProductoTerminadoDTO;
import com.inventorysystem_project.entities.VentaProductoTerminado;
import com.inventorysystem_project.serviceinterfaces.IVentaProductoTerminadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventas-productos-terminados")
public class VentaProductoTerminadoController {

    @Autowired
    private IVentaProductoTerminadoService ventaProductoTerminadoService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody VentaProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        VentaProductoTerminado venta = m.map(dto, VentaProductoTerminado.class);
        ventaProductoTerminadoService.insert(venta);
    }

    @GetMapping("Listar")
    public List<VentaProductoTerminadoDTO> listar() {
        return ventaProductoTerminadoService.list().stream().map(venta -> {
            VentaProductoTerminadoDTO dto = new VentaProductoTerminadoDTO();
            dto.setId(venta.getId());
            dto.setProductoTerminadoId(venta.getProductoTerminado().getId());
            dto.setCantidad(venta.getCantidad());
            dto.setPrecioTotal(venta.getPrecioTotal());
            dto.setFecha(venta.getFecha());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VentaProductoTerminadoDTO listarPorId(@PathVariable("id") Long id) {
        VentaProductoTerminado venta = ventaProductoTerminadoService.listId(id);
        VentaProductoTerminadoDTO dto = new VentaProductoTerminadoDTO();
        dto.setId(venta.getId());
        dto.setProductoTerminadoId(venta.getProductoTerminado().getId());
        dto.setCantidad(venta.getCantidad());
        dto.setPrecioTotal(venta.getPrecioTotal());
        dto.setFecha(venta.getFecha());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        ventaProductoTerminadoService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody VentaProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        VentaProductoTerminado venta = m.map(dto, VentaProductoTerminado.class);
        ventaProductoTerminadoService.insert(venta);
    }
}

