package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.VentaProductoTerminadoDTO;
import com.inventorysystem_project.entities.VentaProductoTerminado;
import com.inventorysystem_project.serviceinterfaces.IVentaProductoTerminadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/venta-producto-terminado")
public class VentaProductoTerminadoController {

    @Autowired
    private IVentaProductoTerminadoService ventaProductoTerminadoService;

    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registrar(@RequestBody VentaProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        VentaProductoTerminado x = m.map(dto, VentaProductoTerminado.class);
        ventaProductoTerminadoService.insert(x);
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<VentaProductoTerminadoDTO> listar() {
        return ventaProductoTerminadoService.list().stream().map(ventaProductoTerminado -> {
            ModelMapper m = new ModelMapper();
            return m.map(ventaProductoTerminado, VentaProductoTerminadoDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public VentaProductoTerminadoDTO listarPorId(@PathVariable("id") Long id) {
        VentaProductoTerminado x = ventaProductoTerminadoService.listId(id);
        ModelMapper m = new ModelMapper();
        return m.map(x, VentaProductoTerminadoDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") Long id) {
        ventaProductoTerminadoService.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void modificar(@RequestBody VentaProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        VentaProductoTerminado x = m.map(dto, VentaProductoTerminado.class);
        ventaProductoTerminadoService.insert(x);
    }
}
