package data_access;

import entity.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * The {@code UserLoginDataAccessObject} class is responsible for handling
 * data access related to user login information. It implements the
 * {@link UserLoginDataAccessInterface}, providing methods to manage the
 * file and path information where the user data is stored, as well as
 * retrieving the user information.
 */

public class UserLoginDataAccessObject implements UserLoginDataAccessInterface {
    private String csvPath;
    private File csvFile;
    private User user;

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

    public User getUser() {
        return this.user;
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
    public boolean findUser(String username, String password) {
        String value = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(this.getcsvFile()))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] row = line.split(value);
                if (username.equals(row[0])){
                    if (password.equals(row[1])){
                        this.user = new User(row[0], row[1], parseLocation(row[2]));
                        br.close();
                        return true;
                    } else {
                        return false;
                    }
                }
                line = br.readLine();
            }
            System.out.println("User not found");
        }

        catch (IOException e){
            throw new RuntimeException(e);
        }
        return false;
    }
    @Override
    public User getUser(String username, String password) {
        return this.user;
    }

    /**
     * Helper method to parse the location string into an ArrayList of Doubles.
     * @param locationStr the location string.
     * @return the parsed location as an ArrayList of Doubles.
     */
    public ArrayList<Double> parseLocation(String locationStr) {
        ArrayList<Double> location = new ArrayList<>();
        String[] values = locationStr.replace("\"", "").split(",");
        for (String value : values) {
            location.add(Double.parseDouble(value.trim()));
        }
        return location;
    }


}


