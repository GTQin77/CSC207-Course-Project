package use_case.dayplanList;

import entity.Dayplan;
import entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserDayPlanInputData {

    private final String username;
    private final ArrayList<String> location;
    private final int numMeals;
    private final int numActivities;
    private final String description;

    public UserDayPlanInputData(String username, String location, int numMeals, int numActivities, String description) {
        this.username = username;
        this.location = new ArrayList<String>((List.of(location.split(","))));
        this.numMeals = numMeals;
        this.numActivities = numActivities;
        this.description = description;
    }

    public String getUsername(){return this.username;}

    public ArrayList<String> getLocation(){return this.location;}

    public int getNumMeals(){return this.numMeals;}

    public int getNumActivities(){return this.numActivities;}

    public String getDescription(){return this.description;}
}
