package com.ita.edu.speakua.ui.utils.jdbc.services;

import com.ita.edu.speakua.ui.utils.jdbc.dao.LocationDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.LocationEntity;

import java.util.List;

public class LocationService {
    private final LocationDAO locationDAO;

    public LocationService() {
        locationDAO = new LocationDAO();
    }

    public List<LocationEntity> getLocationByName(String name) {
        return locationDAO.selectByName(name);
    }
}
