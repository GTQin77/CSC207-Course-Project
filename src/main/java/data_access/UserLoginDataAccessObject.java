package data_access;

import entity.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;


public class UserLoginDataAccessObject implements UserLoginDataAccessInterface {
    private String csvPath;
    private File csvFile;

    public File getcsvFile() {
        return this.csvFile;
    }

    public String getcsvPath() {
        return this.csvPath;
    }

    public void setcsvFileandPath(String csvPath) {
        this.csvPath = csvPath;
        this.csvFile = new File(csvPath);
    }

    /**
     * Method that checks if a user exists in the UserDatabase csv file based on username and password.
     * Each attribute of the User class is saved into a column in the csv file, starting with userID.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return User object if user is found in UserDataBase.
     */
    @Override
    public User findUser(String username, String password) {
        String value = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(this.getCsvFile()))) {
            String line = br.readLine();


    }
}

