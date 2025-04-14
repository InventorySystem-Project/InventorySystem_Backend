package com.inventorysystem_project.serviceinterfaces;

import com.inventorysystem_project.entities.VentaProductoTerminado;

import java.util.List;

public interface IVentaProductoTerminadoService {
    void insert(VentaProductoTerminado ventaProductoTerminado);
    List<VentaProductoTerminado> list();
    void delete(Long id);
    VentaProductoTerminado listId(Long id);
}

