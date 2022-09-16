package com.ita.edu.speakua.api.models.task;

import lombok.Data;

import java.util.List;

@Data
public class ReadTaskResponse {
    private long id;
    private String name;
    private String headerText;
    private String description;
    private String picture;
    private List<Integer> startDate;
    private long challengeId;
}