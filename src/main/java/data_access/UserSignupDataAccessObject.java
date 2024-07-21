package data_access;

import entity.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

// A DAO object that writes to a Microsoft Excel .csv file.
// Records new Users and existing day lists associated with their account into the file.
public class UserSignupDataAccessObject implements UserSignupDataAccessInterface {

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
     * Method that checks if a user already exists in the UserDatabase csv file.
     * Each attribute of the User class is saved into a column in the csv file, starting with userID.
     * @param user a User object meant to be checked for existence.
     * @return  boolean that is true if user is already in DB.
     */
    @Override
    public boolean userExists(User user) {
        // Create variable used to track where to split values in single line
        String value = ",";
        // Access user.userID attribute
        String identifier = user.getUserName();

        // "Try" block is necessary for BufferedReader objects
        try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
            String line = br.readLine();
            // Mutate line to refer to 2nd row... where actual values begin(skipping past row names)
            line = br.readLine();
            // While loop that keeps reading file until it's empty
            while (line != null) {
                // Create an array of Strings that stores each value separated by comma as a new object in array
                String[] row = line.split(value);
                // Early return if the userID we put in is equal to the userID in the row
                if (identifier.equals(row[0])){
                    System.out.println(true);
                    // Need to close the BufferedReader object
                    // Normally, the "Try" block will do this for you, but not in case of early return
                    br.close();
                    // Do not save the user to database, return false
                    return true;
                }
                line = br.readLine();
            }
        }

        // "Catch" block is necessary with any try block
        catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println(false);
        return false;
    }


    /**
     * Save a user into the csv file UserDB that acts as a database.
     * Each attribute of the User class is saved into a column in the csv file, starting with userID.
     * Writes the user to the first available row at the end of the DB.
     * @param user a User object that contains info like id, username, etc.
     */
    @Override
    public void saveUser(User user){
        // Same structure as existsByName, using try/catch block
        // Here, we use FileWriter class
        try (FileWriter fw = new FileWriter(this.getcsvFile(), true)) {
            fw.write("\n");
            fw.write(this.userToString(user));
            System.out.println("User saved successfully!");
            }

        // "Catch" block to accompany "Try" block
        catch (IOException e){
            throw new RuntimeException(e);
        }
        }


    /**
     * Helper method that converts a user into a String form for writing into a csv.
     * @param user a User object that contains info like id, username, etc.
     * @return String that contains all of user's info separated by commas
     */
    public String userToString(User user){
        String stringUser = user.getUserName() + "," + user.getPassword() + ",";

        String location = user.getLocation().toString();

        return stringUser + "\"" + location.substring(1, location.length() - 2) + "\"";
    }

}
