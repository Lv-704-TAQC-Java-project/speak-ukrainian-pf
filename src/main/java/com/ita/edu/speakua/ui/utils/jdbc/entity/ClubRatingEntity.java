package com.ita.edu.speakua.ui.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClubRatingEntity {
    public static final String SIX_RESULTS_ASC = "SELECT rating FROM clubs ORDER BY name ASC LIMIT 6;";
    public static final String SIX_RESULTS_DESC = "SELECT rating FROM clubs ORDER BY name DESC LIMIT 6;";
    public static final String ORDER_BY_RATING_ASC_SIX = "SELECT id, name, rating FROM clubs ORDER BY rating, id LIMIT 6;";
    public static final String ORDER_BY_RATING_DESC_SIX = "SELECT id, name, rating FROM clubs ORDER BY rating DESC, id LIMIT 6;";
    private double rating;

    public static ClubRatingEntity parseRating(List<String> row) {
        ClubRatingEntity clubRatingEntity = new ClubRatingEntity();
        clubRatingEntity.setRating(Double.parseDouble(row.get(0)));
        return clubRatingEntity;
    }

    public static List<ClubRatingEntity> parseRatings(List<List<String>> rows) {
        List<ClubRatingEntity> clubRatingEntitity = new ArrayList<>();
        for (List<String> row : rows) {
            clubRatingEntitity.add(parseRating(row));
        }
        return clubRatingEntitity;
    }
}
