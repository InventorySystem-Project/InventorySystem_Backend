package com.inventorysystem_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.aaw.demo1_202302_si63.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}