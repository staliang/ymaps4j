package com.staliang.ymaps4j.beans;

import java.util.List;

/**
 * Created by Alexandr_Badin on 13.08.2015
 */
public class Route {


    private String type;
    private Boolean hasTolls;
    private Distance distance;
    private Duration duration;
    private Duration durationInTraffic;
    private List<Segment> segments;

    public void setType(String type) {
        this.type = type;
    }

    public void setHasTolls(Boolean hasTolls) {
        this.hasTolls = hasTolls;
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

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
