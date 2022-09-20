package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

@Data
public class Location {
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
    private int latitude;
    private int longitude;
    private String phone;
//    @Override
//    public String toString() {
//        return format("    {\n" +
//                        "\"id\": %d,\n" +
//                        "\"name\": \"%s\",\n" +
//                        "\"address\": \"%s\",\n" +
//                        "\"cityName\": \"%s\",\n" +
//                        "\"districtName\": \"%s\",\n" +
//                        "\"stationName\": \"%s\",\n" +
//                        "\"locationCity\": %s,\n" +
//                        "\"cityId\": %d,\n" +
//                        "\"districtId\": %d,\n" +
//                        "\"stationId\": %d,\n" +
//                        "\"clubId\": %d,\n" +
//                        "\"coordinates\": %s,\n" +
//                        "\"latitude\": %d,\n" +
//                        "\"longitude\": %d,\n" +
//                        "\"phone\": %s\n" +
//                        "    }\n",
//                id,
//                name,
//                address,
//                cityName,
//                districtName,
//                stationName,
//                locationCity.toString().replaceAll("\\[|\\]", ""),
//                cityId,
//                districtId,
//                stationId,
//                clubId,
//                coordinates,
//                longitude,
//                latitude,
//                phone);
//    }
}
