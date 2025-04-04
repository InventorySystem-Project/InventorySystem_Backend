package com.inventorysystem_project.serviceinterfaces;

import com.inventorysystem_project.entities.Role;

import java.util.List;

public interface IRolService {
    public void insert(Role role);
    public List<Role> list();
    public void delete(int idRol);
    public Role listId(int idRol);
}
