package com.ita.edu.speakua.api.models.club.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;

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
}
