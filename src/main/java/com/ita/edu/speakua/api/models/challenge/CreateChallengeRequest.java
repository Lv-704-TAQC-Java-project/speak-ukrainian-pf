package com.ita.edu.speakua.api.models.challenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static java.lang.String.format;

@Data
@Builder
@AllArgsConstructor
public class CreateChallengeRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("registrationLink")
    private String registrationLink;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("sortNumber")
    private int sortNumber;

    public String json() {
        return format("{\n" +
                        "\"name\":\"%s\",\n" +
                        "\"title\":\"%s\",\n" +
                        "\"description\":\"%s\",\n" +
                        "\"picture\":\"%s\",\n" +
                        "\"sortNumber\":\"%s\"\n" +
                        "}",
                name,
                title,
                description,
                picture,
                sortNumber);
    }
}


