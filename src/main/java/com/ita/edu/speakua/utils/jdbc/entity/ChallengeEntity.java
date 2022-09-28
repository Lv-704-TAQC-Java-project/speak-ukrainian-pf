package com.ita.edu.speakua.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChallengeEntity {
    private static final String ALL_FIELDS = "id, description, is_active, name, " +
            "picture, registration_link, sort_number, title, user_id";
    private static final String SELECT_ALL_FIELDS = "SELECT " + ALL_FIELDS;

    public static final String SELECT_ALL_CHALLENGES = SELECT_ALL_FIELDS + " FROM challenges;";
    public static final String SELECT_CHALLENGE_BY_ID = SELECT_ALL_FIELDS + " FROM challenges WHERE id='%s';";
    public static final String COUNT_WHERE_ID = "SELECT count(*) FROM challenges WHERE id='%s';";
    public static final String SELECT_CHALLENGE_WHERE_SORT_NUMBER_IS_MAX = SELECT_ALL_FIELDS +
            " FROM challenges WHERE sort_number = (SELECT MAX(sort_number) FROM challenges)";

    private long id;
    private String description;
    private Boolean isActive;
    private String name;
    private String picture;
    private String registrationLink;
    private long sortNumber;
    private String title;
    private long userId;

    public static ChallengeEntity parseRow(List<String> row) {
        ChallengeEntity challengeEntity = new ChallengeEntity();
        challengeEntity.setId(Long.parseLong(row.get(0)));
        challengeEntity.setDescription(row.get(1));
        challengeEntity.setIsActive(row.get(2).equals("t"));
        challengeEntity.setName(row.get(3));
        challengeEntity.setPicture(row.get(4));
        challengeEntity.setRegistrationLink(row.get(5));
        challengeEntity.setSortNumber(Long.parseLong(row.get(6)));
        challengeEntity.setTitle(row.get(7));
        challengeEntity.setUserId(Long.parseLong(row.get(8) == null ? "0" : row.get(8)));
        return challengeEntity;
    }

    public static List<ChallengeEntity> parseRows(List<List<String>> rows) {
        List<ChallengeEntity> challengeEntitys = new ArrayList<>();
        for (List<String> row : rows) {
            challengeEntitys.add(parseRow(row));
        }
        return challengeEntitys;
    }
}