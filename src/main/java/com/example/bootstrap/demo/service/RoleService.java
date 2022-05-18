package com.example.bootstrap.demo.service;

import com.example.bootstrap.demo.entity.Role;
import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    Set<Role> getSetOfRoles(String[] roleNames);
    void add(Role role);
    void edit(Role role);
    Role getById(int id);
}
