package ru.filimonov.steps.java.fox;

import ru.filimonov.steps.java.fox.dao.*;
import ru.filimonov.steps.java.fox.formatter.DataFormatter;
import ru.filimonov.steps.java.fox.formatter.GroupFormat;
import ru.filimonov.steps.java.fox.formatter.StudentCourseFormat;
import ru.filimonov.steps.java.fox.generators.*;
import ru.filimonov.steps.java.fox.services.GroupService;
import ru.filimonov.steps.java.fox.services.StudentService;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

public class Runner {
    private static final Logger logger = Logger.getLogger(Runner.class.getName());
    private static final String SQL_SCRIPT = "src/main/resources/script.sql";
    private static final String FIRS_NAMES = "src/main/resources/firstNames.txt";
    private static final String LAST_NAMES = "src/main/resources/lastNames.txt";
    private static final String COURSES = "src/main/resources/courses.txt";
    private static final TableGenerator TABLE_GENERATOR = new TableGenerator();
    private static final StudentGenerator STUDENT_GENERATOR = new StudentGenerator();
    private static final StudentCourseGenerator STUDENT_COURSE_GENERATOR = new StudentCourseGenerator();
    private static final StudentJdbcDao studentDao = new StudentJdbcDao();
    private static final CourseJdbcDao courseDao = new CourseJdbcDao();
    private static final DataFormatter<String, Map<String, String>> studentCourseFormat = new StudentCourseFormat();
    private static final GroupService groupService = new GroupService(new GroupJdbcDao(), new GroupFormat());
    private static final StudentService studentService = new StudentService(new StudentCourseJdbcDao(),
            new StudentJdbcDao(), new CourseJdbcDao(), new StudentCourseFormat());

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//        tableService.createTable(SQL_SCRIPT);
//        groupService.createGroups();
//        studentService.createStudents(FIRS_NAMES, LAST_NAMES);
//        courseService.generateData(COURSES);
//        studentCourseService.assignStudentCourse(studentDao.getAll(), courseDao.getAll());
//        logger.info(studentService.findStudentsByCourse("Psychology basic"));
//        logger.info(groupService.findByCount(14));
    }
}
