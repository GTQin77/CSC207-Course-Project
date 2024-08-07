package interface_adapter.BusinessDetails;

public class BusinessDetailsViewModel {
    private String name;
    private String location;
    private String distance;
    private String contactNum;
    private String price;
    private String ratings;
    private String type;

    public BusinessDetailsViewModel(String name, String location, String distance,
                                    String contactNum, String price, String ratings, String type) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.contactNum = contactNum;
        this.price = price;
        this.ratings = ratings;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
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

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

