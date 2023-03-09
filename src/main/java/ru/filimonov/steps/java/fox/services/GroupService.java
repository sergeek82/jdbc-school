package ru.filimonov.steps.java.fox.services;

import ru.filimonov.steps.java.fox.dao.GroupDao;
import ru.filimonov.steps.java.fox.dto.Group;
import ru.filimonov.steps.java.fox.formatter.DataFormatter;

import java.sql.SQLException;
import java.util.Map;

public class GroupService {
    GroupDao<Group, String, Integer> groupDao;
    DataFormatter<String, Map<String, Integer>> groupFormatter;

    public GroupService(GroupDao<Group, String, Integer> groupDao,
                        DataFormatter<String, Map<String, Integer>> groupFormatter) {
        this.groupDao = groupDao;
        this.groupFormatter = groupFormatter;
    }

    public String findByCount(Integer count) throws SQLException {
        return groupFormatter.format(groupDao.getGroupStudents(count));
    }
}
