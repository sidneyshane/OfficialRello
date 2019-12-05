package com.example.rello;

import java.util.List;

public class Group {
    private String groupName;
    private String groupDescription;
    private List<String> groupMembers;

    public Group(){}

    public Group(String aName, String aDescription) {
        setGroupName(aName);
        setGroupDescription(aDescription);
    }

    public Group(String aName, String aDescription, List<String> members) {
        setGroupName(aName);
        setGroupDescription(aDescription);
        setGroupMembers(members);
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<String> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
