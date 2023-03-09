package ru.filimonov.steps.java.fox.dao;

import ru.filimonov.steps.java.fox.dto.Student;
import ru.filimonov.steps.java.fox.utils.ConnectionManager;
import ru.filimonov.steps.java.fox.utils.PropertiesUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbcDao implements StudentDao<Student> {
    private static final String STUDENTS_INSERT = "db.student.insertion";
    private static final String STUDENT_QUERY = "db.student.get.all";
    private static final String ID = "id";
    private static final String GROUP_ID = "group_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    public void saveStudent(Student student) throws SQLException {
        try (
                Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection.prepareStatement(
                        String.format(PropertiesUtil.get(STUDENTS_INSERT), student.getGroupId(), student.getFirstName(),
                                student.getLastName()))
        ) {
            statement.executeUpdate();
        }
    }

    public List<Student> getAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.open();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(PropertiesUtil.get(STUDENT_QUERY))
        ) {
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt(ID), resultSet.getInt(GROUP_ID),
                        resultSet.getString(FIRST_NAME), resultSet.getString(LAST_NAME));
                studentList.add(student);
            }
        }
        return studentList;
    }
}
