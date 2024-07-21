package entity;

import entity.DayplanFactory;
import entity.BusinessFactory;
import entity.Business;
import entity.Meal;
import entity.Activity;
import api.OpenInterface;
import api.YelpInterface;

import java.util.ArrayList;

public class CommonDayplanFactory implements DayplanFactory{

    /**
     * A method that holds implementation for creation of new Dayplan object.
     * @param user User that owns the dayplan.
     * @param location Location that the dayplan surrounds.
     * @param city City that the user's location is in.
     * @param numMeals Number of meals User wants in dayplan.
     * @param numActivities Number of activities User wants in dayplan.
     * @param description Abstract input the user inputs at the beginning of program.
     * @return Dayplan object with all businesses initialized.
     */

    @Override
    public Dayplan create(User user, ArrayList<Double> location, String city, int numMeals, int numActivities, String description) {
        Dayplan dayplan = new Dayplan();
        dayplan.setUser(user);
        dayplan.setLocation(location);
        dayplan.setNumMeals(numMeals);
        dayplan.setnumActivities(numActivities);
        dayplan.setDescription(description);

        // 1. Call OpenAI API, receive category from description
        String userMessage = "Given the following prompt:" + "\"" + description + "\"" + ", pick 1 category from: " +
                "\"active, arts, beautysvc, localflavor, nightlife, shopping\"." + " Respond with just the category.";

        for (int i = 0; i < numActivities; i++){
            Business business = BusinessFactory.createBusiness();
            dayplan.getPlan().add(business);
        }

        return dayplan;
    }
}
