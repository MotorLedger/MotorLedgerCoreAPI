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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateUser(User oldUser) {
        User newUser = getUserById(oldUser.getId());
        if (newUser != null) {
            newUser.setName(oldUser.getName());
            newUser.setAddress(oldUser.getAddress());
            newUser.setPhone(oldUser.getPhone());
            return saveUser(newUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
