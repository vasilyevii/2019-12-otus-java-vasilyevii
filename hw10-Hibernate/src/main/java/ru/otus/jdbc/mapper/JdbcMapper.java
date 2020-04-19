package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcMapper {

    public static <T> SqlStatement getInsertStatement(T objectData) throws IllegalAccessException {

        Class clazz = objectData.getClass();
        StringBuilder sql = new StringBuilder("insert into ")
                .append(clazz.getSimpleName())
                .append("(");
        StringBuilder sqlValues = new StringBuilder();
        List<String> params = new ArrayList<>();
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            sql.append(field.getName()).append(",");
            params.add(field.get(objectData).toString());
            sqlValues.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1)
                .append(") values (")
                .append(sqlValues.toString())
                .deleteCharAt(sql.length() - 1)
                .append(")");

        return new SqlStatement(sql.toString(), params, fields);
    }

    public static <T> SqlStatement getUpdateStatement(T objectData) throws IllegalAccessException {

        Class clazz = objectData.getClass();
        StringBuilder sql = new StringBuilder("update ")
                .append(clazz.getSimpleName())
                .append(" set ");
        List<String> params = new ArrayList<>();
        Field[] fields = objectData.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            sql.append(field.getName()).append(" = ?,");
            params.add(field.get(objectData).toString());
        }
        sql.deleteCharAt(sql.length() - 1).append(" where ");
        for (Field field : fields) {
            if (field.isAnnotationPresent(ID.class)) {
                sql.append(field.getName()).append(" = ? and ");
                params.add(field.get(objectData).toString());
            }
        }

        return new SqlStatement(sql.toString().substring(0, sql.length() - 5), params, fields);
    }

    public static <T> SqlStatement getSelectStatement(long id, Class<T> clazz) {

        StringBuilder sql = new StringBuilder("select ");
        StringBuilder sqlWhere = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            sql.append(field.getName()).append(",");
            if (field.isAnnotationPresent(ID.class)) {
                sqlWhere.append(" where ").append(field.getName()).append(" = ?");
            }
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ").append(clazz.getSimpleName());
        if (sqlWhere.length() > 0) {
            sql.append(sqlWhere.toString());
        }

        return new SqlStatement(sql.toString(), Collections.singletonList(String.valueOf(id)), fields);
    }

}
