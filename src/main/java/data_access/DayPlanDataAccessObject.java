package data_access;

import entity.Dayplan;
import entity.User;

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

    // public String getcsvPath(){return this.csvPath;}

    public void setUser(User user){this.user = user;}

    public User getUser(){return this.user;}

    /**
     * Method that writes a newly created Dayplan to the DayplanDatabase csv.
     * Each row in the csv refers to 1 Dayplan object.
     * Each cell corresponds to 1 Business object within Dayplan.
     * @param dayplan a Dayplan object that we want to save to a csv with an associated User.
     */
    public void saveDayPlan(Dayplan dayplan){
        // Same structure as existsByName, using try/catch block
        // Use FileWriter class
        // Try block automatically closes FileWriter class
        try (FileWriter fw = new FileWriter(this.getcsvFile(), true)) {
            fw.write("\n");
            fw.write(user.getUserID() + "," + dayplan);
            System.out.println("User saved successfully!");
        }

        // "Catch" block to accompany "Try" block
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    /**
     *
     */
    public String dayplanToString(Dayplan dayplan){
        //String stringDayplan =


        //return user.getUserID() + stringDayplan;
        return "hELLO";
    }



}
