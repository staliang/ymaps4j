package com.staliang.ymaps4j.exception;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
public class YMapsException extends Exception {

    public YMapsException(Exception e) {
        super(e);
    }

    public YMapsException(String message) {
        super(message);
    }

}
