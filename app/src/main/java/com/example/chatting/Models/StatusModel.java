package com.example.chatting.Models;

public class StatusModel {

    String name, image, uid;

    long lastUpdated;

    public StatusModel(String name, String image, long lastUpdated, String uid) {
        this.name = name;
        this.image = image;
        this.lastUpdated = lastUpdated;
        this.uid = uid;
    }



    public StatusModel() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public String getUid() {
        return uid;
    }


}
