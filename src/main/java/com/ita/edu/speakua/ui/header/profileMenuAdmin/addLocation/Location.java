package com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation;

public class Location {
    private String name;
    private String city;
    private String region;
    private String metro="";
    private String address;
    private String coordinates;
    private String phone;


    public Location(String name, String city, String region, String metro, String address, String coordinates, String phone) {
        this.name = name;
        this.city = city;
        this.region = region;
        this.metro = metro;
        this.address = address;
        this.coordinates = coordinates;
        this.phone = phone;
    }

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

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
