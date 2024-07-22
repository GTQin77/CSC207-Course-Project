package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dayplan {
    private User user;
    private ArrayList<Double> location;
    private String city;
    private int numMeals;
    private int numActivities;
    private String description;
    private String vibe;
    private ArrayList<Business> plan;

    public Dayplan() {plan = new ArrayList<>();}

    public void setUser(User user) {this.user = user;}
    public User getUser() {return this.user;}

    public void setLocation(ArrayList<Double> location){this.location = location;}
    public ArrayList<Double> getLocation(){return this.location;}

    public void setCity(String city){this.city = city;}
    public String getCity(){return this.city;}

    public void setNumMeals(int numMeals){this.numMeals = numMeals;}
    public int getNumMeals(){return this.numMeals;}

    public void setnumActivities(int numActivities){this.numActivities = numActivities;}
    public int getnumActivities(){return this.numActivities;}

    public void setDescription(String description){this.description = description;}
    public String getDescription(){return this.description;}

    public void setVibe(String vibe) {this.vibe = vibe;}
    public String getVibe(){return this.vibe;}

    public void setPlan(ArrayList<Business> plan){this.plan = plan;}
    public ArrayList<Business> getPlan() {return plan;}
}