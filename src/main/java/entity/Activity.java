package entity;

import java.util.ArrayList;

// Public class Activity that inherits from Business and implements Timeblock
public class Activity extends Business implements Timeblock {
    public Activity(String name, ArrayList<Double> location, Double distance, String contactNum,
                    String price, Float rating) {
        super(name, location, distance, contactNum, price, rating);
    }

    // Implementing the Timeblock interface method to get duration of activity
    @Override
    public int getDuration() {
        return 0; // Replace this with implementation
    }
}
