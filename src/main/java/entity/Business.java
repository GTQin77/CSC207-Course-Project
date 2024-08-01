package entity;

import java.util.ArrayList;

public class Business {
    private String name;
    private ArrayList<Double> location;
    private Double distance;
    private String contactNum;
    private String price;
    private Float ratings;

    public Business(String name, ArrayList<Double> location, Double distance, String contactNum,
                    String price, Float rating) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.contactNum = contactNum;
        this.price = price;
        this.ratings = rating;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public ArrayList<Double> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Double> location) {
        this.location = location;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
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

    public Float getRatings() { return ratings; }

    public void setRatings(Float ratings) { this.ratings = ratings; }
}


