package entity;

import java.util.ArrayList;

public class Events {
    private ArrayList<Float> location;
    private float distance;
    private String contactNum;


    public Events(ArrayList<Float> location, float distance, String contactNum) {
        this.location = location;
        this.distance = distance;
        this.contactNum = contactNum;
    }

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
}