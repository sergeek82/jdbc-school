package ru.filimonov.steps.java.fox.generators;

import ru.filimonov.steps.java.fox.dao.TableJdbcDao;
import ru.filimonov.steps.java.fox.dto.Table;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableGenerator {
    private static final Logger logger = Logger.getLogger(TableGenerator.class.getName());
    private static final String INFO = "Tables created successfully...";

    public void createTable(String sqlScript) throws IOException, SQLException {
        try (Stream<String> sqlStream = Files.lines(Paths.get(sqlScript))) {
            Table table = new Table(sqlStream.collect(Collectors.joining()));
            new TableJdbcDao().saveTable(table);
        }
        logger.info(INFO);
    }
}
