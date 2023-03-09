package ru.filimonov.steps.java.fox.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<E> {

    List<E> getAll() throws SQLException;
}
