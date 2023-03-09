package ru.filimonov.steps.java.fox.formatter;

public interface DataFormatter<T, E> {
    T format(E entity);
}
