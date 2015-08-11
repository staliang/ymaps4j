package com.staliang.ymaps4j.impl;

import com.staliang.ymaps4j.Coordinate;
import com.staliang.ymaps4j.YMaps;
import com.staliang.ymaps4j.YMapsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public class YMapsImplTest {

    @Test
    public void testGeocode() throws YMapsException {
        YMaps yMaps = new YMapsImpl();

        assertEquals(new Coordinate(37.620393, 55.75396), yMaps.geocode("Moscow"));
    }

    @Test
    public void testGeocode1() throws YMapsException {
        YMaps yMaps = new YMapsImpl();

        assertEquals("Красная площадь", yMaps.geocode(new Coordinate(37.620393, 55.75396)));
    }
}
