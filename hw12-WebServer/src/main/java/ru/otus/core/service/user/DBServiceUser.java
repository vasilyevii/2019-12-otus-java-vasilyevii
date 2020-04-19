package ru.otus.core.service.user;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    long updateUser(User user);

    Optional<User> getUser(long id);

    Optional<User> getUserByName(String name);

    public UserDao getUserDao();

    public SessionManagerHibernate getSessionManager();

    public EntityManager getEntityManager();
}
