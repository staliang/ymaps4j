# ymaps4j

Yandex Maps API for Java. Implementation follow functions:

1) https://tech.yandex.com/maps/doc/jsapi/2.1/dg/concepts/geocoding-docpage/

YMaps yMaps = YMapsFactory.getInstance();
yMaps.geocode("Moscow");
yMaps.geocode(new Coordinate(37.620393, 55.75396));

2) https://tech.yandex.com/maps/doc/jsapi/2.1/dg/concepts/geolocation-docpage/

YMaps yMaps = YMapsFactory.getInstance();
yMaps.geolocation();

3) https://tech.yandex.com/maps/doc/jsapi/2.1/dg/concepts/router-docpage/

YMaps yMaps = YMapsFactory.getInstance();
yMaps.route("Moscow", "Saratov");
