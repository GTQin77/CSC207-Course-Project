package use_case.distance;

import entity.Business;

import java.util.ArrayList;

public class DistanceOutputData {
    private final ArrayList<Business> events;

    public DistanceOutputData(ArrayList<Business> events) {
        this.events = events;
    }

    public ArrayList<Business> getEvents() {return events;}


}
