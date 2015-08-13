package com.staliang.ymaps4j;

import com.staliang.ymaps4j.beans.Coordinate;
import com.staliang.ymaps4j.beans.Geolocation;
import com.staliang.ymaps4j.beans.Route;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public interface YMaps {

    /**
     * Initialize YMaps
     */
    void init() throws YMapsException;

    /**
     * Detecting the user's location
     *
     * @return
     * @throws YMapsException
     */
    Geolocation geolocation() throws YMapsException;

    /**
     * Find an object's name based on its coordinates (reverse geocoding)
     *
     * @param coordinate
     * @return
     */
    String geocode(Coordinate coordinate) throws YMapsException;

    /**
     * Determine an object's geographical coordinates based on its name (forward geocoding)
     *
     * @param location
     * @return
     */
    Coordinate geocode(String location) throws YMapsException;

    /**
     * Build driving routes by locations
     *
     * @param locations
     * @return
     * @throws YMapsException
     */
    Route route(String... locations) throws YMapsException;

    /**
     * Build driving routes by coordinates
     *
     * @param coordinates
     * @return
     * @throws YMapsException
     */
    Route route(Coordinate... coordinates) throws YMapsException;
}
