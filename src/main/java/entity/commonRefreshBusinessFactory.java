package entity;

import api.OpenInterface;
import api.YelpInterface;

import java.util.ArrayList;

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
    public Business generateNewBusiness(Dayplan dayplan) {
        User user = dayplan.getUser();
        String city = dayplan.getCity();
        String description = dayplan.getDescription();

        String category = this.openApi.getCategory(description);

        ArrayList<String> mealsIDs = this.yelpApi.getBusinessIDs("restaurants", city, );
        ArrayList<String> activityIDs = this.yelpApi.getBusinessIDs(category, city, numActivities);


    }
}
