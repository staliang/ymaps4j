package com.staliang.ymaps4j.impl.v2.util;

import com.staliang.ymaps4j.beans.Coordinate;
import com.staliang.ymaps4j.beans.UserLocation;

/**
 * Created by Alexandr_Badin on 27.10.2015
 */
public class UserLocationConvert {

    public static UserLocation convert(com.staliang.ymaps4j.json.types.Geolocation source) {
        UserLocation userLocation = new UserLocation(source.getCity(), source.getRegion(),
                source.getCountry(), new Coordinate(source.getLongitude(), source.getLatitude()));
        return userLocation;
    }
}
