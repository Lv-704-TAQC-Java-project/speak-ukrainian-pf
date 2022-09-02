package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityEntity {
    public static final String SELLEC_ALL = "SELECT * FROM cities ORDER BY id;";
    public static final String SELLEC_WHERE_NAME = "SELECT * FROM cities WHERE name = '%s';";
    public static final String SELLEC_BY_ID = "SELECT * FROM cities WHERE id = '%s';";

    private long id;
    private double latitude;
    private double longitude;
    private String name;

    public static CityEntity parseRow(List<String> row) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(Long.parseLong(row.get(0)));
        cityEntity.setLatitude(Double.parseDouble(row.get(1)));
        cityEntity.setLongitude(Double.parseDouble(row.get(2)));
        cityEntity.setName(row.get(3));
        return cityEntity;
    }

    public static List<CityEntity> parseRows(List<List<String>> rows) {
        List<CityEntity> cityEntities = new ArrayList<>();
        for (List<String> row : rows) {
            cityEntities.add(parseRow(row));
        }

        return cityEntities;
    }
}