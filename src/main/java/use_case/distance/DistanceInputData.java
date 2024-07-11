package use_case.distance;
import entity.User;

import java.util.ArrayList;

public class DistanceInputData {
    final private User user;
    final private ArrayList<Float> location;
    final private Float distanceCap;
    public DistanceInputData(User user, ArrayList<Float> location, Float distanceCap) {
        this.user = user;
        this.location = user.getLocation();
        this.distanceCap = distanceCap;
    }

    User getUser() {return user;}

    ArrayList<Float> getLocation() {return location;}

    Float getDistanceCap() {return distanceCap;}
}
