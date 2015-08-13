package com.staliang.ymaps4j;

/**
 * Created by Alexandr_Badin on 13.08.2015
 */
public class Duration {

    private final Double value;
    private final String text;

    public Duration(Double value, String text) {
        this.value = value;
        this.text = text;
    }

    public Double getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "value=" + value +
                ", text='" + text + '\'' +
                '}';
    }
}
