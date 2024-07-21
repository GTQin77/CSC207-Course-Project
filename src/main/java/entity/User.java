package entity;

import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<Double> location;
    private ArrayList<Dayplan> dayPlans;

    public User(String userName, String password, ArrayList<Double> location) {
        this.userName = userName;
        this.password = password;
        this.location = location;
    }

    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password = password;}

    public ArrayList<Double> getLocation(){return this.location;}
    public void setLocation(ArrayList<Double> location){this.location = location;}

    public ArrayList<Dayplan> getDayPlans(){return this.dayPlans;}
    public void setDayPlans(ArrayList<Dayplan> dayPlans) {this.dayPlans = dayPlans;}
}

