package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Center {
    private int id;
    private String name;
    private String urlBackgroundPicture;
    private String email;
    private String phones;
    private String description;
    private String urlWeb;
    private String urlLogo;
    private String socialLinks;
    private User user;
    private ArrayList<LocationResponse> locations;
    private String contacts;
//    @Override
//    public String toString() {
//        return format("    {\n" +
//                        "\"id\": %d,\n" +
//                        "\"name\": \"%s\",\n" +
//                        "\"urlBackgroundPicture\": \"%s\",\n" +
//                        "\"email\": \"%s\",\n" +
//                        "\"phones\": \"%s\",\n" +
//                        "\"description\": \"%s\",\n" +
//                        "\"urlWeb\": \"%s\",\n" +
//                        "\"urlLogo\": \"%s\",\n" +
//                        "\"socialLinks\": \"%s\",\n" +
//                        "\"user\": %s,\n" +
//                        "\"locations\": [\n" +
//                        "%s,\n" +
//                        "],\n" +
//                        "\"contacts\": \"%s\",\n" +
//                        "    }\n",
//                id,
//                name,
//                urlBackgroundPicture,
//                email,
//                phones,
//                description,
//                urlWeb,
//                urlLogo,
//                socialLinks,
//                user.toString().replaceAll("\\[|\\]", ""),
//                locations.toString().replaceAll("\\[|\\]", ""),
//                contacts);
//    }
}
