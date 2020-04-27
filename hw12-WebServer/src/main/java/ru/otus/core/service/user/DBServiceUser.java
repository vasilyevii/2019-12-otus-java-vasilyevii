package ru.otus.core.service.user;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    long updateUser(User user);

    Optional<User> getUserByID(long id);

    Optional<User> getUserByName(String name);

    List<User> findAll();

    UserDao getUserDao();

}
