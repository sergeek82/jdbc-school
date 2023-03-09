package ru.filimonov.steps.java.fox.generators;

import ru.filimonov.steps.java.fox.dao.StudentCourseJdbcDao;
import ru.filimonov.steps.java.fox.dto.Course;
import ru.filimonov.steps.java.fox.dto.Student;
import ru.filimonov.steps.java.fox.dto.StudentCourse;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentCourseGenerator {
    private static final Logger logger = Logger.getLogger(StudentCourseGenerator.class.getName());
    private static final Random random = new Random();
    private static final StudentCourseJdbcDao jdbcDao = new StudentCourseJdbcDao();
    private static final String END_INFO = "Courses were assigned to students...";
    private static final String START_INFO = "Starting course assignment...";

    public void assignStudentCourse(List<Student> studentList, List<Course> courseList) {
        logger.info(START_INFO);
        studentList.forEach(student -> {
            int randomCourseAmount = random.nextInt(3) + 1;
            IntStream.range(0, randomCourseAmount)
                    .mapToObj(course -> {
                        int randomCourse = random.nextInt(10);
                        return new StudentCourse(student.getStudentId(), courseList.get(randomCourse)
                                .getCourseId());
                    })
                    .collect(Collectors.toSet())
                    .forEach(studentCourse -> {
                        try {
                            jdbcDao.saveStudentCourse(studentCourse);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
        });
        logger.info(END_INFO);
    }
}
