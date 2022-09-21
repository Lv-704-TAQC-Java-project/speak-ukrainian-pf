package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

@Data
public class LocationResponse {
    private int id;
    private String name;
    private String address;
    private String cityName;
    private String districtName;
    private String stationName;
    private LocationCity locationCity;
    private int cityId;
    private int districtId;
    private int stationId;
    private int clubId;
    private String coordinates;
    private double latitude;
    private double longitude;
    private String phone;
}
