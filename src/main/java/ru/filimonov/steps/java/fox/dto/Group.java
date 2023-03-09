package ru.filimonov.steps.java.fox.dto;

public class Group {
    private int groupId;
    private String groupName;

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Group(String group_Name) {
        this.groupName = group_Name;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
