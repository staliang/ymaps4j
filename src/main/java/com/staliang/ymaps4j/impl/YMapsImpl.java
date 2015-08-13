package com.staliang.ymaps4j.impl;

import com.staliang.ymaps4j.*;
import com.staliang.ymaps4j.json.types.Geocode;
import com.staliang.ymaps4j.json.types.Geometry;
import com.staliang.ymaps4j.util.JSONUtil;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
class YMapsImpl implements YMaps {

    private static final Logger logger = Logger.getLogger(YMapsImpl.class);

    private final Locale locale;
    private final YMapsHttpClient client;

    private String token;
    private Geolocation geolocation;
    private CoordinatesOrder coordinatesOrder;
    private boolean initialised = false;

    public YMapsImpl() {
        this(Locale.getDefault());
    }

    public YMapsImpl(Locale locale) {
        this.locale = locale;
        this.client = new YMapsHttpClient();
    }

    public void init() throws YMapsException {
        try {
            URI uri = new URI(String.format("https://api-maps.yandex.ru/2.0-stable/?load=package.full&lang=%s", locale));
            String getResult = client.get(uri);

            Map<String, String> stringMap = new HashMap<>();
            String[] strings = getResult.split("project_data");
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].startsWith("[")) {
                    String[] split = strings[i].split("=");
                    stringMap.put(split[0].trim(), split[1].trim());
                }
            }

            String rawToken = stringMap.get("[\"token\"]");
            token = rawToken.substring(1, rawToken.length() - 2);

            String rawGeolocation = stringMap.get("[\"geolocation\"]");
            geolocation = Adapter.convert(JSONUtil.fromJSON(rawGeolocation.substring(0, rawGeolocation.length() - 1), com.staliang.ymaps4j.json.types.Geolocation.class));

            String rawCoordinatesOrder = stringMap.get("[\"coordinatesOrder\"]");
            coordinatesOrder = CoordinatesOrder.getBySysName(rawCoordinatesOrder.substring(1, rawCoordinatesOrder.length() - 2));

            initialised = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new YMapsException(e);
        }
    }

    private void checkInit() throws YMapsException {
        if (!initialised) {
            throw new YMapsException("YMaps must be initialised. For initialization use method init().");
        }
    }

    public Geolocation geolocation() throws YMapsException {
        checkInit();

        return geolocation;
    }

    private Geocode requestGeocode(String location, CoordinatesOrder coordinatesOrder) throws YMapsException {
        try {
            String s = URLEncoder.encode(location, "UTF-8");
            URI uri = new URI("https://api-maps.yandex.ru/services/search/v1/?text=" + s + "&format=json&rspn=0&lang=" + locale + "&results=Geocode&token=" + token + "&type=geo&properties=addressdetails&geocoder_sco="+ coordinatesOrder.getSysName()+"&origin=jsapi2Geocoder");
            return JSONUtil.fromJSON(client.get(uri), Geocode.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new YMapsException(e);
        }
    }

    private Geocode requestGeocode(String location) throws YMapsException {
        return requestGeocode(location, coordinatesOrder);
    }

    public Coordinate geocode(String location) throws YMapsException {
        checkInit();

        Geocode geocode = requestGeocode(location);
        if (geocode.getFeatures().isEmpty()) {
            throw new YMapsException("The location not found");
        }
        Geometry geometry = geocode.getFeatures().get(0).getGeometry();
        return new Coordinate(Double.valueOf(geometry.getCoordinates().get(0).toString()), Double.valueOf(geometry.getCoordinates().get(1).toString()));
    }

    public String geocode(Coordinate coordinate) throws YMapsException {
        checkInit();

        String location = String.format("%s %s", coordinate.getLongitude(), coordinate.getLatitude());
        Geocode geocode = requestGeocode(location, CoordinatesOrder.LONGLAT);
        if (geocode.getFeatures().isEmpty()) {
            throw new YMapsException("The location not found");
        }
        return geocode.getFeatures().get(0).getProperties().getGeocoderMetaData().getText();
    }

    public Route route(String... locations) throws YMapsException {
        checkInit();

        try {
            Coordinate[] coordinates = new Coordinate[locations.length];
            for (int i = 0; i < locations.length; i++) {
                coordinates[i] = geocode(locations[i]);
            }
            return route(coordinates);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new YMapsException(e);
        }
    }

    public Route route(Coordinate... coordinates) throws YMapsException {
        checkInit();

        try {
            String string = Stream.of(coordinates)
                    .map(point -> String.format("%s%%2C%s", point.getLongitude(), point.getLatitude()))
                    .collect(Collectors.joining("~"));
            String url = String.format("https://api-maps.yandex.ru/services/route/2.0/?rll=%s&lang=%s&token=%s&results=1&rtm=atm", string, locale, token);
            return Adapter.convert(JSONUtil.fromJSON(client.get(new URI(url)), com.staliang.ymaps4j.json.types.Route.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new YMapsException(e);
        }
    }
}
