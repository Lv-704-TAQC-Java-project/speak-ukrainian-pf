package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.CenterEntity;

import java.util.List;

public class CenterService {
    private final CenterDAO centerDAO;

    public CenterService() {
        centerDAO = new CenterDAO();
    }

    public List<CenterEntity> getAllCenters() {
        return centerDAO.selectAllCenters();
    }

    public String[] getCenterNames(String city, String orderBy, boolean desc, long limit) {
        return centerDAO
                .selectCenters(city, orderBy, desc, limit)
                .stream()
                .map(centerEntity -> centerEntity
                        .getName()
                        .trim()
                        .replaceAll("\\s+", " "))
                .toArray(String[]::new);
    }
}