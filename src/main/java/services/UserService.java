package services;

import entity.Dayplan;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private User currentUser;
    private ArrayList<String> currentLocation;
    private Dayplan dayplan;

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
