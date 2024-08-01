package entity;

import java.util.ArrayList;

// Public class Meal that inherits from Business.
public class Meal extends Business {
    public Meal(String name, ArrayList<Double> location, Double distance, String contactNum,
                String price, Float rating) {
        super(name, location, distance, contactNum, price, rating);
    }
}
