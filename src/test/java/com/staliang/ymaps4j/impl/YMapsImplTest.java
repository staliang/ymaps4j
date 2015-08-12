package com.staliang.ymaps4j.impl;

import com.staliang.ymaps4j.Coordinate;
import com.staliang.ymaps4j.YMaps;
import com.staliang.ymaps4j.YMapsException;
import com.staliang.ymaps4j.json.types.Route;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public class YMapsImplTest {

    private YMaps yMaps;

    @Before
    public void before() throws YMapsException {
        yMaps = new YMapsImpl(Locale.forLanguageTag("ru"));
        yMaps.init();
    }

    @Test
    public void testGeolocation() throws YMapsException {
        yMaps.geolocation();
    }

    @Test
    public void testGeocode() throws YMapsException {
        assertEquals(new Coordinate(37.620393, 55.75396), yMaps.geocode("Moscow"));
    }

    @Test
    public void testGeocode1() throws YMapsException {
        assertEquals("Red Square", yMaps.geocode(new Coordinate(37.620393, 55.75396)));
    }

    @Test
    public void testRoute() throws YMapsException {
        Route route = yMaps.route("Moscow", "Saint-Peterburg");
    }
}
