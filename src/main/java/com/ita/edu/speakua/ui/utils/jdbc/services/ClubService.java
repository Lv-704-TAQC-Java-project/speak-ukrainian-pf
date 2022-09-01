package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.ClubDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dto.CenterDTO;
import com.ita.edu.speakua.ui.utils.jdbc.dto.ClubWithCenterDTO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.ClubEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClubService {
    private final ClubDAO clubDAO;
    private final CenterDAO centerDAO;

    public ClubService() {
        clubDAO = new ClubDAO();
        centerDAO = new CenterDAO();
    }

    public List<ClubEntity> getAll() {
        return clubDAO.selectAll();
    }

    public List<String> getAllName() {
        List<String> names = new ArrayList<>();
        for (ClubEntity club : clubDAO.selectAll()) {
            names.add(club.getName());
        }
        return names;
    }

    public List<ClubWithCenterDTO> getAllClubWithCenter() {
        List<ClubWithCenterDTO> clubs = new ArrayList<>();
        for (ClubEntity club : clubDAO.selectAll()) {
            ClubWithCenterDTO clubDTO = new ClubWithCenterDTO();
            clubDTO.setClub(club);
            CenterDTO center = new CenterDTO();
            center.setCenter(centerDAO.selectById(club.getCenterId()));
            clubs.add(clubDTO);
        }
        return clubs;
    }

    public List<String> getAllNamesOrderByRatingIdAscLimit(long number) {
        List<String> names = new ArrayList<>();
        for (ClubEntity club : clubDAO.selectAllOrderByRatingIdAscLimit(number)) {
            names.add(club.getName().trim());
        }
        return names;
    }

    public List<String> getAllNamesOrderByRatingIdDescLimit(long number) {
        List<String> names = new ArrayList<>();
        for (ClubEntity club : clubDAO.selectAllOrderByRatingIdDescLimit(number)) {
            names.add(club.getName().trim());
        }
        return names;
    }

    public List<Double> getAllRatingsOrderByRatingIdAscLimit(long number) {
        List<Double> rating = new ArrayList<>();
        for (ClubEntity club : clubDAO.selectAllOrderByRatingIdAscLimit(number)) {
            rating.add(club.getRating());
        }
        return rating;
    }

    public List<Double> getAllRatingsOrderByRatingIdDescLimit(long number) {
        List<Double> rating = new ArrayList<>();
        for (ClubEntity club : clubDAO.selectAllOrderByRatingIdDescLimit(number)) {
            rating.add(club.getRating());
        }
        return rating;
    }

    public List<String> getNamesOrderByAscending(int limit) {
        return clubDAO
                .selectAllOrderByName(false, limit)
                .stream()
                .map(ClubEntity::getName)
                .collect(Collectors.toList());
    }

    public List<String> getNamesOrderByDescending(int limit) {
        return clubDAO
                .selectAllOrderByName(true, limit)
                .stream()
                .map(ClubEntity::getName)
                .collect(Collectors.toList());
    }
    
    public ClubEntity selectById(long id) {
        return clubDAO.selectById(id);
    }

    public List<ClubEntity> getAllNameWhereNameLike(String name) {
        return clubDAO.selectNameWhereNameLike(name);
    }

    public List<String> getAllNameByCity(String city) {
        return clubDAO
                .selectAllWhereCity(city)
                .stream()
                .map(ClubEntity::getName)
                .collect(Collectors.toList());
    }
}