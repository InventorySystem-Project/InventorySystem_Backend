package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.DetalleOrdenCompraDTO;
import com.inventorysystem_project.dtos.OrdenCompraDTO;
import com.inventorysystem_project.dtos.*;
import com.inventorysystem_project.entities.DetalleOrdenCompra;
import com.inventorysystem_project.entities.MateriaPrima;
import com.inventorysystem_project.entities.OrdenCompra;
import com.inventorysystem_project.serviceinterfaces.IMateriaPrimaService;
import com.inventorysystem_project.serviceinterfaces.IOrdenCompraService;
import com.inventorysystem_project.serviceinterfaces.IDetalleOrdenCompraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private IMateriaPrimaService materiaPrimaService; // <-- AÑADE ESTA LÍNEA
    // En OrdenCompraController.java
    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('GUEST')")
    @Transactional
// CAMBIO 1: La función ya no es 'void'. Ahora devuelve una respuesta con la orden creada.
    public ResponseEntity<OrdenCompraDTO> registrar(@RequestBody OrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();

        if (dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La orden de compra debe tener al menos un detalle.");
        }

        OrdenCompra ordenCompra = m.map(dto, OrdenCompra.class);
        ordenCompra.setDetalles(null);

        // Guardamos la cabecera y la re-asignamos para obtenerla con su ID generado
        ordenCompra = ordenCompraService.insert(ordenCompra);

        // Procesamos y guardamos los detalles
        for (DetalleOrdenCompraDTO detalleDTO : dto.getDetalles()) {
            if (detalleDTO.getMateriaPrimaId() == null) {
                throw new IllegalArgumentException("El producto en la orden de compra no tiene un ID válido (es nulo).");
            }
            DetalleOrdenCompra detalle = new DetalleOrdenCompra();
            detalle.setOrdenCompra(ordenCompra);

            MateriaPrima materiaPrimaPersistente = materiaPrimaService.listId(detalleDTO.getMateriaPrimaId());
            if (materiaPrimaPersistente == null) {
                throw new RuntimeException("Materia Prima con ID " + detalleDTO.getMateriaPrimaId() + " no encontrada.");
            }
            detalle.setMateriaPrima(materiaPrimaPersistente);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalleOrdenCompraService.insert(detalle);
        }

        // CAMBIO 2: Preparamos y devolvemos la orden completa al frontend.
        // Buscamos la orden recién creada para que incluya todos sus detalles.
        OrdenCompra ordenCompleta = ordenCompraService.listId(ordenCompra.getId());
        OrdenCompraDTO ordenCompletaDTO = m.map(ordenCompleta, OrdenCompraDTO.class);

        // Devolvemos el objeto DTO con un estado HTTP 201 (Created), que es la práctica correcta.
        return new ResponseEntity<>(ordenCompletaDTO, HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('GUEST')")
    public List<OrdenCompraDTO> listar() {
        return ordenCompraService.list().stream().map(ordenCompra -> {
            ModelMapper m = new ModelMapper();
            return m.map(ordenCompra, OrdenCompraDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('GUEST')")
    public OrdenCompraDTO listarPorId(@PathVariable("id") Long id) {
        OrdenCompra ordenCompra = ordenCompraService.listId(id);
        ModelMapper m = new ModelMapper();
        return m.map(ordenCompra, OrdenCompraDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('GUEST')")
    public void eliminar(@PathVariable("id") Long id) {
        ordenCompraService.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('GUEST')")
    public void modificar(@RequestBody OrdenCompraDTO dto) {
        ModelMapper m = new ModelMapper();
        OrdenCompra ordenCompra = m.map(dto, OrdenCompra.class);
        ordenCompraService.insert(ordenCompra);
    }
}
