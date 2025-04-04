package com.inventorysystem_project.serviceinterfaces;

import com.inventorysystem_project.entities.Users;

import java.util.List;

public interface IUserService {
    void insert(Users user);
    List<Users> list();
    void delete(Long id);
    Users listId(Long id);

    // Puedes agregar métodos personalizados luego, como:
    // List<Users> findByEnabled(boolean status);
    // List<Users> findByRoleName(String role);
}