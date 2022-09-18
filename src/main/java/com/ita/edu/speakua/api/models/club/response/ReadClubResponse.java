package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.ArrayList;

import static java.lang.String.format;

@Data
public class ReadClubResponse {
    private int id;
    private int ageFrom;
    private int ageTo;
    private String name;
    private String description;
    private String urlWeb;
    private String urlLogo;
    private String urlBackground;
    private ArrayList<UrlGallery> urlGallery;
    private String workTime;
    private ArrayList<Category> categories;
    private User user;
    private Center center;
    private double rating;
    private ArrayList<Location> locations;
    private String isApproved;
    private String isOnline;

    @Override
    public String toString() {
        return format("{" +
                        "  \"id\": %d,\n" +
                        "  \"ageFrom\": %d,\n" +
                        "  \"ageTo\": %d,\n" +
                        "  \"name\": \"%s\",\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"urlWeb\": \"%s\",\n" +
                        "  \"urlLogo\": \"%s\",\n" +
                        "  \"urlBackground\": \"%s\",\n" +
                        "  \"urlGallery\": [\n" +
                        "%s" +
                        "]," +
                        "  \"workTime\": \"%s\",\n" +
                        "  \"categories\": [\n" +
                        "%s" +
                        "]," +
                        "  \"user\":\n" +
                        "%s,\n" +
                        "  \"center\": %s,\n" +
                        "  \"rating\": %f,\n" +
                        "  \"locations\": [\n" +
                        "%s" +
                        "]," +
                        "  \"isApproved\": %s,\n" +
                        "  \"isOnline\": %s\n" +
                        "}",
                id,
                ageFrom,
                ageTo,
                name,
                description,
                urlWeb,
                urlLogo,
                urlBackground,
                urlGallery.toString().replaceAll("\\[|\\]", ""),
                workTime,
                categories.toString().replaceAll("\\[|\\]", ""),
                user.toString().replaceAll("\\[|\\]", ""),
                center.toString().replaceAll("\\[|\\]", ""),
                rating,
                locations.toString().replaceAll("\\[|\\]", ""),
                isApproved,
                isOnline
        );
    }
}
