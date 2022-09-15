package com.ita.edu.speakua.api.models.task;

import lombok.Builder;

import static java.lang.String.format;

@Builder
public class CreateTaskRequest {
    public String name;
    public String headerText;
    public String description;
    public String picture;
    public String startDate;

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