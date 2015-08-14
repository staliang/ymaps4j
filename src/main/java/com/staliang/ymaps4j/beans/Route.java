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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHasTolls() {
        return hasTolls;
    }

    public void setHasTolls(Boolean hasTolls) {
        this.hasTolls = hasTolls;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration getDurationInTraffic() {
        return durationInTraffic;
    }

    public void setDurationInTraffic(Duration durationInTraffic) {
        this.durationInTraffic = durationInTraffic;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}
