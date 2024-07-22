package use_case.dayplanList;

import entity.Dayplan;
import entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserDayPlanInputData {

    private final User user;
    private final ArrayList<String> location;
    private final String city;
    private final int numMeals;
    private final int numActivities;
    private final String description;

    public UserDayPlanInputData(User user, String location, String city, int numMeals, int numActivities, String description) {
        this.user = user;
        this.location = new ArrayList<String>((List.of(location.substring(1, location.length() - 1).split(","))));
        this.city = city;
        this.numMeals = numMeals;
        this.numActivities = numActivities;
        this.description = description;
    }

    public User getUser(){return this.user;}

    public ArrayList<String> getLocation(){return this.location;}

    public String getCity(){return this.city;}

    public int getNumMeals(){return this.numMeals;}

    public int getNumActivities(){return this.numActivities;}

    public String getDescription(){return this.description;}
}
