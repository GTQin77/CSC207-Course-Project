package entity;

import java.util.ArrayList;

// Public class Meal that inherits from Business and implements Timeblock
public class Meal extends Business implements Timeblock {
    public Meal(String name, ArrayList<Float> location, float distance, String contactNum,
                String price, Float rating) {
        super(name, location, distance, contactNum, price, rating);
    }

    // Implementing the Timeblock interface method to get duration of meal
    @Override
    public int getDuration() {
        return 0; // Replace this with implementation
    }
}
