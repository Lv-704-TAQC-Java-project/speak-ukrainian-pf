package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateClubResponse {
    private int id;
    private int ageFrom;
    private int ageTo;
    private String name;
    private String description;
    private String urlWeb;
    private String urlBackground;
    private ArrayList<UrlGallery> urlGallery;
    private String urlLogo;
    private String workTime;
    private ArrayList<Category> categories;
    private User user;
    private Center center;
    private int rating;
    private ArrayList<LocationResponse> locations;
    private boolean isApproved;
    private boolean isOnline;
}
