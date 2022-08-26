package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CenterNameEntity {
    public static final String FIRST_SIX_NAMES_ASC = "SELECT name FROM centers ORDER BY name ASC LIMIT 6;";
    public static final String FIRST_SIX_NAMES_DESC = "SELECT name FROM centers ORDER BY name DESC LIMIT 6;";
    private String name;

    public static CenterNameEntity parseName(List<String> row) {
        CenterNameEntity centerEntity = new CenterNameEntity();
        centerEntity.setName(row.get(0));
        return centerEntity;
    }

    public static List<CenterNameEntity> parseNames(List<List<String>> rows) {
        List<CenterNameEntity> centerEntities = new ArrayList<>();
        for (List<String> row : rows) {
            centerEntities.add(parseName(row));
        }
        return centerEntities;
    }
}