package entity;

import java.util.ArrayList;

public class Meal extends Business implements Timeblock {
    private String restaurant;
    private ArrayList<Float> coordinates;
    private float rating;

    public Meal(String restaurant, ArrayList<Float> coordinates, float distance, float rating,
                String contactNum, String price) {
        super("Coordinates: " + coordinates.toString(), distance, contactNum, price);
        this.restaurant = restaurant;
        this.coordinates = coordinates;
        this.rating = rating;
    }

    //Getters and Setters
    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Float> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Float> coordinates) {
        this.coordinates = coordinates;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    // Implementing the Timeblock interface method
    @Override
    public int getDuration() {
        return 0; // Replace this with implementation
    }
}
