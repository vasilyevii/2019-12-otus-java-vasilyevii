package ru.otus.controllers;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;


@RestController
public class UserController {

    private final DBServiceUser dbServiceUser;

    public UserController(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @PostMapping("/api/user/create")
    public String userSave(@RequestBody String body) {
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        dbServiceUser.saveUser(user);
        return gson.toJson(user);
    }
}
