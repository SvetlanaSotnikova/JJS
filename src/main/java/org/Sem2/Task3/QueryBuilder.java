package org.Sem2.Task3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QueryBuilder {
    public String buildInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }

        Table table = clazz.getAnnotation(Table.class);
        StringBuilder query = new StringBuilder("INSERT INTO ")
                .append(table.name())
                .append(" (");

        Field[] fields = clazz.getDeclaredFields();
        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columns.add(column.name());

                field.setAccessible(true);
                Object value = field.get(obj);
                values.add("'" + value + "'");
            }
        }

        if (columns.isEmpty()) {
            return null;
        }
        query.append(String.join(", ", columns))
                .append(") VALUES (")
                .append(String.join(", ", values))
                .append(")")
                .append(";");

        return query.toString();
    }

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }

        Table table = clazz.getAnnotation(Table.class);
        StringBuilder query = new StringBuilder("SELECT * FROM ")
                .append(table.name())
                .append(" WHERE ");


        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column.primaryKey()) {
                    query.append(column.name()).append(" = '").append(primaryKey).append("'")
                            .append(";");
                    break;
                }

            }
        }
        return query.toString();
    }

    public String buildUpdateQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }

        Table table = clazz.getAnnotation(Table.class);
        StringBuilder query = new StringBuilder("UPDATE ")
                .append(table.name()).append(" SET ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column.primaryKey()) {
                    continue;
                }
                query.append(column.name()).append(" = '").append(field.get(obj)).append("', ");
            }
        }
        if (query.charAt(query.length() - 2) == ',') {
            query.delete(query.length() - 2, query.length());
        }

        query.append(" WHERE ");

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column.primaryKey()) {
                    query.append(column.name()).append(" = '").append(field.get(obj)).append("'")
                            .append(";");
                    break;
                }
            }
        }


        return query.toString();
    }

    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }

        Table table = clazz.getAnnotation(Table.class);
        StringBuilder query = new StringBuilder("DELETE FROM ")
                .append(table.name())
                .append(" WHERE ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column.primaryKey()) {
                    query.append(column.name()).append(" = '").append(primaryKey).append("'")
                            .append(";");
                    break;
                }
            }
        }
        return query.toString();
    }
}
