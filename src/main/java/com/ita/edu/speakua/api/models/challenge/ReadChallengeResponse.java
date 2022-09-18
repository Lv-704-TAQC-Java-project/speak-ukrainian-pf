package com.ita.edu.speakua.api.models.challenge;

import lombok.Data;

import java.util.ArrayList;


@Data
public class ReadChallengeResponse {
    private int id;
    private String name;
    private String title;
    private String description;
    private String picture;
    private int sortNumber;
    private Boolean isActive;
    private ArrayList<Task> tasks;
    private User user;
    private String registrationLink;
}