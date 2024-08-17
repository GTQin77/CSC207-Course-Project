package api;
import java.util.ArrayList;
import org.json.JSONArray;

import static org.junit.jupiter.api.Assertions.*;
class YelpFusionTest {

    @org.junit.jupiter.api.Test
    void getApiToken() {
        String token = System.getenv("API_TOKEN");
        assertNotNull(token);
    }

    @org.junit.jupiter.api.Test
    void getBusinessID() {
        String category = "food";
        String cityName = "Toronto";

        int numIDs = 20;

        YelpFusion yelpFusion = new YelpFusion();
        ArrayList<String> businessIDs = yelpFusion.getBusinessIDs(category, cityName, numIDs);

        assertNotNull(businessIDs);
        assertEquals(numIDs, businessIDs.size());

    }

    @org.junit.jupiter.api.Test
    void getBusiness() {
        // this ID is taken from Yelp Fusion api website, it is an example they have there
        String businessID = "north-india-restaurant-san-francisco";

        YelpFusion yelpFusion = new YelpFusion();
        ArrayList<Object> businessDetails = yelpFusion.getBusiness(businessID);

        assertNotNull(businessDetails);
        assertEquals(5, businessDetails.size());

        assertInstanceOf(String.class, businessDetails.get(0));
        assertInstanceOf(Float.class, businessDetails.get(1));
        assertInstanceOf(String.class, businessDetails.get(2));
        assertInstanceOf(String.class, businessDetails.get(3));
        assertInstanceOf(ArrayList.class, businessDetails.get(4));

    }
//
//    @org.junit.jupiter.api.Test
//    void getBusinessReviews() {
//        String businessID = "north-india-restaurant-san-francisco";
//        YelpFusion yelpFusion = new YelpFusion();
//
//        JSONArray businessReviews = yelpFusion.getBusinessReviews(businessID);
//        assertNotNull(businessReviews);
//        assertEquals(3, businessReviews.length());
//    }
}