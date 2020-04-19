package ru.otus.core.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.core.dao.ObjectDataDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceException;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceUserImpl implements DBServiceUser {
    private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final ObjectDataDao objectDataDao;
    private final HwCache<Long, User> cache = new MyCache<>();

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
//                logger.info("created user: {} {}", userId, user);
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

        Optional<User> userOptional = Optional.ofNullable(cache.get(id));
        if (userOptional.isPresent()) {
            return userOptional;
        }

        try (SessionManager sessionManager = objectDataDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                userOptional = objectDataDao.load(id, User.class);
                if (userOptional.isPresent()) {
                    cache.put(id, userOptional.get());
                }
//                logger.info("user: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public HwCache<Long, User> getCache() {
        return cache;
    }
}
