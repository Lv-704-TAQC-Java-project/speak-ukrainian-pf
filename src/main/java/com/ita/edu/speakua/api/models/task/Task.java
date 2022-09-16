package com.ita.edu.speakua.api.models.task;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Task {
    private long id;
    private String name;
    private String headerText;
    private String picture;
    private ArrayList<Integer> startDate;
}