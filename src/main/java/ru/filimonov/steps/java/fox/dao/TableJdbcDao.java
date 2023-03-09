package ru.filimonov.steps.java.fox.dao;

import ru.filimonov.steps.java.fox.dto.Table;
import ru.filimonov.steps.java.fox.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class TableJdbcDao implements TableDao<Table> {

    public void saveTable(Table sqlTable) throws SQLException {
        try (
                Connection conn = ConnectionManager.open();
                PreparedStatement statement = conn.prepareStatement(sqlTable.getSqlStatement())
        ) {
            statement.executeUpdate();
        }
    }

    @Override
    public List<Table> getAll() {
        return new ArrayList<>();
    }
}
