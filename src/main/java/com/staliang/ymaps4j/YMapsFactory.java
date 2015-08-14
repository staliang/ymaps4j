package com.staliang.ymaps4j;

import com.staliang.ymaps4j.exception.YMapsException;
import com.staliang.ymaps4j.impl.v2.YMapsV2Impl;

import java.util.Locale;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public abstract class YMapsFactory {

    public static YMaps getMaps(YMapsVersion mapsVersion) throws YMapsException {
        return getMaps(mapsVersion, Locale.getDefault());
    }

    public static YMaps getMaps(YMapsVersion mapsVersion, Locale locale) throws YMapsException {
        switch (mapsVersion){
            case V2:
                return new YMapsV2Impl(locale);
            default:
                throw new YMapsException("Unknown version of Yandex Maps");
        }
    }
}
