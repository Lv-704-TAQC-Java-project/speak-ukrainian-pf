package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.CenterEntity;

import java.util.ArrayList;
import java.util.List;

public class CenterService {
    private final CenterDAO centerDAO;

    public CenterService() {
        centerDAO = new CenterDAO();
    }

    public List<CenterEntity> getAll() {
        return centerDAO.selectAll();
    }

    public List<String> getAllName(String name) {
        List<String> names = new ArrayList<>();
        for (CenterEntity center : centerDAO.selectByName(name)) {
            names.add(center.getName());
        }
        return names;
    }

    public List<String> getNamesOrderByNameAsc(long limit) {
        List<String> names = new ArrayList<>();
        for (CenterEntity center : centerDAO.selectOrderByName(false, limit)) {
            names.add(center.getName());
        }
        return names;
    }

    public List<String> getNamesOrderByNameDesc(long limit) {
        List<String> names = new ArrayList<>();
        for (CenterEntity center : centerDAO.selectOrderByName(true, limit)) {
            names.add(center.getName());
        }
        return names;
    }

    public List<String> getNamesInKyivOrderByName(String city, boolean desc, long limit) {
        List<String> names = new ArrayList<>();
        for (CenterEntity center : centerDAO.selectInCityOrderByName("Київ", desc, limit)) {
            names.add(center.getName().trim().replaceAll("\\s+", " "));
        }
        return names;
    }
}
