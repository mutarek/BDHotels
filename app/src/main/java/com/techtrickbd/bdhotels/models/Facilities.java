package com.techtrickbd.bdhotels.models;

import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Facilities {
    private String extra;
    private boolean internet;
    private boolean parking;
    private ArrayList<String> general = new ArrayList<String>();
    private ArrayList<String> miscellaneous = new ArrayList<String>();
    private ArrayList<String> services = new ArrayList<String>();

    public Facilities() {
    }

    public Facilities(String extra, boolean internet, boolean parking, ArrayList<String> general, ArrayList<String> miscellaneous, ArrayList<String> services) {
        this.extra = extra;
        this.internet = internet;
        this.parking = parking;
        this.general = general;
        this.miscellaneous = miscellaneous;
        this.services = services;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isInternet() {
        return internet;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public ArrayList<String> getGeneral() {
        return general;
    }

    public void setGeneral(ArrayList<String> general) {
        this.general = general;
    }

    public ArrayList<String> getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(ArrayList<String> miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

}
