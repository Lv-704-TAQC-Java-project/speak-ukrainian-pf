package com.ita.edu.speakua.api.models.task;

import lombok.Data;

import java.util.List;

@Data
public class Task {
    public long id;
    public String name;
    public String headerText;
    public String picture;
    public List<Integer> startDate;
}