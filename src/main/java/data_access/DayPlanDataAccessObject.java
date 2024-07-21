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

    public void saveDayPlan(User user, Dayplan dayplan){
        // Create variable used to track where to split values in single line
        String value = ",";
        // Access user.userID attribute
        String identifier = String.valueOf(user.getUserID());

        // "Try" block is necessary for BufferedReader objects
        try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
            String line = br.readLine();
            // Mutate line to refer to 2nd row... where actual values begin(skipping past row names)
            line = br.readLine();
            // Create an array of Strings that stores each value separated by comma as a new object in array
            String[] row = line.split(value);
            // While loop that keeps reading file until it's empty
            while (!row[0].equals(identifier)) {
                // Keep iterating through rows until we get to the row where our user is stored
                line = br.readLine();
                row = line.split(value);
                }
            // Currently, variable line holds the info of the user we need

            }

        // "Catch" block is necessary with any try block
        catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println(false);
        return false;
    }
    }



}
