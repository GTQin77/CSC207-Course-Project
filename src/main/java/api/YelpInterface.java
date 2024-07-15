package api;
import entity.Business;
import org.json.JSONArray;

import java.util.ArrayList;

public interface YelpInterface {
    String getBusinessID(String category, ArrayList<Float> location);
    Business getBusiness(String businessID);
    JSONArray getBusinessReviews(String businessID);
}
