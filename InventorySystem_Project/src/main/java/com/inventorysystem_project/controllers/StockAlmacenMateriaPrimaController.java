package com.inventorysystem_project.controllers;

import com.inventorysystem_project.dtos.StockAlmacenMateriaPrimaDTO;
import com.inventorysystem_project.entities.StockAlmacenMateriaPrima;
import com.inventorysystem_project.serviceinterfaces.IStockAlmacenMateriaPrimaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock-almacen-materia-prima")
public class StockAlmacenMateriaPrimaController {

    @Autowired
    private IStockAlmacenMateriaPrimaService stockAlmacenMateriaPrimaService;

    @PostMapping("Registrar")
    public void registrar(@RequestBody StockAlmacenMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        StockAlmacenMateriaPrima stock = m.map(dto, StockAlmacenMateriaPrima.class);
        stockAlmacenMateriaPrimaService.insert(stock);
    }

    @GetMapping("Listar")
    public List<StockAlmacenMateriaPrimaDTO> listar() {
        return stockAlmacenMateriaPrimaService.list().stream().map(stock -> {
            StockAlmacenMateriaPrimaDTO dto = new StockAlmacenMateriaPrimaDTO();
            dto.setId(stock.getId());
            dto.setAlmacenId(stock.getAlmacen().getId());
            dto.setMateriaPrimaId(stock.getMateriaPrima().getId());
            dto.setCantidad(stock.getCantidad());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StockAlmacenMateriaPrimaDTO listarPorId(@PathVariable("id") Long id) {
        StockAlmacenMateriaPrima stock = stockAlmacenMateriaPrimaService.listId(id);
        StockAlmacenMateriaPrimaDTO dto = new StockAlmacenMateriaPrimaDTO();
        dto.setId(stock.getId());
        dto.setAlmacenId(stock.getAlmacen().getId());
        dto.setMateriaPrimaId(stock.getMateriaPrima().getId());
        dto.setCantidad(stock.getCantidad());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        stockAlmacenMateriaPrimaService.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody StockAlmacenMateriaPrimaDTO dto) {
        ModelMapper m = new ModelMapper();
        StockAlmacenMateriaPrima stock = m.map(dto, StockAlmacenMateriaPrima.class);
        stockAlmacenMateriaPrimaService.insert(stock);
    }
}

