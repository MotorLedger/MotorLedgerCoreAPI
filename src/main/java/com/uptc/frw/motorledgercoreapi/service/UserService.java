package com.uptc.frw.motorledgercoreapi.service;

import com.uptc.frw.motorledgercoreapi.model.User;
import com.uptc.frw.motorledgercoreapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
