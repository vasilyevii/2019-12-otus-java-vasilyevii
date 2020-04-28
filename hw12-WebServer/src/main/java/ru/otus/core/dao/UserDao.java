package ru.otus.core.dao;

import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    long saveUser(User objectData);

    Optional<User> findById(long id);

    Optional<User> findByName(String name);

    List<User> findAll();

    SessionManager getSessionManager();

}
