package com.staliang.ymaps4j.beans;

/**
 * Created by Alexandr_Badin on 13.08.2015
 */
public class Action {

    private final String value;
    private final String text;

    public Action(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Action{" +
                "value='" + value + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
