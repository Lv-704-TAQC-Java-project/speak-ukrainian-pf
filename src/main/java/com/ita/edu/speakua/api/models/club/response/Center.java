package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Center {
    private int id;
    private String name;
    private String contacts;
    private String urlBackgroundPicture;
    private String description;
    private String urlWeb;
    private String urlLogo;
    private ArrayList<String> locations;
    private ArrayList<String> clubs;
    private User user;
    private int centerExternalId;
    private int rating;
    private int clubCount;
    private String email;
    private String phones;
    private String socialLinks;
}
