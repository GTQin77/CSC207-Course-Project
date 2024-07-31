package use_case.distance;
import entity.User;

import java.util.ArrayList;

public class DistanceInputData {
    final private User user;
    final private ArrayList<Double> location;
    final private Float distanceCap;
    //add variable for location of resto/event?
    final private ArrayList<Double> businessLocation;

    public DistanceInputData(User user, Float distanceCap, ArrayList<Double> businessLocation) {
        this.user = user;
        this.location = user.getLocation();
        this.distanceCap = distanceCap;
        this.businessLocation = businessLocation;
    }

    User getUser() {return this.user;}

    ArrayList<Double> getLocation() {return this.location;}

    Float getDistanceCap() {return this.distanceCap;}

    ArrayList<Double> getBusinessLocation() {return this.businessLocation;}
}
