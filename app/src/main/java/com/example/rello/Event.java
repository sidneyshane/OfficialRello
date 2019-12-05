package com.example.rello;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class Event {
    private String name;
    private String description;
    private boolean hiddenFlag;
    private String eventDate;

    public Event(){}

    public Event(String aName, String aDate, String aDesc, boolean aFlag) {
        setName(aName);
        setEventDate(aDate);
        setDescription(aDesc);
        setHiddenFlag(aFlag);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHiddenFlag() {
        return hiddenFlag;
    }

    public void setHiddenFlag(boolean hiddenFlag) {
        this.hiddenFlag = hiddenFlag;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
