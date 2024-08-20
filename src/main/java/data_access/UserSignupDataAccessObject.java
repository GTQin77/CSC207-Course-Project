package data_access;

import entity.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserSignupDataAccessObject implements UserSignupDataAccessInterface {

    private File csvFile;
    private String csvPath;
    private User user;

    public File getcsvFile(){return this.csvFile;}

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
     * @param username linked to a user account to be checked for existence.
     * @return  boolean that is true if user is already in DB.
     */
    @Override
    public boolean userExists(String username) {

        String value = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
            String line = br.readLine();
            while (line != null) {
                String[] row = line.split(value);
                if (username.equals(row[0])){
                    br.close();
                    return true;
                }
                line = br.readLine();
            }
        }

        catch (IOException e){
            throw new RuntimeException(e);
        }
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
        try (FileWriter fw = new FileWriter(this.getcsvFile(), true)) {
            fw.write("\n");
            fw.write(this.userToString(user));
            System.out.println("User saved successfully!");
            }

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
        String stringUser = user.getUserName() + ";" + user.getPassword() + ";";

        String location = listToString(user.getLocation());

        return stringUser + location;
    }


    /**
     * Helper method taking in an ArrayList<Double> location and converts it to a String.
     * Used in userToString to prep a User's location for writing to the DAO.
     */
    public static String listToString(ArrayList<Double> location){
        return location.getFirst() + "," + location.getLast();
    }




}
