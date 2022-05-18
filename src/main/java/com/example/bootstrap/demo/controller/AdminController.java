package com.example.bootstrap.demo.controller;

import com.example.bootstrap.demo.entity.User;
import com.example.bootstrap.demo.service.RoleServiceImpl;
import com.example.bootstrap.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public AdminController() {}

    @GetMapping()
    public String homeAdmin() {
        return "redirect:/admin/users";
    }

    @GetMapping("users")
    public String printUsers(Principal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("userSet", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "admin";
    }

    // добавить пользователя
    @GetMapping(value = "users/new")
    public String newUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping(value ="users/new")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "roles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    // изменение пользователя

    @GetMapping("users/{id}/edit")
    public String editUserForm(@ModelAttribute("user") User user,
                               ModelMap model,
                               @PathVariable("id") Long id) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "admin";
    }

    @PostMapping("users/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id,
                         @RequestParam(value = "editRoles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    // удаление пользователя

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }


}
