package com.inventorysystem_project.repositories;

import com.inventorysystem_project.entities.VentaProductoTerminado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaProductoTerminadoRepository extends JpaRepository<VentaProductoTerminado, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}
