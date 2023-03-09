package ru.filimonov.steps.java.fox.generators;

import ru.filimonov.steps.java.fox.dao.CourseDao;
import ru.filimonov.steps.java.fox.dao.CourseJdbcDao;
import ru.filimonov.steps.java.fox.dto.Course;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CoursesGenerator {
    private static final String UNDERSCORE = "_";
    private static final String INFO = "Courses created successfully...";
    private static final Logger logger = Logger.getLogger(CoursesGenerator.class.getName());
    private static final CourseDao<Course> courseDao = new CourseJdbcDao();

    public void generateData(String COURSES) throws IOException {
        try (Stream<String> coursesStream = Files.lines(Paths.get(COURSES))) {
            coursesStream.map(s -> s.split(UNDERSCORE))
                    .forEach(strings -> courseDao.saveCourse(new Course(strings[0], strings[1])));
        }
        logger.info(INFO);
    }
}
