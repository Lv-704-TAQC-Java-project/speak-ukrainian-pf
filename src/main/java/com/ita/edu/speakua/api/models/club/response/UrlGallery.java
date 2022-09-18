package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import static java.lang.String.format;

@Data
public class UrlGallery {
    private int id;
    private String url;

    @Override
    public String toString() {
        return format("    {\n" +
                        "\"id\": %d,\n" +
                        "\"url\": \"%s\",\n" +
                        "    }\n",
                id,
                url);
    }
}
