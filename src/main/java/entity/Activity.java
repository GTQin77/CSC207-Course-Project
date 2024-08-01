package entity;

import java.util.ArrayList;

// Public class Activity that inherits from Business.
public class Activity extends Business {
    public Activity(String name, ArrayList<Double> location, Double distance, String contactNum,
                    String price, Float rating) {
        super(name, location, distance, contactNum, price, rating);
    }
}
