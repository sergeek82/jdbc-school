package ru.filimonov.steps.java.fox.generators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StudentGeneratorTest {
    private static final String FIRST = "src/main/resources/firstNames.txt";
    private static final String LAST = "src/main/resources/lastNames.txt";
    private static final int AMOUNT = 20;
    private StudentGenerator studentGenerator;

    @BeforeEach
    void init() {
        studentGenerator = new StudentGenerator();
    }

    @Test
    void processShouldCheckNamesListsSize() throws IOException {
        studentGenerator.createStudents(FIRST, LAST);
        assertEquals(AMOUNT, studentGenerator.getFirstNames()
                .size());
        assertEquals(AMOUNT, studentGenerator.getLastNames()
                .size());
    }
}