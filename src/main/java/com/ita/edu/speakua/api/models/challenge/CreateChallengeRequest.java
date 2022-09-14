package com.ita.edu.speakua.api.models.challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static java.lang.String.format;

@Data
@Builder
@AllArgsConstructor
public class CreateChallengeRequest {
    private String name;
    private String title;
    private String description;
    private String registrationLink;
    private String picture;
    private int sortNumber;

    public String json() {
        return format("{\n" +
                        "\"name\":\"%s\",\n" +
                        "\"title\":\"%s\",\n" +
                        "\"description\":\"%s\",\n" +
                        "\"picture\":\"%s\",\n" +
                        "\"sortNumber\":\"%s\",\n" +
                        "}",
                name,
                title,
                description,
                picture,
                sortNumber);
    }
}


