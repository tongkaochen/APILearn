package com.tifone.ui.xmlparser;

/**
 * Created by tongkao.chen on 2018/4/24.
 */

public class XmlBean {
    private String name;
    private String type;
    private int value;
    public XmlBean() {

    }
    public XmlBean (String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "name = " + name +
                ", type = " + type +
                ", value = " + value;
    }
}
