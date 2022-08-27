package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClubNameEntity {
    public static final String SELECT_SIX_NAMES_ASC = "SELECT name FROM clubs ORDER BY name ASC LIMIT 6;";
    public static final String SELECT_SIX_NAMES_DESC = "SELECT name FROM clubs ORDER BY name DESC LIMIT 6;";
    private String name;

    public static ClubNameEntity parseName(List<String> row) {
        ClubNameEntity clubEntity = new ClubNameEntity();
        clubEntity.setName(row.get(0));
        return clubEntity;
    }

    public static List<ClubNameEntity> parseNames(List<List<String>> rows) {
        List<ClubNameEntity> clubEntities = new ArrayList<>();
        for (List<String> row : rows) {
            clubEntities.add(parseName(row));
        }
        return clubEntities;
    }
}
