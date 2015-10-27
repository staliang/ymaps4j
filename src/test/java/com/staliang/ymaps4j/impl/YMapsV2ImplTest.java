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
public class YMapsV2ImplTest {

    public static final String MOCK_CITY = "MockCity";

    public static final String SAMARA = "Samara";
    public static final String PETROZAVODSK = "Petrozavodsk";

    private static YMaps yMaps = YMapsFactory.getMaps(YMapsVersion.V2, Locale.ENGLISH);

    @Test
    public void testGeolocation() throws YMapsException {
        assertNotNull(yMaps.geolocation());
    }

    @Test(expected = YMapsException.class)
    public void testGeocodeException() throws YMapsException {
        yMaps.geocode(MOCK_CITY);
    }

    @Test
    public void testGeocode() throws YMapsException {
        assertEquals(new Coordinate(34.359688, 61.789036), yMaps.geocode(PETROZAVODSK));
    }

    @Test
    public void testReverseGeocode() throws YMapsException {
        assertEquals(PETROZAVODSK, yMaps.geocode(new Coordinate(34.36, 61.79)).getLocality());
    }

    @Test
    public void testRoute() throws YMapsException {
        Route route = yMaps.route(PETROZAVODSK, SAMARA);
        assertNotNull(route);
    }

    @Test(expected = YMapsException.class)
    public void testRouteException() throws YMapsException {
        yMaps.route(MOCK_CITY, PETROZAVODSK);
    }
}
