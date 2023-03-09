package ru.filimonov.steps.java.fox.dao;

import ru.filimonov.steps.java.fox.dto.Group;
import ru.filimonov.steps.java.fox.utils.ConnectionManager;
import ru.filimonov.steps.java.fox.utils.PropertiesUtil;

import java.sql.*;
import java.util.*;

public class GroupJdbcDao implements GroupDao<Group, String, Integer> {
    private static final String GROUPS_INSERT = "db.group.insertion";
    private static final String GROUPS_QUERY = "db.group.get.all";
    private static final String FIND = "db.groups.find";

    public void saveGroup(Group group) {
        try (
                Connection conn = ConnectionManager.open();
                Statement writeInto = conn.createStatement()
        ) {
            writeInto.executeUpdate(String.format(PropertiesUtil.get(GROUPS_INSERT), group.getGroupName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> getAll() throws SQLException {
        List<Group> groupList = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.open();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(PropertiesUtil.get(GROUPS_QUERY))
        ) {
            while (resultSet.next()) {
                Group group = new Group(resultSet.getString(2));
                group.setGroupId(resultSet.getInt(1));
                groupList.add(group);
            }
        }
        return groupList;
    }

    public Map<String, Integer> getGroupStudents(Integer count) throws SQLException {
        Map<String, Integer> map = new LinkedHashMap<>();
        try (
                Connection connection = ConnectionManager.open();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(String.format(PropertiesUtil.get(FIND), count))
        ) {
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getInt(2));
            }
        }
        return map;
    }
}