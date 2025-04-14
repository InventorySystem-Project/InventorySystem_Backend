package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.StockAlmacenProductoTerminadoDTO;
import com.inventorysystem_project.entities.StockAlmacenProductoTerminado;
import com.inventorysystem_project.serviceinterfaces.IStockAlmacenProductoTerminadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock-almacen-producto-terminado")
public class StockAlmacenProductoTerminadoController {

    @Autowired
    private IStockAlmacenProductoTerminadoService stockAlmacenProductoTerminadoService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody StockAlmacenProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        StockAlmacenProductoTerminado stockAlmacenProductoTerminado = m.map(dto, StockAlmacenProductoTerminado.class);
        stockAlmacenProductoTerminadoService.insert(stockAlmacenProductoTerminado);
    }

    @GetMapping("Listar")
    public List<StockAlmacenProductoTerminadoDTO> listar() {
        return stockAlmacenProductoTerminadoService.list().stream().map(stockAlmacenProductoTerminado -> {
            StockAlmacenProductoTerminadoDTO dto = new StockAlmacenProductoTerminadoDTO();
            dto.setId(stockAlmacenProductoTerminado.getId());
            dto.setAlmacenId(stockAlmacenProductoTerminado.getAlmacen().getId());
            dto.setProductoTerminadoId(stockAlmacenProductoTerminado.getProductoTerminado().getId());
            dto.setStockActual(stockAlmacenProductoTerminado.getStockActual());
            dto.setStockMinimo(stockAlmacenProductoTerminado.getStockMinimo());
            dto.setUltimaActualizacion(stockAlmacenProductoTerminado.getUltimaActualizacion());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StockAlmacenProductoTerminadoDTO listarPorId(@PathVariable("id") Long id) {
        StockAlmacenProductoTerminado stockAlmacenProductoTerminado = stockAlmacenProductoTerminadoService.listId(id);
        StockAlmacenProductoTerminadoDTO dto = new StockAlmacenProductoTerminadoDTO();
        dto.setId(stockAlmacenProductoTerminado.getId());
        dto.setAlmacenId(stockAlmacenProductoTerminado.getAlmacen().getId());
        dto.setProductoTerminadoId(stockAlmacenProductoTerminado.getProductoTerminado().getId());
        dto.setStockActual(stockAlmacenProductoTerminado.getStockActual());
        dto.setStockMinimo(stockAlmacenProductoTerminado.getStockMinimo());
        dto.setUltimaActualizacion(stockAlmacenProductoTerminado.getUltimaActualizacion());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        stockAlmacenProductoTerminadoService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody StockAlmacenProductoTerminadoDTO dto) {
        ModelMapper m = new ModelMapper();
        StockAlmacenProductoTerminado stockAlmacenProductoTerminado = m.map(dto, StockAlmacenProductoTerminado.class);
        stockAlmacenProductoTerminadoService.insert(stockAlmacenProductoTerminado);
    }
}
