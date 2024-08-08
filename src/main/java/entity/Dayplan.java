package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import entity.Business;

public class Dayplan {
    private User user;
    private ArrayList<Double> location;
    private String city;
    private int numMeals;
    private int numActivities;
    private String description;
    private String vibe;
    private ArrayList<Business> plan;
    private ArrayList<String> businessIDs;

    public Dayplan() {plan = new ArrayList<>();}

    public void setUser(User user) {this.user = user;}
    public User getUser() {return this.user;}

    public void setLocation(ArrayList<Double> location){this.location = location;}
    public ArrayList<Double> getLocation(){return this.location;}

    public void setCity(String city){this.city = city;}
    public String getCity(){return this.city;}

    public void setNumMeals(int numMeals){this.numMeals = numMeals;}
    public int getNumMeals(){return this.numMeals;}

    public void setnumActivities(int numActivities){this.numActivities = numActivities;}
    public int getnumActivities(){return this.numActivities;}

    public void setDescription(String description){this.description = description;}
    public String getDescription(){return this.description;}

    public void setVibe(String vibe) {this.vibe = vibe;}
    public String getVibe(){return this.vibe;}

    public void setPlan(ArrayList<Business> plan){this.plan = plan;}
    public ArrayList<Business> getPlan() {return plan;}

    public ArrayList<String> getBusinessIDs() {
        return businessIDs;
    }

    public void setBusinessIDs(ArrayList<String> businessIDs) {
        this.businessIDs = businessIDs;
    }


    /**
     * A method that returns the String version of a Dayplan object for purposes of printing in the console.
     * Each business in Dayplan is on it's own line.
     * Each detail about a Business will be separated by commas on same line.
     * @return String that contains the above info.
     */
    public String dayplanToString(){
        StringBuilder businessList = new StringBuilder();
        // For each Business in the plan...
        for (int i = 0; i < this.getPlan().size(); i++){
            Business business = this.getPlan().get(i);
            String strBusiness = business.getName() + ", Location: " + business.getLocation().toString() +
                    ", Distance: " + business.getDistance() + ", Contact: " + business.getContactNum() +
                    ", Price: " + business.getPrice() + ", Ratings: " + business.getRatings() + "\n";
            businessList.append(strBusiness);
        }
        return this.getVibe() + "\n" + businessList.toString();

    }

    /**
     * Replace the businesses at a given index with a given business.
     *
     * @param index the index of the business that we want to replace
     * @param newBusiness the new business that we want to input into the dayplan
     * @return true if the business got replaced, false otherwise
     */
    public boolean replaceBusiness(int index, Business newBusiness, String iD) {
        if (index >= this.plan.size() || index < 0) {
            return false; // invalid index, fail to replace business
        }
        this.plan.set(index, newBusiness);
        this.businessIDs.set(index, iD);
        return true;
    }


    /**
     * Get the names of all business in the dayplan.
     * @return an arraylist of business name.
     */
    public ArrayList<String> getBusinessNames(){
        ArrayList<String> businessNames = new ArrayList<>();
        for (int i = 0; i < this.getPlan().size(); i++){
            Business business = this.getPlan().get(i);
            businessNames.add(business.getName());
        }
        return businessNames;
    }
}
