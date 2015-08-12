package com.staliang.ymaps4j.impl;

import com.staliang.ymaps4j.YMaps;

import java.util.Locale;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public abstract class YMapsFactory {

    public static YMaps getInstance() {
        return new YMapsImpl();
    }

    public static YMaps getInstance(Locale locale) {
        return new YMapsImpl(locale);
    }
}
