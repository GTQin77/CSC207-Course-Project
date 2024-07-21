package entity;

import java.util.ArrayList;

public interface DayplanFactory {
    Dayplan create(User user, ArrayList<Double> location, String city, int numMeals, int numActivities, String description);
}
