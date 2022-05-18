package com.example.bootstrap.demo.service;

import com.example.bootstrap.demo.entity.User;
import java.util.List;

public interface UserService {
     void addUser(User user);
     void deleteUser(Long id);
     void editUser(User user);
     User getUserById(Long id);
     User getUserByEmail(String email);
     List<User> getAllUsers();
}
