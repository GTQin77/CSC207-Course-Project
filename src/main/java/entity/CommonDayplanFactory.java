package entity;

import api.YelpFusion;
import entity.DayplanFactory;
import entity.BusinessFactory;
import entity.Business;
import entity.Meal;
import entity.Activity;
import api.OpenInterface;
import api.YelpInterface;

import java.util.ArrayList;

public class CommonDayplanFactory implements DayplanFactory{

    OpenInterface openApi;
    YelpInterface yelpApi;

    public void setOpenApi(OpenInterface openApi) {this.openApi = openApi;}
    public OpenInterface getOpenApi(){return this.openApi;}

    public void setYelpApi(YelpInterface yelpApi){this.yelpApi = yelpApi;}
    public YelpInterface getYelpApi(){return this.yelpApi;}

    public

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

        // 2. Calling OpenAI API to get a category
        String category = this.openApi.getCategory(userMessage);

        // 3. Calling Yelp API with the category
        ArrayList<String> mealsIDs = this.yelpApi.getBusinessIDs("restaurants", city, numMeals);
        ArrayList<String> activityIDs = this.yelpApi.getBusinessIDs(category, city, numActivities);

        // 4. Calling Yelp API to create Business objects for each Business
        for (int i = 0; i < numActivities; i++){
            String businessID = activityIDs.get(i);

            BusinessFactory businessFactory = new YelpBusinessFactory((YelpFusion)this.getYelpApi());   // Casting yelpApi to YelpFusion type




            Business business = BusinessFactory.createBusiness(businessID);
            dayplan.getPlan().add(business);
        }

        return dayplan;
    }
}
