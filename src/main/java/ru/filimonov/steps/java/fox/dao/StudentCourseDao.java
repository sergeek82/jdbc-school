package ru.filimonov.steps.java.fox.dao;

import java.sql.SQLException;

public interface StudentCourseDao<E> extends GenericDao<E> {

    void saveStudentCourse(E studentCourse) throws SQLException;
}
