package ru.filimonov.steps.java.fox.dao;

import ru.filimonov.steps.java.fox.dto.StudentCourse;
import ru.filimonov.steps.java.fox.utils.ConnectionManager;
import ru.filimonov.steps.java.fox.utils.PropertiesUtil;

import java.sql.*;
import java.util.*;

public class StudentCourseJdbcDao implements StudentCourseDao<StudentCourse> {
    private static final String INSERT = "db.stud.course.insertion";
    private static final String QUERY = "db.stud.course.get.all";
    private static final int FIRST_COLUMN = 1;
    private static final int SECOND_COLUMN = 2;

    public void saveStudentCourse(StudentCourse studentCourse) throws SQLException {
        try (
                Connection conn = ConnectionManager.open();
                Statement statement = conn.createStatement()
        ) {
            statement.executeUpdate(String.format(PropertiesUtil.get(INSERT), studentCourse.getStudentId(),
                    studentCourse.getCourseId()));
        }
    }

    public List<StudentCourse> getAll() throws SQLException {
        List<StudentCourse> studentCourseList = new ArrayList<>();
        try (
                Connection conn = ConnectionManager.open();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(PropertiesUtil.get(QUERY))
        ) {
            while (resultSet.next()) {
                StudentCourse studentCourse = new StudentCourse(resultSet.getInt(FIRST_COLUMN),
                        resultSet.getInt(SECOND_COLUMN));
                studentCourseList.add(studentCourse);
            }
            return studentCourseList;
        }
    }
}

