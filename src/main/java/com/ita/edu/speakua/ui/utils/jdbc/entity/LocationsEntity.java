package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationsEntity {
    public static final String SELECT_BY_NAME = "SELECT * FROM locations WHERE name = '%s';";

    private long id;
    private String address;
    private double latitude;
    private double longitude;
    private String name;
    private long centerId;
    private long cityId;
    private long clubId;
    private long districtId;
    private long stationId;
    private String phone;

    public static LocationsEntity parseRow(List<String> row) {
        LocationsEntity locationsEntity = new LocationsEntity();
        locationsEntity.setId(Long.parseLong(row.get(0)));
        locationsEntity.setAddress(row.get(1));
        locationsEntity.setLatitude(Double.parseDouble(row.get(2) == null ? "0" : row.get(2)));
        locationsEntity.setLongitude(Double.parseDouble(row.get(3) == null ? "0" : row.get(3)));
        locationsEntity.setName(row.get(4));
        locationsEntity.setCenterId(Long.parseLong(row.get(5) == null ? "0" : row.get(5)));
        locationsEntity.setCityId(Long.parseLong(row.get(6)));
        locationsEntity.setClubId(Long.parseLong(row.get(7) == null ? "0" : row.get(7)));
        locationsEntity.setDistrictId(Long.parseLong(row.get(8) == null ? "0" : row.get(8)));
        locationsEntity.setStationId(Long.parseLong(row.get(9) == null ? "0" : row.get(9)));
        locationsEntity.setPhone(row.get(10) == null ? "0" : row.get(10));
        return locationsEntity;
    }

    public static List<LocationsEntity> parseRows(List<List<String>> rows) {
        List<LocationsEntity> locationsEntities = new ArrayList<>();
        for (List<String> row : rows) {
            locationsEntities.add(parseRow(row));
        }
        return locationsEntities;
    }
}
