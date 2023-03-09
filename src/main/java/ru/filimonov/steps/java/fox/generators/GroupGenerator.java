package ru.filimonov.steps.java.fox.generators;

import ru.filimonov.steps.java.fox.dao.GroupDao;
import ru.filimonov.steps.java.fox.dao.GroupJdbcDao;
import ru.filimonov.steps.java.fox.dto.Group;

import java.sql.SQLException;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class GroupGenerator {
    private static final Random rnd = new Random();
    private static final Logger logger = Logger.getLogger(GroupGenerator.class.getName());
    private static final String INFO = "Groups created successfully...";

    private static final GroupDao<Group,String,Integer> groupDao = new GroupJdbcDao();

    public void createGroups() {
        Stream.generate(this::getGroupName)
                .limit(10)
                .map(Group::new)
                .forEach(group -> groupDao.saveGroup(group));
        logger.info(INFO);
    }

    private String getGroupName() {
        char chOne = (char) ('A' + rnd.nextInt(26));
        char chTwo = (char) ('A' + rnd.nextInt(26));
        char chThree = (char) ('0' + rnd.nextInt(10));
        char chFour = (char) ('0' + rnd.nextInt(10));
        return String.format("%c%c-%c%c", chOne, chTwo, chThree, chFour);
    }


}
