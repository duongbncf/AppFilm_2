package com.example.appfilm_2.model;

public class Comming {
    private String ivlink;
    private String ivlinkShare;
    private String commingTime;
    private String name;
    private String description;

//    public Comming(String ivlink, String ivlinkShare, String commingTime, String name, String description) {
//        this.ivlink = ivlink;
//        this.ivlinkShare = ivlinkShare;
//        this.commingTime = commingTime;
//        this.name = name;
//        this.description = description;
//    }

    public String getIvlink() {
        return ivlink;
    }

    public void setIvlink(String ivlink) {
        this.ivlink = ivlink;
    }

    public String getIvlinkShare() {
        return ivlinkShare;
    }

    public void setIvlinkShare(String ivlinkShare) {
        this.ivlinkShare = ivlinkShare;
    }

    public String getCommingTime() {
        return commingTime;
    }

    public void setCommingTime(String commingTime) {
        this.commingTime = commingTime;
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
}
