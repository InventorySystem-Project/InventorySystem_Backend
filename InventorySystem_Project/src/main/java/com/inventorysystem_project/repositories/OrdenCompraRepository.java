package com.inventorysystem_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventorysystem_project.entities.OrdenCompra;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
    OrdenCompra findByNumeroOrden(String numeroOrden);
}

