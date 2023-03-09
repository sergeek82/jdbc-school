package ru.filimonov.steps.java.fox.dao;

import ru.filimonov.steps.java.fox.dto.Course;
import ru.filimonov.steps.java.fox.utils.ConnectionManager;
import ru.filimonov.steps.java.fox.utils.PropertiesUtil;

import java.sql.*;
import java.util.*;

public class CourseJdbcDao implements CourseDao<Course> {
    private static final String COURSES_INSERT = "db.courses.insertion";
    private static final String GET_ALL = "db.courses.get.all";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";

    public void saveCourse(Course course) {
        try (Connection conn = ConnectionManager.open();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(
                    String.format(PropertiesUtil.get(COURSES_INSERT), course.getCourseName(),
                            course.getCourseDescription()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAll() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(PropertiesUtil.get(GET_ALL))) {
            while (rs.next()) {
                Course course = new Course(rs.getString(NAME), rs.getString(DESCRIPTION));
                course.setCourseId(rs.getInt(ID));
                courseList.add(course);
            }
        }
        return courseList;
    }
}
