package com.tifone.ui.json;

/**
 * Created by tongkao.chen on 2018/4/19.
 */

public class JsonBean {
    private String name;
    private String summary;
    private String description;
    private String address;
    private int distance;
    public JsonBean(String name, String summary, String description, String address, int distance) {
        this.name = name;
        this.summary = summary;
        this.description = description;
        this.address = address;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "name: " + name +
                " summary: " + summary +
                " description: " + description +
                " address: " + address +
                " distance: " + distance;
    }
}
