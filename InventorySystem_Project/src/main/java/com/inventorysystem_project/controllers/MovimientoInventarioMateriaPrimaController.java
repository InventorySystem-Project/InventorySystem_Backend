package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.MovimientoInventarioMateriaPrimaDTO;
import com.inventorysystem_project.entities.MovimientoInventarioMateriaPrima;
import com.inventorysystem_project.serviceinterfaces.IMovimientoInventarioMateriaPrimaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class MovimientoInventarioMateriaPrimaController {

    @Autowired
    private IMovimientoInventarioMateriaPrimaService movimientoService;

    // Registrar un nuevo movimiento
    @PostMapping("Registrar")
    public void registrar(@RequestBody MovimientoInventarioMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        MovimientoInventarioMateriaPrima movimiento = m.map(dto, MovimientoInventarioMateriaPrima.class);
        movimientoService.insert(movimiento);
    }

    // Listar todos los movimientos
    @GetMapping("Listar")
    public List<MovimientoInventarioMateriaPrimaDTO> listar() {
        return movimientoService.list().stream().map(movimiento -> {
            MovimientoInventarioMateriaPrimaDTO dto = new MovimientoInventarioMateriaPrimaDTO();
            dto.setId(movimiento.getId());
            dto.setAlmacenId(movimiento.getAlmacen().getId());
            dto.setMateriaPrimaId(movimiento.getMateriaPrima().getId());
            dto.setFechaMovimiento(movimiento.getFechaMovimiento());
            dto.setTipoMovimiento(movimiento.getTipoMovimiento());
            dto.setCantidad(movimiento.getCantidad());
            dto.setUnidad(movimiento.getUnidad());
            dto.setEstadoRecepcion(movimiento.getEstadoRecepcion());
            return dto;
        }).collect(Collectors.toList());
    }

    // Obtener un movimiento por ID
    @GetMapping("/{id}")
    public MovimientoInventarioMateriaPrimaDTO listarPorId(@PathVariable("id") Long id) {
        MovimientoInventarioMateriaPrima movimiento = movimientoService.listId(id);
        MovimientoInventarioMateriaPrimaDTO dto = new MovimientoInventarioMateriaPrimaDTO();
        dto.setId(movimiento.getId());
        dto.setAlmacenId(movimiento.getAlmacen().getId());
        dto.setMateriaPrimaId(movimiento.getMateriaPrima().getId());
        dto.setFechaMovimiento(movimiento.getFechaMovimiento());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setCantidad(movimiento.getCantidad());
        dto.setUnidad(movimiento.getUnidad());
        dto.setEstadoRecepcion(movimiento.getEstadoRecepcion());
        return dto;
    }

    // Eliminar un movimiento
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        movimientoService.delete(id);
    }

    // Modificar un movimiento
    @PutMapping
    public void modificar(@RequestBody MovimientoInventarioMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        MovimientoInventarioMateriaPrima movimiento = m.map(dto, MovimientoInventarioMateriaPrima.class);
        movimientoService.insert(movimiento);
    }
}
