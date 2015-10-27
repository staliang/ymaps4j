package com.staliang.ymaps4j.beans;

/**
 * Created by Alexandr_Badin on 27.10.2015
 */
public class Location {

    private String street;
    private String district;
    private String locality;
    private String area;
    private String province;
    private String country;
    private String house;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "GeocodeResult{" +
                "street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", locality='" + locality + '\'' +
                ", area='" + area + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
