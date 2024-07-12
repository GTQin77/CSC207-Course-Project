package entity;

import java.util.ArrayList;

public class Events {
        private String location;
        private float distance;
        private String contactNum;
        private String price;

        public Events (String location, float distance, String contactNum, String price) {
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

        public static class Meal extends Events {
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
    }

        public static class Activity extends Events {
            private String activityName;
            private int reviews;

        public Activity(String activityName, String location, float distance,
                        int reviews, String contactNum, String price) {
            super(location, distance, contactNum, price);
            this.activityName = activityName;
            this.reviews = reviews;
        }

        // Getters and Setters
        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getReviews() {
            return reviews;
        }

        public void setReviews(int reviews) {
            this.reviews = reviews;
        }
    }
}