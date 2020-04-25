package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;

import java.util.List;

@Controller
public class AdminController {

    private final DBServiceUser dbServiceUser;

    public AdminController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @GetMapping({"/", "/admin"})
    public String adminPage(Model model) {
        model.addAttribute("user", new User());
        List<User> users = dbServiceUser.getAllUsers();
        model.addAttribute("users", users);
        return "admin.html";
    }
}
