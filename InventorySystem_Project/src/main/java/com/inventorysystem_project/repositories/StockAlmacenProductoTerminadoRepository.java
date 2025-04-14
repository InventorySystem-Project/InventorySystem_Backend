package com.inventorysystem_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventorysystem_project.entities.StockAlmacenProductoTerminado;

@Repository
public interface StockAlmacenProductoTerminadoRepository extends JpaRepository<StockAlmacenProductoTerminado, Long> {
}
