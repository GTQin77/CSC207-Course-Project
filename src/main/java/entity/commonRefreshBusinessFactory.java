package entity;

import api.OpenInterface;
import api.YelpInterface;

import java.util.ArrayList;
import java.util.Objects;
import entity.BusinessFactory;

public class commonRefreshBusinessFactory implements refreshBusinessFactory{
    OpenInterface openApi;
    YelpInterface yelpApi;
    BusinessFactory businessFactory;

    public void setOpenApi(OpenInterface openApi) {this.openApi = openApi;}
    public OpenInterface getOpenApi(){return this.openApi;}

    public void setYelpApi(YelpInterface yelpApi){this.yelpApi = yelpApi;}
    public YelpInterface getYelpApi(){return this.yelpApi;}

    public void setBusinessFactory(BusinessFactory businessFactory){this.businessFactory = businessFactory;}

    @Override
    public Business generateNewBusiness(Dayplan dayplan, String type) {
        User user = dayplan.getUser();
        String city = dayplan.getCity();
        String description = dayplan.getDescription();
        ArrayList<String> businessIDs = dayplan.getBusinessIDs();


        String category = this.openApi.getCategory(description);

        ArrayList<String> mealsIDs = this.yelpApi.getBusinessIDs("restaurants", city, 20);
        ArrayList<String> activityIDs = this.yelpApi.getBusinessIDs(category, city, 20);
        Business newBusiness = null;
        if (Objects.equals(type, "meal")){
            int i = 0;
            while (businessIDs.contains(mealsIDs.get(i))){
                i = i + 1;
            }
            newBusiness = this.businessFactory.createBusiness(mealsIDs.get(i));
        }
        else if (Objects.equals(type, "activity")){
            int i = 0;
            while (businessIDs.contains(activityIDs.get(i))){
                i = i + 1;
            }
            newBusiness = this.businessFactory.createBusiness(activityIDs.get(i));
        }

        return newBusiness;
// =======
        // String activityCategory = this.openApi.getCategory(description, false);
        // String mealCategory = this.openApi.getCategory(description, true);

        // ArrayList<String> mealsIDs = this.yelpApi.getBusinessIDs(mealCategory, city, dayplan.getNumMeals());
        // ArrayList<String> activityIDs = this.yelpApi.getBusinessIDs(activityCategory, city, dayplan.getnumActivities());
        // NOTE TO SELF: REPLACE RETURN STATEMENT
        // return null;
    }
}
