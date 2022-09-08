package com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocationModal;

public class Location {
    private String name;
    private String city;
    private String region;
    private String address;
    private String coordinates;
    private String phone;

    public Location(String name, String city, String region, String address, String coordinates, String phone) {
        this.name = name;
        this.city = city;
        this.region = region;
        this.address = address;
        this.coordinates = coordinates;
        this.phone = phone;
    }

    public Location(String name, String city, String address, String coordinates, String phone) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.coordinates = coordinates;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getPhone() {
        return phone;
    }

}