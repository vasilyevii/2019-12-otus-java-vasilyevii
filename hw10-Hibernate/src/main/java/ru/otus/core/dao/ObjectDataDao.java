package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface ObjectDataDao<T> {

    long create(T objectData);

    long update(T objectData);

    Optional<T> load(long id, Class<T> clazz);

    SessionManager getSessionManager();

}
