package api;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Interface of Yelp Fusion. Details description of the methods can be found in YelpFusion.java.
 */
public interface YelpInterface {
    ArrayList<String> getBusinessID(String category, String city, Integer i);
    ArrayList<Object> getBusiness(String businessID);
    JSONArray getBusinessReviews(String businessID);
}
