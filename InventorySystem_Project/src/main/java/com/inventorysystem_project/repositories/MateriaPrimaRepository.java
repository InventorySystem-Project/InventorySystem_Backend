package com.inventorysystem_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventorysystem_project.entities.MateriaPrima;

@Repository
public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Long> {
    MateriaPrima findByNombre(String nombre);
}

