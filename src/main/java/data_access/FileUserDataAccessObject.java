package data_access;

import entity.User;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// A DAO object that writes to a Microsoft Excel .csv file.
// Records new Users and existing day lists associated with their account into the file.
public class FileUserDataAccessObject implements UserSignupDataAccessInterface {

    private File csvFile;
    private String csvPath;
    private User user;

    // csvFile attribute has no individual setter
    public File getcsvFile(){
        return this.csvFile;
    }

    // Method is a setter for both csvPath and csvFile
    public void setcsvPathAndcsvFile(String csvPath){
        this.csvPath = csvPath;
        this.csvFile = new File(csvPath);
    }

    public String getcsvPath(){
        return this.csvPath;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    /**
     * Check to see if a user
     * @param identifier a User object's userID.
     * @return  boolean that is false if user doesn't exist in DB.
     */
    @Override
    public boolean existsByName(String identifier) {
        // Creating a loop variable
        boolean exists = false;
        // Create variable used to track where to split values in single line
        String value = ",";

        // "Try" block is necessary for BufferedReader objects
        try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
            String line = br.readLine();
            // Mutate line to refer to 2nd row... where actual values begin(skipping past row names)
            line = br.readLine();
            // While loop that keeps reading file until it's empty
            while (line != null) {
                // Create an array of Strings that stores each value separated by comma as a new object in array
                assert line != null;
                String[] row = line.split(value);
                // Early return if the userID we put in is equal to the userID in the row
                if (identifier.equals(row[0])){
                    System.out.println(true);
                    // Need to close the BufferedReader object
                    // Normally, the "Try" block will do this for you, but not in case of early return
                    br.close();
                    return true;
                }
                line = br.readLine();
            }
        }
        // "Catch" block is necessary with any try block
        catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println(exists);
        return exists;
    }

    /**
     * Save a user into the csv file UserDB that acts as a database.
     * Each attribute of the User class is saved into a column in the csv file,
     * Starting with userID.
     * @param user a User object that contains info like id, username, etc.
     */
    @Override
    public void saveUser(User user) {


    }
}
