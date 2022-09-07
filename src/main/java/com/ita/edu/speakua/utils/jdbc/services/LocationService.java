package com.ita.edu.speakua.utils.jdbc.services;

import com.ita.edu.speakua.utils.jdbc.dao.LocationDAO;
import com.ita.edu.speakua.utils.jdbc.entity.LocationEntity;

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
