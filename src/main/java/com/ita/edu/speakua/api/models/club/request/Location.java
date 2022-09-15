package com.ita.edu.speakua.api.models.club.request;

import lombok.Builder;

import static java.lang.String.format;

@Builder
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

    @Override
    public String toString() {
        return format("    \"id\": %d,\n" +
                        "    \"name\": \"%s\",\n" +
                        "    \"address\": \"%s\",\n" +
                        "    \"cityId\": %d,\n" +
                        "    \"districtId\": %d,\n" +
                        "    \"stationId\": %d,\n" +
                        "    \"cityName\": \"%s\",\n" +
                        "    \"districtName\": \"%s\",\n" +
                        "    \"stationName\": \"%s\",\n" +
                        "    \"coordinates\": \"%s\",\n" +
                        "    \"longitude\": %d,\n" +
                        "    \"latitude\": %d,\n" +
                        "    \"centerId\": %d,\n" +
                        "    \"clubId\": %d,\n" +
                        "    \"phone\": \"%s\"",
                id,
                name,
                address,
                cityId,
                districtId,
                stationId,
                cityName,
                districtName,
                stationName,
                coordinates,
                longitude,
                latitude,
                centerId,
                clubId,
                phone);
    }
}
