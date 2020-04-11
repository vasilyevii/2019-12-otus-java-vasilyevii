package ru.otus.core.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDataDao;
import ru.otus.core.model.Account;
import ru.otus.core.service.DbServiceException;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceAccountImpl implements DBServiceAccount {
    private static Logger logger = LoggerFactory.getLogger(DbServiceAccountImpl.class);

    private final ObjectDataDao objectDataDao;

    public DbServiceAccountImpl(ObjectDataDao objectDataDao) {
        this.objectDataDao = objectDataDao;
    }

    @Override
    public long saveAccount(Account user) {
        try (SessionManager sessionManager = objectDataDao.getSessionManager()) {
            try {
                sessionManager.beginSession();
                long userId = objectDataDao.create(user);
                sessionManager.commitSession();
                logger.info("created account: {} {}", userId, user);
                return userId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public long updateAccount(Account user) {
        try (SessionManager sessionManager = objectDataDao.getSessionManager()) {
            try {
                sessionManager.beginSession();
                long userId = objectDataDao.update(user);
                sessionManager.commitSession();
                logger.info("updated account: {} {}", userId, user);
                return userId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<Account> getAccount(long id) {
        try (SessionManager sessionManager = objectDataDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<Account> userOptional = objectDataDao.load(id, Account.class);

                logger.info("account: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

}
