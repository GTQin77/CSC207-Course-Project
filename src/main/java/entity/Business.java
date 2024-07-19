package entity;

import java.util.ArrayList;

// I think we need to go through this Entity again, seems too cluttered.
public class Business {
    private String name;
    private ArrayList<Float> location;
    private float distance;
    private String contactNum;
    private String price;
    private String ratings;

    public Business(String name, ArrayList<Float> location, float distance, String contactNum,
                    String price, String rating) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.contactNum = contactNum;
        this.price = price;
        this.ratings = rating;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public ArrayList<Float> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Float> location) {
        this.location = location;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRatings() { return ratings; }

    public void setRatings(String ratings) { this.ratings = ratings; }
}


