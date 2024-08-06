package entity;

public class PreviousBusiness {
    private final String business;
    private final String location;
    private final String distance;
    private final String phoneNumber;
    private final String price;
    private final String rating;

    PreviousBusiness(String business, String location, String distance, String phoneNumber, String price,
                     String rating) {
        this.business = business;
        this.location = location;
        this.distance = distance;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.rating = rating;
    }

    public String getBusiness() {
        return this.business;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDistance() {
        return this.distance;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPrice() {
        return this.price;
    }

    public String getRating() {
        return this.rating;
    }
}
