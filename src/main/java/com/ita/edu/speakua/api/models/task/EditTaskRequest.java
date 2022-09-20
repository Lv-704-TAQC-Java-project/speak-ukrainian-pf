package com.ita.edu.speakua.api.models.task;

import lombok.Builder;

import static java.lang.String.format;

@Builder
public class EditTaskRequest {
    private String name;
    private String headerText;
    private String description;
    private String picture;
    private String startDate;
    private long challengeId;

    public String json() {
        return format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"headerText\": \"%s\",\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"picture\": \"%s\",\n" +
                        "  \"startDate\": \"%s\",\n" +
                        "  \"challengeId\": \"%s\"\n" +
                        "}",
                name,
                headerText,
                description,
                picture,
                startDate,
                challengeId
        );
    }
}