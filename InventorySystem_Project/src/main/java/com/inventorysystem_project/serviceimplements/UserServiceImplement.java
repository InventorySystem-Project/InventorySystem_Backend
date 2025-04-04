package com.inventorysystem_project.serviceimplements;

import com.inventorysystem_project.entities.Users;
import com.inventorysystem_project.repositories.UserRepository;
import com.inventorysystem_project.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplement implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insert(Users user) {
        userRepository.save(user);
    }

    @Override
    public List<Users> list() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users listId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
