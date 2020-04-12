package ru.otus.core.service.user;

import ru.otus.cachehw.HwCache;
import ru.otus.core.model.User;

import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    long updateUser(User user);

    Optional<User> getUser(long id);

    HwCache<Long, User> getCache();

}
