package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.ArrayList;

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
    private ArrayList<Club> clubs;
}
