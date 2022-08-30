package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskEntity {
    public static final String ALL_FIELDS = "id, description, name, picture, start_date, challenge_id, header_text";
    public static final String SELECT_ALL_FIELDS = "SELECT " + ALL_FIELDS;
    public static final String SELECT_ALL = SELECT_ALL_FIELDS + " FROM tasks;";
    public static final String SELECT_ALL_WHERE_NAME = SELECT_ALL_FIELDS + " FROM tasks WHERE name = '%s';";
    public static final String SELECT_ALL_WHERE_NAME_LIKE = SELECT_ALL_FIELDS + " FROM tasks WHERE name LIKE '%s';";

    private long id;
    private String description;
    private String name;
    private String picture;
    private String startDate;
    private long challenge_id;
    private String headerText;

    public static TaskEntity parseRow(List<String> row) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setId(Long.parseLong(row.get(0)));
        taskEntity.setDescription(row.get(1));
        taskEntity.setName(row.get(2));
        taskEntity.setPicture(row.get(3));
        taskEntity.setStartDate(row.get(4));
        taskEntity.setChallenge_id(Long.parseLong(row.get(5) == null ? "0" : row.get(5)));
        taskEntity.setHeaderText(row.get(6));

        return taskEntity;
    }

    public static List<TaskEntity> parseRows(List<List<String>> rows) {
        List<TaskEntity> taskEntities = new ArrayList<>();
        for (List<String> row : rows) {
            taskEntities.add(parseRow(row));
        }

        return taskEntities;
    }
}