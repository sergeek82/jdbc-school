package ru.filimonov.steps.java.fox.dto;

public class Table {
    private final String sqlStatement;

    public Table(String sql_statement) {
        sqlStatement = sql_statement;
    }

    public String getSqlStatement() {
        return sqlStatement;
    }
}
