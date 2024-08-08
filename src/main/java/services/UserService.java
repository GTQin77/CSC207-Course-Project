package services;

import entity.Business;
import entity.Dayplan;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private User currentUser;
    private ArrayList<String> currentLocation;
    private Dayplan dayplan;

    public UserService() {
        this.dayplan = loadInitialDayplan();
    }

    private Dayplan loadInitialDayplan() {
        return new Dayplan();
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    private Business business;

    public String getPrevView() {
        return prevView;
    }

    public void setPrevView(String prevView) {
        this.prevView = prevView;
    }

    private String prevView;

    public Dayplan getDayplan() {
        return dayplan;
    }

    public void setDayplan(Dayplan dayplan) {
        this.dayplan = dayplan;
    }

    public ArrayList<String> getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String locationString) {
        this.currentLocation = new ArrayList<>(List.of(locationString.split(",")));
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
