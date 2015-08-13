package com.staliang.ymaps4j;

/**
 * Created by Alexandr_Badin on 13.08.2015
 */
public class Segment {
    private String street;
    private Action action;
    private Integer angle;
    private Distance distance;
    private Duration duration;
    private Duration durationInTraffic;
    private String text;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDurationInTraffic(Duration durationInTraffic) {
        this.durationInTraffic = durationInTraffic;
    }

    public void setText(String text) {
        this.text = text;
    }
}
