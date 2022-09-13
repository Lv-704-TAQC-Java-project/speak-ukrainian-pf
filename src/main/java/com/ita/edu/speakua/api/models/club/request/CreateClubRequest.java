package com.ita.edu.speakua.api.models.club.request;

import lombok.Data;

import java.util.ArrayList;

@Data
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
}
