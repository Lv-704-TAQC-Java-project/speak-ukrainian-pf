package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

@Data
public class Category {
    private int id;
    private int sortby;
    private String name;
    private String description;
    private String urlLogo;
    private String backgroundColor;
    private String tagBackgroundColor;
    private String tagTextColor;

//    @Override
//    public String toString() {
//        return format("    {\n" +
//                        "\"id\": %d,\n" +
//                        "\"sortby\": %d,\n" +
//                        "\"name\": \"%s\",\n" +
//                        "\"description\": \"%s\",\n" +
//                        "\"urlLogo\": \"%s\",\n" +
//                        "\"backgroundColor\": \"%s\",\n" +
//                        "\"tagBackgroundColor\": \"%s\",\n" +
//                        "\"tagTextColor\": \"%s\",\n" +
//                        "    }\n",
//                id,
//                sortby,
//                name,
//                description,
//                urlLogo,
//                backgroundColor,
//                tagBackgroundColor,
//                tagTextColor);
//    }
}
