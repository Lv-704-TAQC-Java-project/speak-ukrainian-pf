package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CenterEntity {
    public static final String ALL_FIELDS = "id, center_external_id, contacts, description, name, " +
            "url_background_picture, url_logo, url_web, user_id, club_count, rating";
    public static final String ALL_FIELDS_CENTER = "centers.id, centers.center_external_id, centers.contacts, " +
            "centers.description, centers.name, centers.url_background_picture, centers.url_logo, centers.url_web, " +
            "centers.user_id, centers.club_count, centers.rating";
    public static final String SELECT_ALL_FIELDS = "SELECT " + ALL_FIELDS;

    public static final String SELECT_ALL = SELECT_ALL_FIELDS + " FROM centers ORDER BY id;";
    public static final String SELECT_WHERE_NAME = SELECT_ALL_FIELDS + " FROM centers WHERE name = '%s';";
    public static final String SELECT_BY_ID = SELECT_ALL_FIELDS + " FROM centers WHERE id = '%s';";
    public static final String FIRST_NAMES_LIMIT = SELECT_ALL_FIELDS + " FROM centers ORDER BY name %s LIMIT %d;";
    public static final String FIRST_NAMES_IN_CITY_LIMIT = "SELECT DISTINCT " + ALL_FIELDS_CENTER + " FROM centers" +
            " JOIN locations ON locations.center_id = centers.id" +
            " JOIN cities ON cities.id = locations.city_id" +
            " Where cities.name='%s'" +
            " ORDER BY centers.name %s LIMIT %d;";

    private long id;
    private long centerExternalId;
    private String contacts;
    private String description;
    private String name;
    private String urlBackgroundPicture;
    private String urlLogo;
    private String urlWeb;
    private long userId;
    private long clubCount;
    private double rating;

    public static CenterEntity parseRow(List<String> row) {
        CenterEntity centerEntity = new CenterEntity();
        centerEntity.setId(Long.parseLong(row.get(0)));
        centerEntity.setCenterExternalId(Long.parseLong(row.get(1) == null ? "0" : row.get(1)));
        centerEntity.setContacts(row.get(2));
        centerEntity.setDescription(row.get(3));
        centerEntity.setName(row.get(4));
        centerEntity.setUrlBackgroundPicture(row.get(5));
        centerEntity.setUrlLogo(row.get(6));
        centerEntity.setUrlWeb(row.get(7));
        centerEntity.setUserId(Long.parseLong(row.get(8) == null ? "0" : row.get(8)));
        centerEntity.setClubCount(Long.parseLong(row.get(9)));
        centerEntity.setRating(Double.parseDouble(row.get(10)));
        return centerEntity;
    }

    public static List<CenterEntity> parseRows(List<List<String>> rows) {
        List<CenterEntity> centerEntities = new ArrayList<>();
        for (List<String> row : rows) {
            centerEntities.add(parseRow(row));
        }
        return centerEntities;
    }
}