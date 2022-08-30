package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.ClubDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dto.CenterDTO;
import com.ita.edu.speakua.ui.utils.jdbc.dto.ClubWithCenterDTO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.ClubEntity;

import java.util.ArrayList;
import java.util.List;

public class ClubService {
    private  final ClubDAO clubDAO;
    private  final CenterDAO centerDAO;
    public ClubService() {
        clubDAO = new ClubDAO();
        centerDAO = new CenterDAO();
    }
    public List<ClubEntity> getAll() {
        return clubDAO.selectAll();
    }
    public List<String> getAllName() {
        List<String> names = new ArrayList<>();
        for (ClubEntity club: clubDAO.selectAll()) {
            names.add(club.getName());
        }
        return names;
    }

    public List<ClubWithCenterDTO> getAllClubWithCenter() {
        List<ClubWithCenterDTO> clubs = new ArrayList<>();
        for (ClubEntity club: clubDAO.selectAll()) {
            ClubWithCenterDTO clubDTO = new ClubWithCenterDTO();
            clubDTO.setClub(club);
            CenterDTO center = new CenterDTO();
            center.setCenter(centerDAO.selectById(club.getCenterId()));
            clubs.add(clubDTO);
        }
        return clubs;
    }

    public ClubEntity selectById(long id) {
        return clubDAO.selectById(id);
    }
}
