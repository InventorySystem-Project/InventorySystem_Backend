package com.inventorysystem_project.serviceimplements;

import com.inventorysystem_project.entities.VentaProductoTerminado;
import com.inventorysystem_project.repositories.VentaProductoTerminadoRepository;
import com.inventorysystem_project.serviceinterfaces.IVentaProductoTerminadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaProductoTerminadoServiceImplement implements IVentaProductoTerminadoService {

    @Autowired
    private VentaProductoTerminadoRepository ventaProductoTerminadoRepository;

    @Override
    public void insert(VentaProductoTerminado ventaProductoTerminado) {
        ventaProductoTerminadoRepository.save(ventaProductoTerminado);
    }

    @Override
    public List<VentaProductoTerminado> list() {
        return ventaProductoTerminadoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        ventaProductoTerminadoRepository.deleteById(id);
    }

    @Override
    public VentaProductoTerminado listId(Long id) {
        return ventaProductoTerminadoRepository.findById(id).orElse(null);
    }
}

