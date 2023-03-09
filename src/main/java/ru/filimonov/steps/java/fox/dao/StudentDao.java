package ru.filimonov.steps.java.fox.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface StudentDao<E> extends GenericDao<E> {

    void saveStudent(E student) throws SQLException;
}
