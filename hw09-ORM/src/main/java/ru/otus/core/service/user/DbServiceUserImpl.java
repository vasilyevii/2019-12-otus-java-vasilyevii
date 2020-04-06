package ru.otus.core.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDataDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceException;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceUserImpl implements DBServiceUser {
    private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final ObjectDataDao objectDataDao;

    public DbServiceUserImpl(ObjectDataDao objectDataDao) {
        this.objectDataDao = objectDataDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = objectDataDao.getSessionManager()) {
            try {
                sessionManager.beginSession();
                long userId = objectDataDao.create(user);
                sessionManager.commitSession();
                logger.info("created user: {} {}", userId, user);
                return userId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public long updateUser(User user) {
        try (SessionManager sessionManager = objectDataDao.getSessionManager()) {
            try {
                sessionManager.beginSession();
                long userId = objectDataDao.update(user);
                sessionManager.commitSession();
                logger.info("updated user: {} {}", userId, user);
                return userId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        try (SessionManager sessionManager = objectDataDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> userOptional = objectDataDao.load(id, User.class);

                logger.info("user: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

}
