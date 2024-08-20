package data_access;

import entity.Business;
import entity.Dayplan;
import entity.User;
import data_access.UserSignupDataAccessObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DayPlanDataAccessObject implements DayPlanDataAccessInterface{

    private File csvFile;
    private String csvPath;
    private User user;

    // csvFile attribute has no individual setter
    public File getcsvFile(){return this.csvFile;}

    // Method is a setter for both csvPath and csvFile
    public void setcsvPathAndcsvFile(String csvPath){
        this.csvPath = csvPath;
        this.csvFile = new File(csvPath);
    }

    public String getcsvPath(){return this.csvPath;}

    public void setUser(User user){this.user = user;}

    public User getUser(){return this.user;}

    /**
     * Method that writes a newly created Dayplan to the DayplanDatabase csv.
     * Each row in the csv refers to 1 Dayplan object.
     * Each cell corresponds to 1 Business object within Dayplan.
     * @param dayplan a Dayplan object that we want to save to a csv with an associated User.
     */
    public void saveDayPlan(Dayplan dayplan){
        try (FileWriter fw = new FileWriter(this.getcsvFile(), true)) {
            fw.write("\n");

            String stringLocation = UserSignupDataAccessObject.listToString(dayplan.getLocation());
            String finalLocation = "\"" + stringLocation.substring(1, stringLocation.length() - 2) + "\"";


            fw.write(dayplan.getUser().getUserName() + ";" + finalLocation + ";" + dayplan.getVibe() + ";");

            Business business = dayplan.getPlan().getFirst();
            fw.write(this.businessToString(business));
            for (int i = 1; i < dayplan.getPlan().size(); i++){
                business = dayplan.getPlan().get(i);
                fw.write("~" + this.businessToString(business));
            }
            System.out.println("Dayplan saved successfully!");
        }

        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that returns a String version of a Business object.
     * @param business Business object, example of Liskov Substitution Principle
     * @return String version of a Business, with attributes separated by commas
     */
    public String businessToString(Business business) {
        return String.join(",", business.getName(), business.getLocation().getFirst().toString(), business.getLocation().getLast().toString(),
                String.valueOf(business.getDistance()), business.getContactNum(), business.getPrice(), String.valueOf(business.getRatings()));
    }

}
