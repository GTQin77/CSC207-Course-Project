package entity;

import java.util.ArrayList;

// I think we need to go through this Entity again, seems too cluttered.
public class Business {
    private String location;
    private float distance;
    private String contactNum;
    private String price;

    public Business(String location, float distance, String contactNum, String price) {
        this.location = location;
        this.distance = distance;
        this.contactNum = contactNum;
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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
}


