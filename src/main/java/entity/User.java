package entity;

import java.util.ArrayList;

public class User {
    private int userID;
    private String userName;
    private ArrayList<Double> location;
    private String mood;
    private int numActivities;
    private int numMeals;
    private ArrayList<Dayplan> dayPlans;

    public User(int userID, String userName, ArrayList<Double> location, String mood, int numActivities, int numMeals) {
        this.userID = userID;
        this.userName = userName;
        this.mood = mood;
        this.numActivities = numActivities;
        this.numMeals = numMeals;
        this.location = new ArrayList<>();
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Double> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Double> location) {
        this.location = location;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getNumActivities() {
        return numActivities;
    }

    public void setNumActivities(int numActivities) {
        this.numActivities = numActivities;
    }

    public int getNumMeals() {
        return numMeals;
    }

    public void setNumMeals(int numMeals) {
        this.numMeals = numMeals;
    }

    public ArrayList<Dayplan> getDayPlans(){return this.dayPlans;}

    public void setDayPlans(ArrayList<Dayplan> dayPlans) {this.dayPlans = dayPlans;}
}

