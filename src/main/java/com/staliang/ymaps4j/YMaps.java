package com.staliang.ymaps4j;

import com.staliang.ymaps4j.beans.Coordinate;
import com.staliang.ymaps4j.beans.ReverseGeocode;
import com.staliang.ymaps4j.beans.Geolocation;
import com.staliang.ymaps4j.beans.Route;
import com.staliang.ymaps4j.exception.YMapsException;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public interface YMaps {

    /**
     * Detecting the user's location
     *
     * @return
     * @throws YMapsException
     */
    Geolocation geolocation();

    /**
     * Find an object's name based on its coordinates (reverse geocoding)
     *
     * @param coordinate
     * @return
     */
    ReverseGeocode geocode(Coordinate coordinate);

    /**
     * Determine an object's geographical coordinates based on its name (forward geocoding)
     *
     * @param location
     * @return
     */
    Coordinate geocode(String location);

    /**
     * Build driving routes by locations
     *
     * @param locations
     * @return
     * @throws YMapsException
     */
    Route route(String... locations);

    /**
     * Build driving routes by coordinates
     *
     * @param coordinates
     * @return
     * @throws YMapsException
     */
    Route route(Coordinate... coordinates);
}
