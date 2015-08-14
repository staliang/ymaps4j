package com.staliang.ymaps4j.impl;

import com.staliang.ymaps4j.YMaps;
import com.staliang.ymaps4j.YMapsFactory;
import com.staliang.ymaps4j.beans.Coordinate;
import com.staliang.ymaps4j.beans.Route;
import com.staliang.ymaps4j.YMapsVersion;
import com.staliang.ymaps4j.exception.YMapsException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public class YMapsV2Test {

    public static final String MOSCOW = "Moscow";
    public static final String SAMARA = "Samara";

    public static final String MOCK_CITY = "MockCity";

    private static YMaps yMaps;

    @BeforeClass
    public static void before() throws YMapsException {
        yMaps = YMapsFactory.getMaps(YMapsVersion.V2, Locale.ENGLISH);
        yMaps.init();
    }

    @Test
    public void testGeolocation() throws YMapsException {
        assertNotNull(yMaps.geolocation());
    }

    @Test(expected = YMapsException.class)
    public void testGeocodeException() throws YMapsException {
        yMaps.geocode(MOCK_CITY);
    }

    @Test
    public void testGeocodeForward() throws YMapsException {
        assertEquals(new Coordinate(37.620393, 55.75396), yMaps.geocode(MOSCOW));
        assertEquals(new Coordinate(50.101801, 53.195533), yMaps.geocode(SAMARA));
    }

    @Test
    public void testGeocodeReverse() throws YMapsException {
        assertEquals("Russian Federation, Moscow, Red Square", yMaps.geocode(new Coordinate(37.620393, 55.75396)));
        assertEquals("Russian Federation, Samara, ploshchad Kuybysheva", yMaps.geocode(new Coordinate(50.101801, 53.195533)));
    }

    @Test
    public void testRoute() throws YMapsException {
        Route route = yMaps.route(MOSCOW, SAMARA);
        assertNotNull(route);
    }

    @Test(expected = YMapsException.class)
    public void testRouteException() throws YMapsException {
        yMaps.route(MOCK_CITY, MOSCOW);
    }
}
