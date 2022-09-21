package com.ita.edu.speakua.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleEntity {

    public static final String SELECT_WHERE_USER_ID = "SELECT roles.id, roles.name FROM roles " +
            "JOIN users ON roles.id = users.role_id " +
            "WHERE users.id = '%d';";

    private long id;
    private String name;

    public static RoleEntity parseRow(List<String> row) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(Long.parseLong(row.get(0)));
        roleEntity.setName(row.get(1));
        return roleEntity;
    }

    public static List<RoleEntity> parseRows(List<List<String>> rows) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (List<String> row : rows) {
            roleEntities.add(parseRow(row));
        }
        return roleEntities;
    }
}