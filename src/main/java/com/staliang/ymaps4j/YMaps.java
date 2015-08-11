package com.staliang.ymaps4j;

import com.staliang.ymaps4j.json.types.Route;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public interface YMaps {

    /**
    * Определение название объекта по его координатам
     * @param coordinate
    * @return
            */
    String geocode(Coordinate coordinate) throws YMapsException;

    /**
     * Определение координаты объекта по его названию
     * @param location
     * @return
     */
    Coordinate geocode(String location) throws YMapsException;

    /**
     * Проложить маршрут по названиям точек маршрута
     * @param locations
     * @return
     * @throws YMapsException
     */
    Route route(String... locations) throws YMapsException;

    /**
     * Проложить маршрут по координатам точек маршрута
     * @param coordinates
     * @return
     * @throws YMapsException
     */
    Route route(Coordinate... coordinates) throws YMapsException;
}
