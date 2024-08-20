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
import java.util.Arrays;
import java.util.stream.Collectors;

public class EditInfoDataAccessObject implements EditInfoDataAccessInterface{
    private File csvFile;
    private String csvPath;
    private User currUser;
    private boolean usernameChanged;
    private boolean passwordChanged;
    private boolean locationChanged;


    public ArrayList<Double> processLocation(String newLocation){
        ArrayList<Double> newDoubLocation = new ArrayList<Double>();
        String[] newStrLocation = newLocation.split(",");
        newDoubLocation.add(Double.parseDouble(newStrLocation[0]));
        newDoubLocation.add(Double.parseDouble(newStrLocation[1]));
        return newDoubLocation;
    }

    public void setCurrUserAndChanges(User user, String newUsername, String newPassword, String newLocation){
        this.currUser = user;
        this.usernameChanged = !user.getUserName().equals(newUsername);
        this.passwordChanged = !user.getPassword().equals(newPassword);
        this.locationChanged = !user.getLocation().equals(processLocation(newLocation));
    }

    public File getcsvFile(){return this.csvFile;}

    public void setcsvPathAndcsvFile(String csvPath){this.csvPath = csvPath; this.csvFile = new File(csvPath);}

    public String getcsvPath(){return this.csvPath;}

    public User getCurrUser(){return this.currUser;}

    public boolean getUsernameChanged(){return this.usernameChanged;}
    public boolean getPasswordChanged(){return this.passwordChanged;}
    public boolean getLocationChanged(){return this.locationChanged;}

    /**
     * Method that controls process of editing a username and/or password and location.
     * Writes to UserDatabase and DayplanDatabase.
     * Checks to see if the new username is already taken.
     * @param newUsername stores username User wants to switch to.
     * @param newPassword optional, replace with null if password is unchanged.
     * @param newLocation optional, replace with null if location is unchanged.
     */
    @Override
    public boolean editUsername(String newUsername, String newPassword, String newLocation, String userPath, String dayplanPath) {
        UserSignupDataAccessInterface userSignupDataAccessInterface = new UserSignupDataAccessObject();

        // must assign/set the csv file
        ((UserSignupDataAccessObject) userSignupDataAccessInterface).setcsvPathAndcsvFile(userPath);

        boolean userPreExists = userSignupDataAccessInterface.userExists(newUsername);
        // CASE 1: New username already exists, we do not change data
        if (userPreExists){
            return false;
        // CASE 2: New username is valid, change data
        } else{
            // Change username file using updateDatabase
            // Update Dayplan DB using updateDatabase
            // BEFORE CALLING THIS.... csvpath MUST be set to userDB


//            this.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
//            HandleFile(newUsername, newPassword, newLocation);

            this.setcsvPathAndcsvFile(userPath);
            HandleFile(newUsername, newPassword, newLocation);



            // Change DB to be Dayplan Database
//            this.setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
//            HandleFile(newUsername, newPassword, newLocation);

            this.setcsvPathAndcsvFile(dayplanPath);
            HandleFile(newUsername, newPassword, newLocation);




            return true;
        }
    }

    /**
     * Method that controls process of editing only a User's password and/or location.
     * Writes to only UserDatabase.
     * @param newPassword optional, replace with null if password is unchanged.
     * @param newLocation optional, replace with null if location is unchanged.
     */
    @Override
    public void editPasswordOrLocation(String newPassword, String newLocation, String userPath, String dayplanPath) {
        UserSignupDataAccessInterface userSignupDataAccessInterface = new UserSignupDataAccessObject();
            // Change username file using updateDatabase
            // Update Dayplan DB using updateDatabase
            // BEFORE CALLING THIS.... csvpath MUST be set to userDB
        // this.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");

        this.setcsvPathAndcsvFile(userPath);
        HandleFile("placeholder", newPassword, newLocation);


        // COMMENT OUT IF NEEDED... NEW STUFF???
        // this.setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");

        this.setcsvPathAndcsvFile(dayplanPath);
        HandleFile("placeholder", newPassword, newLocation);


        }

    /**
     * Helper method that creates a temporary database and calls updateDatabse helper method
     * Once done updating info, it replaces the old database with the updated temporary one.
     * @param newUsername optional, replace with null if unchanged.
     * @param newPassword optional, replace with null if unchanged.
     * @param newLocation optional, replace with null if unchanged.
     */
    public void HandleFile(String newUsername, String newPassword, String newLocation) {
        try {
            // Create new temporary database file
            File tempFile = new File("./src/main/resources/TempDatabase.csv");
            // If successfully created...
            if (tempFile.createNewFile()) {
                System.out.println("File created: " + tempFile.getName());
                // For every line in old database, write to new database...
                // UNLESS row[0] is username.
                // In that case, edit info THEN write to new database.
                this.updateDatabase(newUsername, newPassword, newLocation, tempFile);

                // Rename temp file to old file name
                // Path tempPath = Paths.get("./src/main/resources/TempDatabase.csv");

                File oldFile = this.getcsvFile();
                oldFile.delete();
                tempFile.renameTo(oldFile);

                // Files.move(tempPath, tempPath.resolveSibling(this.getcsvFile().getName()));

            }
//            else {
//                System.out.println("File already exists.");
//            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Helper method that copies over existing database to a temporary one, rewriting info where necessary.
     * Calls helper method rewriteRow to change specific lines.
     * @param newUsername optional, replace with null if unchanged.
     * @param newPassword optional, replace with null if unchanged.
     * @param newLocation optional, replace with null if unchanged.
     * @param tempFile is an EMPTY new temporary csv file.
     */
    public void updateDatabase(String newUsername, String newPassword, String newLocation, File tempFile){
        // For every line in old database, write to new database...
        // UNLESS row[0] is username.
        // In that case, edit info THEN write to new database.
        String currUsername = this.getCurrUser().getUserName();
        try (FileWriter fw = new FileWriter(tempFile, true)) {
//             Writing header & switching to next line
            // Case 1: If csvpath is to UserDB:
             if (this.getcsvPath().contains("UserDatabase") || this.getcsvPath().contains("userDatabase")){
                fw.write("userName,password,location" + "\n");
            }
                // Write header as username, password, etc.
//             Case 2: If csvpath is to DayplanDB:
            else{
                fw.write("userName,location,vibe,Dayplan" + "\n");
            }
            try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
                String line = br.readLine();
                line = br.readLine();
                // While loop that keeps reading file until it's empty
                while (line != null) {
                    // Create an array of Strings that stores each value separated by comma as a new object in array
                    String[] row = line.split(";");


                    System.out.println(Arrays.toString(row));


                    // Early return if the userID we put in is equal to the userID in the row
                    if (currUsername.equals(row[0])){
                        // Rewriting the row to have updated info
                        line = rewriteRow(newUsername, newPassword, newLocation, row);

                        System.out.println(line);

                    }
                    fw.write(line + "\n");
                    line = br.readLine();
                }
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }

        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * Helper method that given a row in a DB, rewrites the row in String form with the desired updated user info.
     * Works differently depending on whether we write to UserDB or DayplanDB.
     * @param newUsername optional, replace with null if unchanged.
     * @param newPassword optional, replace with null if unchanged.
     * @param newLocation optional, replace with null if unchanged.
     * @param row a String[] from a previous database to alter.
     * @return a String representation of the updated row, ready to write to the copy of the DB.
     */
    public String rewriteRow(String newUsername, String newPassword, String newLocation, String[] row){
        if (this.getcsvPath().contains("UserDatabase") || this.getcsvPath().contains("userDatabase")){
            if (usernameChanged){
                row[0] = newUsername;
            }if (passwordChanged){
                row[1] = newPassword;
            }if (locationChanged){
                row[2] = newLocation.replaceAll("\\s", "");
            }
        }else{  // Writing to Dayplan DB
            if (usernameChanged){
                row[0] = newUsername;
                row[1] = newLocation.replaceAll("\\s", "");
            }
            else{
                row[1] = newLocation.replaceAll("\\s", "");
            }
        }
        StringBuilder newRow = new StringBuilder();
        for (int i = 0; i < row.length - 1; i++){
            newRow.append(row[i]).append(";");
        }
        return newRow + row[row.length - 1];
    }


}
