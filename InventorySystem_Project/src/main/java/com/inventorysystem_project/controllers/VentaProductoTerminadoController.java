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
@RequestMapping("/venta-producto-terminado")
public class VentaProductoTerminadoController {

    @Autowired
    private IVentaProductoTerminadoService ventaProductoTerminadoService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody VentaProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        VentaProductoTerminado ventaProductoTerminado = m.map(dto, VentaProductoTerminado.class);
        ventaProductoTerminadoService.insert(ventaProductoTerminado);
    }

    @GetMapping("Listar")
    public List<VentaProductoTerminadoDTO> listar() {
        return ventaProductoTerminadoService.list().stream().map(ventaProductoTerminado -> {
            VentaProductoTerminadoDTO dto = new VentaProductoTerminadoDTO();
            dto.setId(ventaProductoTerminado.getId());
            dto.setAlmacenId(ventaProductoTerminado.getAlmacen().getId());
            dto.setFechaMovimiento(ventaProductoTerminado.getFechaMovimiento());
            dto.setTipoMovimiento(ventaProductoTerminado.getTipoMovimiento());
            dto.setCantidad(ventaProductoTerminado.getCantidad());
            dto.setUnidad(ventaProductoTerminado.getUnidad());
            dto.setEstadoEntrega(ventaProductoTerminado.getEstadoEntrega());
            dto.setProductoTerminadoId(ventaProductoTerminado.getProductoTerminado().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VentaProductoTerminadoDTO listarPorId(@PathVariable("id") Long id) {
        VentaProductoTerminado ventaProductoTerminado = ventaProductoTerminadoService.listId(id);
        VentaProductoTerminadoDTO dto = new VentaProductoTerminadoDTO();
        dto.setId(ventaProductoTerminado.getId());
        dto.setAlmacenId(ventaProductoTerminado.getAlmacen().getId());
        dto.setFechaMovimiento(ventaProductoTerminado.getFechaMovimiento());
        dto.setTipoMovimiento(ventaProductoTerminado.getTipoMovimiento());
        dto.setCantidad(ventaProductoTerminado.getCantidad());
        dto.setUnidad(ventaProductoTerminado.getUnidad());
        dto.setEstadoEntrega(ventaProductoTerminado.getEstadoEntrega());
        dto.setProductoTerminadoId(ventaProductoTerminado.getProductoTerminado().getId());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        ventaProductoTerminadoService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody VentaProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        VentaProductoTerminado ventaProductoTerminado = m.map(dto, VentaProductoTerminado.class);
        ventaProductoTerminadoService.insert(ventaProductoTerminado);
    }
}
