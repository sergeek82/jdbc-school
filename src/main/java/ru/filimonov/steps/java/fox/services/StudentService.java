package ru.filimonov.steps.java.fox.services;

import ru.filimonov.steps.java.fox.dao.CourseDao;
import ru.filimonov.steps.java.fox.dao.StudentCourseDao;
import ru.filimonov.steps.java.fox.dao.StudentDao;
import ru.filimonov.steps.java.fox.dto.Course;
import ru.filimonov.steps.java.fox.dto.Student;
import ru.filimonov.steps.java.fox.dto.StudentCourse;
import ru.filimonov.steps.java.fox.formatter.DataFormatter;
import ru.filimonov.steps.java.fox.formatter.StudentCourseFormat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentService {
    StudentCourseDao<StudentCourse> studentCourseDao;
    StudentDao<Student> studentDao;
    CourseDao<Course> courseDao;
    DataFormatter<String, Map<String, String>> studentCourseFormat;

    public StudentService(StudentCourseDao<StudentCourse> studentCourseDao, StudentDao<Student> studentDao,
                          CourseDao<Course> courseDao, DataFormatter<String, Map<String, String>> studentCourseFormat) {
        this.studentCourseDao = studentCourseDao;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.studentCourseFormat = studentCourseFormat;
    }

    public String findStudentsByCourse(String courseName) throws SQLException {
        return studentCourseFormat.format(getData(courseName));
    }

    public void addStudent(int groupId, String firstName, String lastName) throws SQLException {
        studentDao.saveStudent(new Student(groupId, firstName, lastName));
    }

    private Map<String, String> getData(String courseName) throws SQLException {
        List<Student> studentList = studentDao.getAll();
        List<Course> courseList = courseDao.getAll();
        Map<String, String> outMap = new HashMap();
        int courseNameId = courseDao.getAll()
                .stream()
                .filter(course -> course.getCourseName()
                        .equals(courseName))
                .mapToInt(c -> c.getCourseId())
                .max()
                .getAsInt();
        List<StudentCourse> filteredList = studentCourseDao.getAll()
                .stream()
                .filter(e -> e.getCourseId() == courseNameId)
                .collect(Collectors.toList());
        filteredList.forEach(e -> {
            outMap.put(studentList.stream()
                            .filter(student -> student.getStudentId() == e.getStudentId())
                            .map(student -> student.getFirstName() + " " + student.getLastName())
                            .collect(Collectors.joining()),
                    courseList.stream()
                            .filter(c -> c.getCourseId() == e.getCourseId())
                            .map(c -> c.getCourseName())
                            .collect(Collectors.joining()));
        });
        return outMap;
    }
}
