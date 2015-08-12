package com.staliang.ymaps4j;

/**
 * Created by Alexandr_Badin on 12.08.2015
 */
public enum PointType {

    WAY_POINT("wayPoint"),
    VIA_POINT("viaPoint");

    private final String sysName;

    PointType(String sysName) {
        this.sysName = sysName;
    }
}
