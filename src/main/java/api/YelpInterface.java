package api;
import org.json.JSONArray;

import java.util.ArrayList;

public interface YelpInterface {
    ArrayList<String> getBusinessID(String category, String city, Integer i);
    ArrayList<Object> getBusiness(String businessID);
    JSONArray getBusinessReviews(String businessID);
}
