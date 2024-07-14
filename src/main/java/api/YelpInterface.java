package api;
import entity.Events;
import org.json.JSONArray;

import java.util.ArrayList;

public interface YelpInterface {
    String getBusinessID(String category, ArrayList<Float> location, int i);
    Events getEvents(String businessID);
    JSONArray getEventsReviews(String businessID);
}
