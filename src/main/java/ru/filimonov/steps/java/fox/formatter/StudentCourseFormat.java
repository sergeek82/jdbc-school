package ru.filimonov.steps.java.fox.formatter;

import java.util.Map;

public class StudentCourseFormat implements DataFormatter<String, Map<String, String>> {
    private static final String HEAD = "\nSTUDENTS              | COURSES NAME\n";
    private static final String UNDERLINE = "------------------------------------\n";
    private static final String SEPARATOR = "|";
    private static final String OUT_FORMAT = "%-22s%-2s%s%n";

    @Override
    public String format(Map<String, String> entity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HEAD)
                .append(UNDERLINE);
        entity.forEach((k, v) -> stringBuilder.append(String.format(OUT_FORMAT, k, SEPARATOR, v)));
        return stringBuilder.toString();
    }
}
