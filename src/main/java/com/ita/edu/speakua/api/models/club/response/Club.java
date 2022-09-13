package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Club {
    private int id;
    private int ageFrom;
    private int ageTo;
    private String name;
    private String description;
    private String urlWeb;
    private String urlLogo;
    private String urlBackground;
    private ArrayList<String> urlGallery;
    private String workTime;
    private int rating;
    private int feedbackCount;
    private boolean isOnline;
    private ArrayList<Location> locations;
    private ArrayList<Feedback> feedbacks;
    private ArrayList<String> categories;
    private User user;
    private Center center;
    private boolean isApproved;
    private String contacts;
    private int clubExternalId;
    private int centerExternalId;
}
