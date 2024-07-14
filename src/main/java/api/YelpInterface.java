package api;
import entity.Events;

import java.util.ArrayList;

public interface YelpInterface {
    String getBusinessID(String keyword, ArrayList<Float> location);
    Events getEvents(String businessID);
}
