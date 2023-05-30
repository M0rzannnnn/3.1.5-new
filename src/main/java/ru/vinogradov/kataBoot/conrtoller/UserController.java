package ru.vinogradov.kataBoot.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vinogradov.kataBoot.model.User;
import ru.vinogradov.kataBoot.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/myUser")
    public String show(@ModelAttribute("users") User user, Model model, Principal principal) {
        model.addAttribute("users", this.userService.findByEmail(principal.getName()));
        return "user";
    }
}
