package com.staliang.ymaps4j.impl;

/**
 * Created by Alexandr_Badin on 12.08.2015
 */
public enum CoordinatesOrder {

    LONGLAT("longlat"),
    LATLONG("latlong");

    private final String sysName;

    CoordinatesOrder(String sysName){
        this.sysName = sysName;
    }

    public static CoordinatesOrder getBySysName(String sysName) {
        for (CoordinatesOrder coordinatesOrder : values()) {
            if (coordinatesOrder.sysName.equals(sysName)) {
                return coordinatesOrder;
            }
        }
        throw new IllegalArgumentException("Not found CoordinatesOrder by name");
    }

    public String getSysName() {
        return sysName;
    }
}
