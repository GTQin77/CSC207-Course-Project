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

    public void setcsvFile(File csvFile) {
        this.csvFile = csvFile;
    }

    public File getcsvFile(){
        return csvFile;
    }

    public void setcsvPath(String csvPath){
        this.csvPath = csvPath;
    }

    public String getcsvPath(){
        return csvPath;
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
