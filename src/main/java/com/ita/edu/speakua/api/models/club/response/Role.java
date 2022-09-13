package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Role {
    private int id;
    private String name;
    private ArrayList<String> users;
}
