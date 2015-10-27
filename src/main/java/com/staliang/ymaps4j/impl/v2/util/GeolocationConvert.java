package com.staliang.ymaps4j.impl.v2.util;

import com.staliang.ymaps4j.beans.Coordinate;
import com.staliang.ymaps4j.beans.Geolocation;

/**
 * Created by Alexandr_Badin on 27.10.2015
 */
public class GeolocationConvert {

    public static Geolocation convert(com.staliang.ymaps4j.json.types.Geolocation source) {
        Geolocation geolocation = new Geolocation(source.getCity(), source.getRegion(),
                source.getCountry(), new Coordinate(source.getLongitude(), source.getLatitude()));
        return geolocation;
    }
}
