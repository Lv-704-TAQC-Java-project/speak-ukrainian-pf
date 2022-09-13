package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

@Data
public class Location{
    private int id;
    private String name;
    private String address;
    private int latitude;
    private int longitude;
    private String phone;
    private District district;
    private Station station;
    private City city;
    private String club;
    private Center center;
    private String cityName;
    private String districtName;
    private String stationName;
    private LocationCity locationCity;
    private int cityId;
    private int districtId;
    private int stationId;
    private int clubId;
    private String coordinates;
}
