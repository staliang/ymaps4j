package com.staliang.ymaps4j.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alexandr_Badin on 28.10.2015
 */
public class UrlBuilder {

    private final String url;

    private Map<UrlToken, Object> map = new HashMap<>();

    public UrlBuilder(String url) {
        this.url = url;
    }

    public UrlBuilder add(UrlToken urlToken, Object value) {
        map.put(urlToken, value);
        return this;
    }

    public String build() {
        return url + "?" + map.keySet().stream().map(e -> String.format("%s=%s", e, map.get(e)).toLowerCase()).collect(Collectors.joining("&"));
    }
}
