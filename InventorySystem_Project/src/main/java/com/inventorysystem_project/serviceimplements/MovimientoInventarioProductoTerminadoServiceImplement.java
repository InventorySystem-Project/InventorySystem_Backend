package com.inventorysystem_project.serviceimplements;

import com.inventorysystem_project.entities.MovimientoInventarioProductoTerminado;
import com.inventorysystem_project.repositories.MovimientoInventarioProductoTerminadoRepository;
import com.inventorysystem_project.serviceinterfaces.IMovimientoInventarioProductoTerminadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoInventarioProductoTerminadoServiceImplement implements IMovimientoInventarioProductoTerminadoService {

    @Autowired
    private MovimientoInventarioProductoTerminadoRepository movimientoInventarioProductoTerminadoRepository;

    @Override
    public void insert(MovimientoInventarioProductoTerminado movimientoInventarioProductoTerminado) {
        movimientoInventarioProductoTerminadoRepository.save(movimientoInventarioProductoTerminado);
    }

    @Override
    public List<MovimientoInventarioProductoTerminado> list() {
        return movimientoInventarioProductoTerminadoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        movimientoInventarioProductoTerminadoRepository.deleteById(id);
    }

    @Override
    public MovimientoInventarioProductoTerminado listId(Long id) {
        return movimientoInventarioProductoTerminadoRepository.findById(id).orElse(null);
    }
}
