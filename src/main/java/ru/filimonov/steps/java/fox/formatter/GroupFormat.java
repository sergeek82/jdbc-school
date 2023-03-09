package ru.filimonov.steps.java.fox.formatter;

import java.util.Map;

public class GroupFormat implements DataFormatter<String, Map<String, Integer>> {
    private static final String OUT_FORMAT = "%7s%5s%8d\n";
    private static final String HEAD = "\nGROUP NAME | GROUP MEMBERS\n";
    private static final String UNDERLINE = "--------------------------\n";
    private static final String SEPARATOR = "|";

    public String format(Map<String, Integer> groupMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEAD)
                .append(UNDERLINE);
        groupMap.forEach((k, v) -> sb.append(String.format(OUT_FORMAT, k, SEPARATOR, v)));
        return sb.toString();
    }
}
