package com.inventorysystem_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventorysystem_project.entities.ProductoTerminado;

@Repository
public interface ProductoTerminadoRepository extends JpaRepository<ProductoTerminado, Long> {
    ProductoTerminado findByNombre(String nombre);
}

