package com.ita.edu.speakua.api.models.challenge;

import lombok.Data;


import java.util.List;

@Data
public class Task {
    private long id;
    private String name;
    private String headerText;
    private String picture;
    private List<Integer> startDate;
}