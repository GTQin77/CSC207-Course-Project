package use_case.distance;

import entity.Events;

import java.util.ArrayList;

public class DistanceOutputData {
    private final ArrayList<Events> events;

    public DistanceOutputData(ArrayList<Events> events) {
        this.events = events;
    }

    public ArrayList<Events> getEvents() {return events;}


}
