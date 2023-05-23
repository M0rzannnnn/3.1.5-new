package ru.vinogradov.kataBoot.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import ru.vinogradov.kataBoot.model.Role;
import ru.vinogradov.kataBoot.model.User;

import ru.vinogradov.kataBoot.service.RoleService;
import ru.vinogradov.kataBoot.service.RoleServiceImp;
import ru.vinogradov.kataBoot.service.UserService;
import ru.vinogradov.kataBoot.service.UserServiceImp;


import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController (UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String printUsers (Principal principal, ModelMap model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<User> listOfUsers = userService.getAll();
        model.addAttribute("listOfUsers", listOfUsers);
        model.addAttribute("listOfRoles",roleService.getAllRoles());
        return "Users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser (ModelMap model, @ModelAttribute("user") User user) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "Users";
    }
    @PostMapping("/")
    public String saveUser (User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String editUser (Model role, @PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
//        List<Role> listOfRoles = roleService.getAllRoles();
        role.addAttribute("listOfRoles", roleService.getAllRoles());
//        List<Role> roles1 = new ArrayList<>(Collections.singleton(new Role("ROLE_ADMIN")));
//        int roleValue1 = 1;
//        int roleValue2 = 2;
//        if (userService.show(id).getRoles() == roles1) {
//            model.addAttribute("valueRole", roleValue1);
//        } else {
//            model.addAttribute("valueRole", roleValue2);
//        }
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(ModelMap model, @ModelAttribute ("user") User user, @PathVariable("id") Long id) {
        List<Role> listOfRoles = roleService.getAllRoles();
        model.addAttribute("listOfRoles", listOfRoles);
        userService.update(user, id);
        return "redirect:/admin/";
    }
    @DeleteMapping("/{id}")
    public String  deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";

    }
}
