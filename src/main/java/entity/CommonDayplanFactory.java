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
    BusinessFactory businessFactory;

    public void setOpenApi(OpenInterface openApi) {this.openApi = openApi;}
    public OpenInterface getOpenApi(){return this.openApi;}

    public void setYelpApi(YelpInterface yelpApi){this.yelpApi = yelpApi;}
    public YelpInterface getYelpApi(){return this.yelpApi;}

    public void setBusinessFactory(BusinessFactory businessFactory){this.businessFactory = businessFactory;}


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
        dayplan.setCity(city);
        dayplan.setNumMeals(numMeals);
        dayplan.setnumActivities(numActivities);
        dayplan.setDescription(description);

        // 1. Call OpenAI API, receive category from description
        String activityCategory = this.openApi.getCategory(description, false);
        String mealCategory = this.openApi.getCategory(description, true);

        // 2. Calling Yelp API with the category
        ArrayList<String> mealsIDs = this.yelpApi.getBusinessIDs(mealCategory, city, numMeals);
        ArrayList<String> activityIDs = this.yelpApi.getBusinessIDs(activityCategory, city, numActivities);

        // 3. Calling Yelp API to create Business objects for each Business
        ArrayList<Business> plan = new ArrayList<Business>();
        ArrayList<String> businessIDs = new ArrayList<String>();
        // For ACTIVITIES
        for (int i = 0; i < numActivities; i++){
            String businessID = activityIDs.get(i);
            // NOTE TO SELF: Do we need to pass in the api??
            Business activity = this.businessFactory.createBusiness(businessID, user.getLocation());  // Casting Business to Activity
            // Adding the Activity object to ArrayList
            plan.add(activity);
            businessIDs.add(businessID);
        }
        // For MEALS
        for (int i = 0; i < numMeals; i++){
            String businessID = mealsIDs.get(i);
            Business meal = this.businessFactory.createBusiness(businessID, user.getLocation());
            plan.add(meal);
            businessIDs.add(businessID);
        }
        dayplan.setPlan(plan);
        dayplan.setBusinessIDs(businessIDs);

        // 4. Calling OpenAI API to get the vibe attribute
        // Must create 1 String that contains sequence of all business names
        StringBuilder collectionNames = new StringBuilder();
        for (int i = 0; i < plan.size(); i++){
            Business currBusiness = plan.get(i);
            collectionNames.append(", ").append(currBusiness.getName());
        }
        String businessNames = collectionNames.toString();

        String vibe = this.openApi.getVibe("\"" + businessNames + "\"");
        dayplan.setVibe(vibe);

        return dayplan;
    }
}
