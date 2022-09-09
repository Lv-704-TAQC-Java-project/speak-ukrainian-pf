package com.ita.edu.speakua.api.models.challenge;

import lombok.Data;

@Data
public class CreateChallengeResponse {
    public int id;
    public String name;
    public String title;
    public String description;
    public String registrationLink;
    public String picture;
    public int sortNumber;
    public boolean isActive;
    public User user;
}
