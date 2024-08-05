package data_access;

import entity.User;
import data_access.UserSignupDataAccessInterface;

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
    public void editUsername(String username) {
        UserSignupDataAccessInterface userSignupDataAccessInterface = new UserSignupDataAccessObject();
        boolean userPreExists = userSignupDataAccessInterface.userExists(user);
        // if(userPreExists){}
    }

    /**
     * Method
     * @param password stores User's password, may be changed.
     * @param location stores User's location, may be changed.
     */
    @Override
    public void editPasswordOrLocation(String password, ArrayList<Double> location) {

    }
}
