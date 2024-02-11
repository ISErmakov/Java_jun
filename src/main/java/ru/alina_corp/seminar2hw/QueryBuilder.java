package ru.alina_corp.seminar2hw;

public class QueryBuilder {
    public String buildDeleteQuery(String table, int id) {
        return "DELETE FROM " + table + " WHERE ID = '" + id + "'";
    }
}
