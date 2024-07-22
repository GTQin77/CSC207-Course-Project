package entity;

import api.YelpFusion;

import java.util.ArrayList;

public class YelpBusinessFactory implements BusinessFactory {
    private final YelpFusion yelpFusion;

    public YelpBusinessFactory(YelpFusion yelpFusion) {
        this.yelpFusion = yelpFusion;
    }

    /**
     * Creating a business object based on the provided businessID.
     * @param businessID The id of the business.
     * @return a business object by providing the businessID.
     */
    @Override
    public Business createBusiness(String businessID) {
        ArrayList<Object> businessDetails = yelpFusion.getBusiness(businessID);
        String name = (String) businessDetails.get(0);
        Float rating = (Float) businessDetails.get(1);
        // String price = (String) businessDetails.get(2);
        String contactNum = (String) businessDetails.get(2);
        ArrayList<Double> location = (ArrayList<Double>) businessDetails.get(3);

        Double distance = 0.0;

        return new Business(name, location, distance, contactNum, "expensive", rating);
    }
}
