package ru.filimonov.steps.java.fox.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface TableDao<E> extends GenericDao<E> {

    void saveTable(E table) throws SQLException, IOException;
}