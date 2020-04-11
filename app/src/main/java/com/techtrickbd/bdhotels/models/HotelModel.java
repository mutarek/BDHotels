package com.techtrickbd.bdhotels.models;

import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;

public class HotelModel {
    private String name;
    private String description;
    private boolean room_exist;
    private String rating;
    private int price;
    private GeoPoint location;
    private ArrayList<String> hotelimages = new ArrayList<String>();
    private Facilities facilities;

    public HotelModel() {
    }

    public HotelModel(String name, String description, boolean room_exist, String rating, int price, GeoPoint location, ArrayList<String> hotelimages, Facilities facilities) {
        this.name = name;
        this.description = description;
        this.room_exist = room_exist;
        this.rating = rating;
        this.price = price;
        this.location = location;
        this.hotelimages = hotelimages;
        this.facilities = facilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRoom_exist() {
        return room_exist;
    }

    public void setRoom_exist(boolean room_exist) {
        this.room_exist = room_exist;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public ArrayList<String> getHotelimages() {
        return hotelimages;
    }

    public void setHotelimages(ArrayList<String> hotelimages) {
        this.hotelimages = hotelimages;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public void setFacilities(Facilities facilities) {
        this.facilities = facilities;
    }
}