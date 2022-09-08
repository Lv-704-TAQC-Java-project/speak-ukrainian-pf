package com.ita.edu.speakua.api.models.challenge;

import lombok.Data;

import java.util.ArrayList;


@Data
public class ReadChallengeResponse {
    public int id;
    public String name;
    public String title;
    public String description;
    public String picture;
    public int sortNumber;
    public boolean isActive;
    public ArrayList<Task> tasks;
    public User user;
    public Object registrationLink;
}