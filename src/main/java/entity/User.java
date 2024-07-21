package entity;

import java.util.ArrayList;

public class User {
    private String userName;
    private ArrayList<Double> location;
    private String mood;
    private int numActivities;
    private int numMeals;
    private ArrayList<Dayplan> dayPlans;

    public User(String userName, ArrayList<Double> location) {
        this.userName = userName;
        this.mood = "";
        this.numActivities = 0;
        this.numMeals = 0;
        this.location = location;
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

    public int getNumMeals() { return numMeals; }

    public void setNumMeals(int numMeals) {
        this.numMeals = numMeals;
    }

    public ArrayList<Dayplan> getDayPlans(){return this.dayPlans;}

    public void setDayPlans(ArrayList<Dayplan> dayPlans) {this.dayPlans = dayPlans;}
}

