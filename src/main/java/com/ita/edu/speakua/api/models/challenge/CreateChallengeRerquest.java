package com.ita.edu.speakua.api.models.challenge;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateChallengeRerquest {
    private String name;
    private String title;
    private String description;
    private String registrationLink;
    private String picture;
    private int sortNumber;
}


