package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskEntity {
    public static final String SELECT_WHERE_NAME = "SELECT * FROM tasks WHERE name = '%s';";

    private long id;
    private String description;
    private String name;
    private String picture;
    private String start_date;
    private long challenge_id;
    private String header_text;

    public static TaskEntity parseRow(List<String> row) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setId(Long.parseLong(row.get(0)));
        taskEntity.setDescription(row.get(1));
        taskEntity.setName(row.get(2));
        taskEntity.setPicture(row.get(3));
        taskEntity.setStart_date(row.get(4));
        taskEntity.setChallenge_id(Long.parseLong(row.get(5)));
        taskEntity.setHeader_text(row.get(6));

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