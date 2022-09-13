package com.ita.edu.speakua.api.models.club.request;

import lombok.Data;

@Data
public class Location {
    private int id;
    private String name;
    private String address;
    private int cityId;
    private int districtId;
    private int stationId;
    private String cityName;
    private String districtName;
    private String stationName;
    private String coordinates;
    private int longitude;
    private int latitude;
    private int centerId;
    private int clubId;
    private String phone;
}
