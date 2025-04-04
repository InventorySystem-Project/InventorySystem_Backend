package com.inventorysystem_project.serviceimplements;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import com.inventorysystem_project.entities.Role;
import com.inventorysystem_project.repositories.RoleRepository;
import com.inventorysystem_project.serviceinterfaces.IRolService;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private RoleRepository rolR;

    @Override
    public void insert(Role role) {
        rolR.save(role);
    }

    @Override
    public List<Role> list() {
        return rolR.findAll();
    }

    @Override
    public void delete(int idRol) {

    }

    @Override
    public Role listId(int idRol) {
        return null;
    }


}
