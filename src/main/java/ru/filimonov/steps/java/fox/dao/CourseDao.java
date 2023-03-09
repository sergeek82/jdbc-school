package ru.filimonov.steps.java.fox.dao;

public interface CourseDao<E> extends GenericDao<E> {

    void saveCourse(E course);
}
