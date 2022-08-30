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
        for (CenterEntity center : centerDAO.selectOrderByNameAsc(limit)) {
            names.add(center.getName());
        }
        return names;
    }

    public List<String> getNamesOrderByNameDesc(long limit) {
        List<String> names = new ArrayList<>();
        for (CenterEntity center : centerDAO.selectOrderByNameDesc(limit)) {
            names.add(center.getName());
        }
        return names;
    }

    public List<String> getNamesInKyivOrderByNameAsc(long limit) {
        List<String> names = new ArrayList<>();
        for (CenterEntity center : centerDAO.selectInKyivOrderByNameAsc(limit)) {
            names.add(center.getName());
        }
        return names;
    }

    public List<String> getNamesInKyivOrderByNameDesc(long limit) {
        List<String> names = new ArrayList<>();
        for (CenterEntity center : centerDAO.selectInKyivOrderByNameDesc(limit)) {
            names.add(center.getName());
        }
        return names;
    }
}
