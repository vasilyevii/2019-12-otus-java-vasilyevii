package ru.otus.jdbc.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDataDao;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.mapper.SqlStatement;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ObjectDataDaoJdbc<T> implements ObjectDataDao<T> {
    private static Logger logger = LoggerFactory.getLogger(ObjectDataDaoJdbc.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<T> dbExecutor;

    public ObjectDataDaoJdbc(SessionManagerJdbc sessionManager, DbExecutor<T> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }


    @Override
    public long create(T objectData) {
        try {
            SqlStatement sqlStatement = JdbcMapper.getInsertStatement(objectData);
            return dbExecutor.insertRecord(getConnection(), sqlStatement.getSql(), sqlStatement.getParams());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public long update(T objectData) {
        try {
            SqlStatement sqlStatement = JdbcMapper.getUpdateStatement(objectData);
            return dbExecutor.insertRecord(getConnection(), sqlStatement.getSql(), sqlStatement.getParams());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }


    @Override
    public Optional<T> load(long id, Class<T> clazz) {

        try {

            SqlStatement sqlStatement = JdbcMapper.getSelectStatement(id, clazz);
            Field[] fields = sqlStatement.getFields();
            Constructor<T> constructor = clazz.getConstructor(fields[0].getType(), fields[1].getType(), fields[2].getType());

            return dbExecutor.selectRecord(getConnection(), sqlStatement.getSql(), id, resultSet -> {
                try {
                    if (resultSet.next()) {
                        return constructor.newInstance(
                                getResultSetValue(resultSet, fields[0]),
                                getResultSetValue(resultSet, fields[1]),
                                getResultSetValue(resultSet, fields[2]));
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    private Object getResultSetValue(ResultSet rs, Field field) throws SQLException {

        Class<?> fieldType = field.getType();
        String fieldName = field.getName();
        if (fieldType.isAssignableFrom(int.class)) {
            return rs.getInt(fieldName);
        } else if (fieldType.isAssignableFrom(long.class)) {
            return rs.getLong(fieldName);
        } else if (fieldType.isAssignableFrom(String.class)) {
            return rs.getString(fieldName);
        } else if (fieldType.isAssignableFrom(boolean.class)) {
            return rs.getBoolean(fieldName);
        }
        return null;
    }
}
