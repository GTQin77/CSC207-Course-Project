package entity;

public class Activity extends Business implements Timeblock {
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

    // Implementing the Timeblock interface method
    @Override
    public int getDuration() {
        return 0; // Replace this with implementation
    }
}
