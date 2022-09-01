package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClubEntity {

    public static final String ALL_FIELDS = "id, age_from, age_to, center_external_id, club_external_id, " +
            "contacts, description, is_approved, is_online, name, rating, url_background, url_logo, url_web, " +
            "work_time, center_id, user_id, feedback_count";
    public static final String SELECT_ALL_FIELDS = "SELECT " + ALL_FIELDS;

    public static final String SELECT_ALL = SELECT_ALL_FIELDS + " FROM clubs ORDER BY id;";
    public static final String SELECT_BY_ID = SELECT_ALL_FIELDS + " FROM clubs WHERE id = '%s';";

    public static final String SELECT_ALL_ORDER_BY_NAME_LIMIT = SELECT_ALL_FIELDS
            + " FROM clubs WHERE name IS NOT NULL AND length(trim(name)) > 1 ORDER BY name %s LIMIT %d;";
//    public static final String SELECT_ALL_ORDER_BY_NAME_DEC_LIMIT = SELECT_ALL_FIELDS
//            + " FROM clubs WHERE name IS NOT NULL AND length(trim(name)) > 1 ORDER BY name DESC LIMIT %d;";

    public static final String SELECT_ALL_ORDER_BY_RATING_ID_ASC_LIMIT = SELECT_ALL_FIELDS + " FROM clubs " +
            "WHERE id IN (SELECT DISTINCT club_category.club_id " +
            "FROM club_category) " +
            "ORDER BY rating ASC, id " +
            "LIMIT %d;";
    public static final String SELECT_ALL_ORDER_BY_RATING_ID_DESC_LIMIT = SELECT_ALL_FIELDS + " FROM clubs " +
            "WHERE id IN (SELECT DISTINCT club_category.club_id " +
            "FROM club_category) " +
            "ORDER BY rating DESC, id " +
            "LIMIT %d;";
    public static final String SELECT_ALL_WHERE_NAME_LIKE = SELECT_ALL_FIELDS + " FROM clubs WHERE name LIKE '%s%%';";
    public static final String SELECT_ALL_WHERE_CITY = "SELECT cl.id, cl.age_from, cl.age_to, " +
            "cl.center_external_id, cl.club_external_id, cl.contacts, cl.description, cl.is_approved, cl.is_online, " +
            "cl.name, cl.rating, cl.url_background, cl.url_logo, cl.url_web, cl.work_time, cl.center_id, " +
            "cl.user_id, cl.feedback_count " +
            "FROM clubs as cl " +
            "JOIN locations l ON cl.id = l.club_id " +
            "JOIN cities ct ON l.city_id = ct.id " +
            "WHERE ct.name = '%s';";

    private long id;
    private int ageFrom;
    private int ageTo;
    private long centerExternalId;
    private long clubExternalId;
    private String contacts;
    private String description;
    private boolean isApproved;
    private boolean isOnline;
    private String name;
    private double rating;
    private String urlBackground;
    private String urlLogo;
    private String urlWeb;
    private String workTime;
    private long centerId;
    private long userId;
    private int feedbackCount;

    public static ClubEntity parseRow(List<String> row) {
        ClubEntity clubEntity = new ClubEntity();
        clubEntity.setId(Long.parseLong(row.get(0)));
        clubEntity.setAgeFrom(Integer.parseInt(row.get(1)));
        clubEntity.setAgeTo(Integer.parseInt(row.get(2)));
        clubEntity.setCenterExternalId(Long.parseLong(row.get(3) == null ? "0" : row.get(3)));
        clubEntity.setClubExternalId(Long.parseLong(row.get(4) == null ? "0" : row.get(4)));
        clubEntity.setContacts(row.get(5));
        clubEntity.setDescription(row.get(6));
        clubEntity.setApproved(Boolean.parseBoolean(row.get(7)));
        clubEntity.setOnline(Boolean.parseBoolean(row.get(8)));
        clubEntity.setName(row.get(9));
        clubEntity.setRating(Double.parseDouble(row.get(10)));
        clubEntity.setUrlBackground(row.get(11));
        clubEntity.setUrlLogo(row.get(12));
        clubEntity.setUrlWeb(row.get(13));
        clubEntity.setWorkTime(row.get(14));
        clubEntity.setCenterId(Long.parseLong(row.get(15) == null ? "0" : row.get(15)));
        clubEntity.setUserId(Long.parseLong(row.get(16) == null ? "0" : row.get(16)));
        clubEntity.setFeedbackCount(Integer.parseInt(row.get(17) == null ? "0" : row.get(17)));
        return clubEntity;
    }

    public static List<ClubEntity> parseRows(List<List<String>> rows) {
        List<ClubEntity> clubEntities = new ArrayList<>();
        for (List<String> row : rows) {
            clubEntities.add(parseRow(row));
        }
        return clubEntities;
    }
}