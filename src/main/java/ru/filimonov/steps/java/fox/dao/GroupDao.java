package ru.filimonov.steps.java.fox.dao;

import java.sql.SQLException;
import java.util.Map;

public interface GroupDao<E, S, I> extends GenericDao<E> {

    void saveGroup(E group);

    Map<S, I> getGroupStudents(I count) throws SQLException;
}
