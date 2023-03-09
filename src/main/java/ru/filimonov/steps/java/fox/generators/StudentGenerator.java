package ru.filimonov.steps.java.fox.generators;

import ru.filimonov.steps.java.fox.dao.StudentJdbcDao;
import ru.filimonov.steps.java.fox.dto.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StudentGenerator {

    private Student student;
    private List<String> firstNames;
    private List<String> lastNames;
    private static final Random random = new Random();
    private static final Logger logger = Logger.getLogger(StudentGenerator.class.getName());
    private static final String INFO = "Students created successfully...";

    public void createStudents(String FIRS_NAMES, String LAST_NAMES) throws IOException {
        readNames(FIRS_NAMES, LAST_NAMES);
        IntStream.range(0, 200)
                .mapToObj(index -> {
                    int gId = random.nextInt(10) + 1;
                    int firstNameIndex = random.nextInt(20);
                    int lastNameIndex = random.nextInt(20);
                    student = new Student(gId, firstNames.get(firstNameIndex), lastNames.get(lastNameIndex));
                    return student;
                })
                .forEach(student -> {
                    try {
                        new StudentJdbcDao().saveStudent(student);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        logger.info(INFO);
    }

    private void readNames(String firstFile, String secondFile) throws IOException {
        try (Stream<String> firstNamesStream = Files.lines(Paths.get(firstFile));
             Stream<String> lastNamesStream = Files.lines(Paths.get(secondFile))) {
            firstNames = firstNamesStream.collect(Collectors.toList());
            lastNames = lastNamesStream.collect(Collectors.toList());
        }
    }

    public List<String> getFirstNames() {
        return firstNames;
    }

    public List<String> getLastNames() {
        return lastNames;
    }
}
