package org.Sem2.Task3;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Employee user = new Employee("Stanislav", "stanislav@gmail.com");
        UUID pk = UUID.randomUUID();
        user.setId(pk);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.buildInsertQuery(user);

        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.printf("Insert Query: %s\n", insertQuery);

        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, user.getId());
        System.out.printf("Select Query: %s\n", selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.printf("Update Query: %s\n", updateQuery);

        String deleteQuery = queryBuilder.buildDeleteQuery(Employee.class, user.getId());
        System.out.printf("Delete Query: %s\n", deleteQuery);
    }
}
