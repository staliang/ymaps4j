# ymaps4j

Yandex Maps API for Java. Implementation follow functions:

<br/>1) https://tech.yandex.com/maps/doc/jsapi/2.1/dg/concepts/geocoding-docpage/
<br/>YMaps yMaps = YMapsFactory.getMaps(YMapsVersion.V2);
<br/>yMaps.init();
<br/>yMaps.geocode("Moscow");
<br/>yMaps.geocode(new Coordinate(37.620393, 55.75396));

<br/>2) https://tech.yandex.com/maps/doc/jsapi/2.1/dg/concepts/geolocation-docpage/
<br/>YMaps yMaps = YMapsFactory.getMaps(YMapsVersion.V2);
<br/>yMaps.init();
<br/>yMaps.geolocation();

<br/>3) https://tech.yandex.com/maps/doc/jsapi/2.1/dg/concepts/router-docpage/
<br/>YMaps yMaps = YMapsFactory.getMaps(YMapsVersion.V2);
<br/>yMaps.init();
<br/>yMaps.route("Moscow", "Saratov");
