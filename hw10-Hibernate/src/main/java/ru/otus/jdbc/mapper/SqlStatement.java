package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;

public class SqlStatement {

    private final String sql;
    private final List<String> params;
    private final Field[] fields;

    public SqlStatement(String sql, List<String> params, Field[] fields) {
        this.sql = sql;
        this.params = params;
        this.fields = fields;
    }

    public String getSql() {
        return sql;
    }

    public List<String> getParams() {
        return params;
    }

    public Field[] getFields() {
        return fields;
    }
}
