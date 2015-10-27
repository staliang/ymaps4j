package com.staliang.ymaps4j.impl.v2.util;

import com.staliang.ymaps4j.beans.Location;
import com.staliang.ymaps4j.json.types.Feature;
import com.staliang.ymaps4j.json.types.Geocode;

/**
 * Created by Alexandr_Badin on 27.10.2015
 */
public final class LocationConvert {

    public static Location convert(Geocode geocode) {
        Location result = new Location();
        for (Feature feature : geocode.getFeatures()) {
            String kind = feature.getProperties().getGeocoderMetaData().getKind();
            switch (kind) {
                case "house":
                    result.setHouse(feature.getProperties().getName());
                    break;
                case "street":
                    result.setStreet(feature.getProperties().getName());
                    break;
                case "district":
                    result.setDistrict(feature.getProperties().getName());
                    break;
                case "locality" :
                    result.setLocality(feature.getProperties().getName());
                    break;
                case "area" :
                    result.setArea(feature.getProperties().getName());
                    break;
                case "province" :
                    result.setProvince(feature.getProperties().getName());
                    break;
                case "country":
                    result.setCountry(feature.getProperties().getName());
                    break;
            }
        }
        return result;
    }
}
