package ru.otus.core.dao;

import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import java.util.Optional;

public interface UserDao {

    long saveUser(User objectData);

    Optional<User> findById(long id);

    SessionManager getSessionManager();
}
