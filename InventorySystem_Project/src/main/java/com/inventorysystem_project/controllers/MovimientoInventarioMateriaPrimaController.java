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
@RequestMapping("/movimientos-inventario-materia-prima")
public class MovimientoInventarioMateriaPrimaController {

    @Autowired
    private IMovimientoInventarioMateriaPrimaService movimientoInventarioMateriaPrimaService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody MovimientoInventarioMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        MovimientoInventarioMateriaPrima movimiento = m.map(dto, MovimientoInventarioMateriaPrima.class);
        movimientoInventarioMateriaPrimaService.insert(movimiento);
    }

    @GetMapping("Listar")
    public List<MovimientoInventarioMateriaPrimaDTO> listar() {
        return movimientoInventarioMateriaPrimaService.list().stream().map(movimiento -> {
            MovimientoInventarioMateriaPrimaDTO dto = new MovimientoInventarioMateriaPrimaDTO();
            dto.setId(movimiento.getId());
            dto.setCantidad(movimiento.getCantidad());
            dto.setFecha(movimiento.getFecha());
            dto.setTipoMovimiento(movimiento.getTipoMovimiento());
            dto.setMateriaPrimaId(movimiento.getMateriaPrima().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MovimientoInventarioMateriaPrimaDTO listarPorId(@PathVariable("id") Long id) {
        MovimientoInventarioMateriaPrima movimiento = movimientoInventarioMateriaPrimaService.listId(id);
        MovimientoInventarioMateriaPrimaDTO dto = new MovimientoInventarioMateriaPrimaDTO();
        dto.setId(movimiento.getId());
        dto.setCantidad(movimiento.getCantidad());
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setMateriaPrimaId(movimiento.getMateriaPrima().getId());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        movimientoInventarioMateriaPrimaService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody MovimientoInventarioMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        MovimientoInventarioMateriaPrima movimiento = m.map(dto, MovimientoInventarioMateriaPrima.class);
        movimientoInventarioMateriaPrimaService.insert(movimiento);
    }
}
