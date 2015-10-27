package com.staliang.ymaps4j.impl.v2.util;

import com.staliang.ymaps4j.beans.*;
import com.staliang.ymaps4j.exception.YMapsException;
import com.staliang.ymaps4j.json.types.Feature___;
import com.staliang.ymaps4j.json.types.PathMetaData;
import com.staliang.ymaps4j.json.types.RouteMetaData;
import com.staliang.ymaps4j.json.types.SegmentMetaData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr_Badin on 13.08.2015
 */
public abstract class RouteConvert {

    public static Route convert(com.staliang.ymaps4j.json.types.Route source) {
        if (source.getData().getFeatures().isEmpty()) {
            throw new YMapsException("Can't build route");
        }

        Route route = new Route();

        RouteMetaData routeMetaData = source.getData().getFeatures().get(0).getProperties().getRouteMetaData();
        route.setType(routeMetaData.getType());
        route.setHasTolls(routeMetaData.getHasTolls());

        PathMetaData pathMetaData = source.getData().getFeatures().get(0).getFeatures().get(0).getProperties().getPathMetaData();
        route.setDistance(new Distance(pathMetaData.getDistance().getValue(), pathMetaData.getDistance().getText()));
        route.setDuration(new Duration(pathMetaData.getDuration().getValue(), pathMetaData.getDuration().getText()));
        route.setDurationInTraffic(new Duration(pathMetaData.getDurationInTraffic().getValue(), pathMetaData.getDurationInTraffic().getText()));

        List<Segment> segments = new ArrayList<>();
        List<Feature___> features = source.getData().getFeatures().get(0).getFeatures().get(0).getFeatures();
        for (Feature___ feature : features) {
            Segment segment = new Segment();

            SegmentMetaData segmentMetaData = feature.getProperties().getSegmentMetaData();
            segment.setStreet(segmentMetaData.getStreet());
            segment.setAction(new Action(segmentMetaData.getAction().getValue(), segmentMetaData.getAction().getText()));
            segment.setAngle(segmentMetaData.getAngle());
            segment.setDistance(new Distance(segmentMetaData.getDistance().getValue(), segmentMetaData.getDistance().getText()));
            segment.setDuration(new Duration(segmentMetaData.getDuration().getValue(), segmentMetaData.getDuration().getText()));
            segment.setDurationInTraffic(new Duration(segmentMetaData.getDurationInTraffic().getValue(), segmentMetaData.getDurationInTraffic().getText()));
            segment.setText(segmentMetaData.getText());

            segments.add(segment);
        }
        route.setSegments(segments);

        return route;
    }
}
