package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import static java.lang.String.format;

@Data
public class LocationCity {
    private int id;
    private String name;
    private int latitude;
    private int longitude;

    @Override
    public String toString() {
        return format("    {\n" +
                        "\"id\": %d,\n" +
                        "\"name\": \"%s\",\n" +
                        "\"latitude\": %d,\n" +
                        "\"longitude\": %d,\n" +
                        "    }\n",
                id,
                name,
                latitude,
                longitude);
    }
}
