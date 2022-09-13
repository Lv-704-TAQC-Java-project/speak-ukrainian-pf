package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

@Data
public class Station{
    private int id;
    private String name;
    private City city;
    private District district;
}
