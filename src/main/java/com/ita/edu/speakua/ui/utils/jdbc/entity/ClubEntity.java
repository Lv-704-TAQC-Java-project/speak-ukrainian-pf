package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClubEntity {

    public static final String SELECT_ALL = "SELECT * FROM clubs ORDER BY id;";
    public static final String SELECT_BY_ID = "SELECT * FROM clubs WHERE id = '%s';";
    public static final String SELECT_SIX_NAMES_ASC = "SELECT name FROM clubs ORDER BY name ASC LIMIT 6;";
    public static final String SELECT_SIX_NAMES_DESC = "SELECT name FROM clubs ORDER BY name DESC LIMIT 6;";
    public static final String SELECT_SIX_NAMES_ORDER_BY_RATING_ASC = "SELECT name FROM clubs ORDER BY rating, id LIMIT 6;";
    public static final String SELECT_SIX_NAMES_ORDER_BY_RATING_DEC = "SELECT name FROM clubs ORDER BY rating, id LIMIT 6;";
    public static final String SELECT_NAME = "SELECT name FROM clubs;";
    public static final String SELECT_NAME_WHERE_NAME_LIKE = "SELECT name FROM clubs WHERE name LIKE '%s%%';";

    public static final String ORDER_BY_RATING_ASC_SIX = "SELECT id, name, rating FROM clubs ORDER BY rating, id LIMIT 6;";
    public static final String ORDER_BY_RATING_DESC_SIX = "SELECT id, name, rating FROM clubs ORDER BY rating DESC, id LIMIT 6;";


    private long id;
    private int age_from;
    private int age_to;
    private long center_external_id;
    private long club_external_id;
    private String contacts;
    private String description;
    private boolean is_approved;
    private boolean is_online;
    private String name;
    private double rating;
    private String url_background;
    private String url_logo;
    private String urlWeb;
    private String work_time;
    private long center_id;
    private long user_id;
    private int feedback_count;

    public static ClubEntity parseRow(List<String> row) {
        ClubEntity clubEntity = new ClubEntity();
        clubEntity.setId(Long.parseLong(row.get(0)));
        clubEntity.setAge_from(Integer.parseInt(row.get(1)));
        clubEntity.setAge_to(Integer.parseInt(row.get(2)));
        clubEntity.setCenter_external_id(Long.parseLong(row.get(3)));
        clubEntity.setClub_external_id(Long.parseLong(row.get(4)));
        clubEntity.setContacts(row.get(5));
        clubEntity.setDescription(row.get(6));
        clubEntity.set_approved(Boolean.parseBoolean(row.get(7)));
        clubEntity.set_online(Boolean.parseBoolean(row.get(8)));
        clubEntity.setName(row.get(9));
        clubEntity.setRating(Double.parseDouble(row.get(10)));
        clubEntity.setUrl_background(row.get(11));
        clubEntity.setUrl_logo(row.get(12));
        clubEntity.setUrlWeb(row.get(13));
        clubEntity.setWork_time(row.get(14));
        clubEntity.setCenter_id(Long.parseLong(row.get(16) == null ? "0" : row.get(15)));
        clubEntity.setUser_id(Long.parseLong(row.get(16) == null ? "0" : row.get(16)));
        clubEntity.setFeedback_count(Integer.parseInt(row.get(17)));
        return clubEntity;
    }

    public static List<ClubEntity> parseRows(List<List<String>> rows) {
        List<ClubEntity> clubEntities = new ArrayList<>();
        for (List<String> row : rows) {
            clubEntities.add(parseRow(row));
        }
        return clubEntities;
    }

    public static ClubEntity parseNameRow(List<String> row) {
        ClubEntity clubEntity = new ClubEntity();
        clubEntity.setName(row.get(0));
        return clubEntity;
    }

    public static List<ClubEntity> parseNameRows(List<List<String>> rows) {
        List<ClubEntity> clubEntities = new ArrayList<>();
        for (List<String> row : rows) {
            clubEntities.add(parseNameRow(row));
        }
        return clubEntities;
    }
}
