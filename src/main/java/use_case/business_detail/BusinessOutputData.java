package use_case.business_detail;

import java.util.ArrayList;

// note to future developer for the View:
// We can use ImageIcon from the javax.swing package to display image
// import javax.swing.ImageIcon;
// import javax.swing.JFrame;
// import javax.swing.JLabel;

public class BusinessOutputData {
    private final ArrayList<Float> location;
    private final Float distance;
    private final String contactNum;
    private final String name;
    private final ArrayList<String> reviews;
    private final String pathToImage;

    public BusinessOutputData(ArrayList<Float> location, Float distance, String contactNum,
                              String name, ArrayList<String> reviews, String pathToImage) {
        this.location = location;
        this.distance = distance;
        this.contactNum = contactNum;
        this.name = name;
        this.reviews = reviews;
        this.pathToImage = pathToImage;
    }

    public ArrayList<Float> getLocation() {
        return location;
    }

    public Float getDistance() {
        return distance;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public String getPathToImage() {
        return pathToImage;
    }
}
