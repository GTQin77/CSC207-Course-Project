package data_access;

import entity.User;
import data_access.UserSignupDataAccessInterface;

import java.nio.file.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EditInfoDataAccessObject implements EditInfoDataAccessInterface{
    private File csvFile;
    private String csvPath;
    private User user;

    public File getcsvFile(){return this.csvFile;}

    public void setcsvPathAndcsvFile(String csvPath){this.csvPath = csvPath; this.csvFile = new File(csvPath);}

    public String getcsvPath(){return this.csvPath;}

    public void setUser(User user){this.user = user;}

    public User getUser(){return this.user;}

    /**
     * Method
     * @param username stores User's username, may be changed.
     */
    @Override
    public String editUsername(String username) {
        UserSignupDataAccessInterface userSignupDataAccessInterface = new UserSignupDataAccessObject();
        this.getUser().setUserName(username);
        boolean userPreExists = userSignupDataAccessInterface.userExists(user);
        // CASE 1: New username already exists, we do not change data
        if (userPreExists){
            return "Username already exists. Please try again.";
        // CASE 2: New username is valid, change data
        } else{
            // Change username file using updateDatabase
            // Update Dayplan DB using updateDatabase
        }
    }

    /**
     * Method
     * @param password stores User's password, may be changed.
     * @param location stores User's location, may be changed.
     */
    @Override
    public void editPasswordOrLocation(String password, ArrayList<Double> location) {

    }

    /**
     * Method
     * @param username
     */
    // @Override
    public void HandleFile(String username) {
        try {
            // Create new temporary database file
            File tempFile = new File("./src/main/resources/TempDatabase.csv");
            // If successfully created...
            if (tempFile.createNewFile()) {
                System.out.println("File created: " + tempFile.getName());
                // For every line in old database, write to new database...
                // UNLESS row[0] is username.
                // In that case, edit info THEN write to new database.
                this.updateDatabase(username, tempFile);

                // Once done writing to temp file, delete old file...
                // this.DeleteIfExists
                // BUT don't use this for now, since Path.resolveSibling should work?

                // Rename temp file to old file name
                try {
                    Path tempPath = Paths.get("./src/main/resources/TempDatabase.csv");
                    Files.move(tempPath, tempPath.resolveSibling(this.getcsvFile().getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean DeleteIfExists(){
        try {
            boolean isDeleted = Files.deleteIfExists(Paths.get(this.getcsvPath()));

            if (isDeleted) {
                System.out.println("File is successfully deleted!");
            } else {
                System.out.println("Sorry, the file was not deleted.");
            }
            return isDeleted;
        }
        catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty!");
        }
        catch (IOException e) {
            System.out.println("I/O error occurred");
        }
        catch (SecurityException e) {
            System.out.println("Delete access denied!");
        }
        return false;
    }

    public void updateDatabase(String username, File tempFile){
        // For every line in old database, write to new database...
        // UNLESS row[0] is username.
        // In that case, edit info THEN write to new database.
        try (FileWriter fw = new FileWriter(tempFile, true)) {
            // Writing header & switching to next line
            // Case 1: If csvpath is to UserDB:
                // Write header as username, password, etc.
            // Case 2: If csvpath is to DayplanDB:
                // Write header as username, location, etc.
                // Switching to new line
                fw.write("\n");
                // Open BufferedReader, read each line, write it to fw
                    // If row[0] == username:
                    // Only write AFTER altering info.
                // When line is null, close BufferedReader
            // Close FileReader

        }
        catch (IOException e){
            throw new RuntimeException(e);
        }




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




    }


}
