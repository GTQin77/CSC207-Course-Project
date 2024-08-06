package entity;

import api.OpenInterface;
import api.YelpInterface;

import java.util.ArrayList;
import java.util.Objects;

public class CommonRefreshBusinessFactory implements RefreshBusinessFactory{
    OpenInterface openApi;
    YelpInterface yelpApi;
    BusinessFactory businessFactory;

    public void setOpenApi(OpenInterface openApi) {this.openApi = openApi;}
    public OpenInterface getOpenApi(){return this.openApi;}

    public void setYelpApi(YelpInterface yelpApi){this.yelpApi = yelpApi;}
    public YelpInterface getYelpApi(){return this.yelpApi;}

    public void setBusinessFactory(BusinessFactory businessFactory){this.businessFactory = businessFactory;}

    /**
     * Generates a new business meant to replace a pre-existing business in a given dayplan.
     * @param dayplan a user's pre-existing dayplan that we want to generate a new business for.
     * @param type the type of a business(Activity or Meal)
     * @return a new Business object.
     */
    @Override
    public Business generateNewBusiness(Dayplan dayplan, String type) {
        User user = dayplan.getUser();
        String city = dayplan.getCity();
        String description = dayplan.getDescription();
        ArrayList<String> prevBusinessIDs = dayplan.getBusinessIDs();

        boolean isMeal = this.isMeal(type);

        String category = this.openApi.getCategory(description, isMeal);

        ArrayList<String> newBusinessIDs = this.yelpApi.getBusinessIDs(category, city, 20);

        int i = 0;
        while (prevBusinessIDs.contains(newBusinessIDs.get(i))){       // NOTE: add a case to handle if all id's are same?
            i = i + 1;
        }

        return this.businessFactory.createBusiness(newBusinessIDs.get(i), user.getLocation());
    }


    public boolean isMeal(String type){
        return type.equals("meal");
    }
}
