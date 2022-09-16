package com.ita.edu.speakua.api.models.task;

import lombok.Builder;

import static java.lang.String.format;

@Builder
public class CreateTaskRequest {
    private String name;
    private String headerText;
    private String description;
    private String picture;
    private String startDate;

    public String json() {
        return format("{\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"headerText\": \"%s\",\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"picture\": \"%s\",\n" +
                        "  \"startDate\": \"%s\"\n" +
                        "}",
                name,
                headerText,
                description,
                picture,
                startDate
        );
    }
}