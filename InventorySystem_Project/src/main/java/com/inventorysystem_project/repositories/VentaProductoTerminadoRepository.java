package com.inventorysystem_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventorysystem_project.entities.VentaProductoTerminado;

@Repository
public interface VentaProductoTerminadoRepository extends JpaRepository<VentaProductoTerminado, Long> {
}

