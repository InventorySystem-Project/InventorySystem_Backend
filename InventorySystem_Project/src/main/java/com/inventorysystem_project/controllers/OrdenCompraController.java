package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.DetalleOrdenCompraDTO;
import com.inventorysystem_project.dtos.OrdenCompraDTO;
import com.inventorysystem_project.dtos.*;
import com.inventorysystem_project.entities.DetalleOrdenCompra;
import com.inventorysystem_project.entities.MateriaPrima;
import com.inventorysystem_project.entities.OrdenCompra;
import com.inventorysystem_project.serviceinterfaces.IOrdenCompraService;
import com.inventorysystem_project.serviceinterfaces.IDetalleOrdenCompraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    private IOrdenCompraService ordenCompraService;

    @Autowired
    private IDetalleOrdenCompraService detalleOrdenCompraService;

    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public void registrar(@RequestBody OrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();

        // ❌ No mapees detalles automáticamente
        OrdenCompra ordenCompra = m.map(dto, OrdenCompra.class);
        ordenCompra.setDetalles(null); // 👈 Muy importante: limpiamos detalles antes de guardar

        // Guardar orden sola
        ordenCompraService.insert(ordenCompra);

        // Ahora guardamos los detalles manualmente
        if (dto.getDetalles() != null && !dto.getDetalles().isEmpty()) {
            for (DetalleOrdenCompraDTO detalleDTO : dto.getDetalles()) {
                DetalleOrdenCompra detalle = new DetalleOrdenCompra();
                detalle.setOrdenCompra(ordenCompra);

                MateriaPrima materiaPrima = new MateriaPrima();
                materiaPrima.setId(detalleDTO.getMateriaPrimaId());
                detalle.setMateriaPrima(materiaPrima);

                detalle.setCantidad(detalleDTO.getCantidad());

                detalleOrdenCompraService.insert(detalle);
            }
        }
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<OrdenCompraDTO> listar() {
        return ordenCompraService.list().stream().map(ordenCompra -> {
            ModelMapper m = new ModelMapper();
            return m.map(ordenCompra, OrdenCompraDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public OrdenCompraDTO listarPorId(@PathVariable("id") Long id) {
        OrdenCompra ordenCompra = ordenCompraService.listId(id);
        ModelMapper m = new ModelMapper();
        return m.map(ordenCompra, OrdenCompraDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") Long id) {
        ordenCompraService.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void modificar(@RequestBody OrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();
        OrdenCompra ordenCompra = m.map(dto, OrdenCompra.class);
        ordenCompraService.insert(ordenCompra);
    }
}
