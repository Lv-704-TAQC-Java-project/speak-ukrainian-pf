package com.ita.edu.speakua.api.models.club.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class Location {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("cityId")
    private int cityId;
    @JsonProperty("districtId")
    private int districtId;
    @JsonProperty("stationId")
    private int stationId;
    @JsonProperty("cityName")
    private String cityName;
    @JsonProperty("districtName")
    private String districtName;
    @JsonProperty("stationName")
    private String stationName;
    @JsonProperty("coordinates")
    private String coordinates;
    @JsonProperty("longitude")
    private int longitude;
    @JsonProperty("latitude")
    private int latitude;
    @JsonProperty("centerId")
    private int centerId;
    @JsonProperty("clubId")
    private int clubId;
    @JsonProperty("phone")
    private String phone;
}
