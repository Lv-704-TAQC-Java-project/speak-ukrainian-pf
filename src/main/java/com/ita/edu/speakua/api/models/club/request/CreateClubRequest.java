package com.ita.edu.speakua.api.models.club.request;

import lombok.Builder;

import java.util.ArrayList;

import static java.lang.String.format;

@Builder
public class CreateClubRequest {
    private int id;
    private ArrayList<String> categoriesName;
    private ArrayList<Location> locations;
    private String description;
    private String name;
    private int ageFrom;
    private int ageTo;
    private String urlBackground;
    private String urlLogo;
    private ArrayList<UrlGallery> urlGallery;
    private boolean isOnline;
    private String contacts;
    private boolean isApproved;
    private int userId;
    private int centerId;
    private int clubExternalId;
    private int centerExternalId;

    public String json() {
        return format("{\n" +
                        "  \"id\": %d,\n" +
                        "  \"categoriesName\": [\n    \"%s\"\n  ],\n" +
                        "  \"locations\": [\n" +
                        "   {\n" +
                        "%s\n" +
                        "   }\n" +
                        " ],\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"ageFrom\": %d,\n" +
                        "  \"ageTo\": %d,\n" +
                        "  \"urlBackground\": \"%s\",\n" +
                        "  \"urlLogo\": \"%s\",\n" +
                        "  \"urlGallery\": [\n" +
                        "   {\n" +
                        "    %s\n" +
                        "   }\n" +
                        " ],\n" +
                        "  \"isOnline\": %s,\n" +
                        "  \"contacts\": \"%s\",\n" +
                        "  \"isApproved\": %s,\n" +
                        "  \"userId\": %d,\n" +
                        "  \"centerId\": %d,\n" +
                        "  \"clubExternalId\": %d,\n" +
                        "  \"centerExternalId\": %d\n" +
                        "}",
                id,
                categoriesName.toString().replaceAll("\\[|\\]", ""),
                locations.toString().replaceAll("\\[|\\]", ""),
                description,
                name,
                ageFrom,
                ageTo,
                urlBackground,
                urlLogo,
                urlGallery.toString().replaceAll("\\[|\\]", ""),
                isOnline,
                contacts,
                isApproved,
                userId,
                centerId,
                clubExternalId,
                centerExternalId
        );
    }
}