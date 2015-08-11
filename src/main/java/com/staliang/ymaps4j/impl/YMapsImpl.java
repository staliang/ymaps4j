package com.staliang.ymaps4j.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staliang.ymaps4j.Coordinate;
import com.staliang.ymaps4j.YMaps;
import com.staliang.ymaps4j.YMapsException;
import com.staliang.ymaps4j.json.types.Geocode;
import com.staliang.ymaps4j.json.types.Geometry;
import com.staliang.ymaps4j.json.types.Route;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Alexandr_Badin on 11.08.2015.
 */
class YMapsImpl implements YMaps {

    private static final Logger LOGGER = Logger.getLogger(YMapsImpl.class);

    private String token;
    private HttpClient client;
    private Map<String, String> params = new HashMap<String, String>();

    private DefaultHttpClient prepareHttpClient() {
        DefaultHttpClient client = new DefaultHttpClient();
        client.addRequestInterceptor(new HttpRequestInterceptor() {

            public void process(
                    final HttpRequest request,
                    final HttpContext context) throws HttpException, IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }

        });

        client.addResponseInterceptor(new HttpResponseInterceptor() {

            public void process(
                    final HttpResponse response,
                    final HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header header = entity.getContentEncoding();
                    if (header != null) {
                        HeaderElement[] elements = header.getElements();
                        for (int i = 0; i < elements.length; i++) {
                            if (elements[i].getName().equalsIgnoreCase("gzip")) {
                                response.setEntity(
                                        new GzipDecompressingEntity(response.getEntity()));
                                return;
                            }
                        }
                    }
                }
            }

        });
        return client;
    }

    private static String doGet(HttpClient client, URI uri) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(uri);
        }
        HttpResponse resp = client.execute(new HttpGet(uri));
        String result = new String(IOUtils.toByteArray(resp.getEntity().getContent()), "UTF-8");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(result);
        }
        return result;
    }

    public void init() throws IOException, URISyntaxException {
        client = prepareHttpClient();

        URI uri = new URI("https://api-maps.yandex.ru/2.0-stable/?load=package.full&lang=ru-RU");
        String getResult = doGet(client, uri);
        String[] strings = getResult.split("project_data");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].startsWith("[")) {
                String[] split = strings[i].split("=");
                params.put(split[0], split[1]);
            }
        }

        token = params.get("[\"token\"]");
        token = token.substring(1, token.length() - 3);
    }

    public YMapsImpl() {
        try {
            init();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private Geocode requestGeocode(String location) throws YMapsException {
        try {
            String s = URLEncoder.encode(location, "UTF-8");
            URI uri = new URI("https://api-maps.yandex.ru/services/search/v1/?text=" + s + "&format=json&rspn=0&lang=ru_RU&results=Geocode&token=" + token + "&type=geo&properties=addressdetails&geocoder_sco=latlong&origin=jsapi2Geocoder");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(doGet(client, uri), Geocode.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new YMapsException(e);
        }
    }

    public Coordinate geocode(String location) throws YMapsException {
        Geocode geocode = requestGeocode(location);
        if (geocode.getFeatures().isEmpty()) {
            throw new YMapsException("The location not found");
        }
        Geometry geometry = geocode.getFeatures().get(0).getGeometry();
        return new Coordinate(Double.valueOf(geometry.getCoordinates().get(0).toString()), Double.valueOf(geometry.getCoordinates().get(1).toString()));
    }

    public String geocode(Coordinate coordinate) throws YMapsException {
        Geocode geocode = requestGeocode(String.format("%s %s", coordinate.getLatitude(), coordinate.getLongitude()));
        if (geocode.getFeatures().isEmpty()) {
            throw new YMapsException("The location not found");
        }
        return geocode.getFeatures().get(0).getProperties().getName();
    }

    public Route route(String... locations) throws YMapsException {
        try {
            Coordinate[] coordinates = new Coordinate[locations.length];
            for (int i = 0; i < locations.length; i++) {
                coordinates[i] = geocode(locations[i]);
            }
            return route(coordinates);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new YMapsException(e);
        }
    }

    public Route route(Coordinate... coordinates) throws YMapsException {
        try {
            Stream<Coordinate> stream = Stream.of(coordinates);
            String s = stream.map(point -> String.format("%f %f", point.getLongitude(), point.getLatitude()))
                    .collect(Collectors.joining("~"));
            String url = String.format("https://api-maps.yandex.ru/services/route/2.0/?rll=%s&lang=ru_RU&token=%s&results=1&rtm=atm", s, token);
            URI uri = new URI(url.replace(" ", "%2C"));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(doGet(client, uri), Route.class);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new YMapsException(e);
        }
    }
}
