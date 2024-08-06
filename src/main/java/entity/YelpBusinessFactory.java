package entity;

import api.YelpFusion;

import java.util.ArrayList;

public class YelpBusinessFactory implements BusinessFactory {
    private final YelpFusion yelpFusion;
    private User user;


    public YelpBusinessFactory(YelpFusion yelpFusion) {
        this.yelpFusion = yelpFusion;
    }

    public void setUser(User user){this.user = user;}

    /**
     * Creating a business object based on the provided businessID.
     * @param businessID The id of the business.
     * @return a business object by providing the businessID.
     */
    @Override
    public Business createBusiness(String businessID, ArrayList<Double> userLocation) {
        ArrayList<Object> businessDetails = yelpFusion.getBusiness(businessID);
        String name = (String) businessDetails.get(0);
        Float rating = (Float) businessDetails.get(1);
        String price = (String) businessDetails.get(2);
        String contactNum = (String) businessDetails.get(3);
        ArrayList<Double> location = (ArrayList<Double>) businessDetails.get(4);



        Double distance = getDifference(location, userLocation);
        String type = "";

        return new Business(name, location, distance, contactNum, price, rating, type);
    }

    /**
     * Calculate the difference between two ArrayLists with longitude and latitude.
     * @param location1 is at ArrayList[0]. Negative numbers are west.
     * @param location2 is at ArrayList[1]. Negative numbers are south.
     * @return a double that is the distance between the two locations
     * */
    private static double getDifference(ArrayList<Double> location1, ArrayList<Double> location2) {
        double differenceNm;
        double radLong1;
        double radLong2;
        double radLat1;
        double radLat2;
        //IN NM
        double radiusEarth = 3440.1;
        double haversineInput;
        double differenceKm;

        radLong1 = Math.toRadians(location1.get(0));
        radLong2 = Math.toRadians(location2.get(0));
        radLat1 = Math.toRadians(location1.get(1));
        radLat2 = Math.toRadians(location2.get(1));

        haversineInput = (Math.sin(radLat1) * Math.sin(radLat2)) + Math.cos(radLat1) *
                Math.cos(radLat2) * Math.cos(radLong1 - radLong2);

        // Haversine Formula
        differenceNm = radiusEarth * Math.acos(haversineInput);
        differenceKm = differenceNm * 1.852;

        return differenceKm;
    }

}
