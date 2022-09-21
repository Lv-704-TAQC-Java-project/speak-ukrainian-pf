package com.ita.edu.speakua.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleEntity {
    public static final String GET_ROLE = "SELECT * FROM roles WHERE id=%d;";

    private long id;
    private String name;

    public static RoleEntity parseRow(List<String> row) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(Long.parseLong(row.get(0)));
        roleEntity.setName(row.get(1));
        return roleEntity;
    }

    public static List<RoleEntity> parseRows(List<List<String>> rows) {
        List<RoleEntity> centerEntities = new ArrayList<>();
        for (List<String> row : rows) {
            centerEntities.add(parseRow(row));
        }
        return centerEntities;
    }
}