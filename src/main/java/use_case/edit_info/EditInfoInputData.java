package use_case.edit_info;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class EditInfoInputData {

    private final String userName;
    private final String password;
    private final String repeatPassword;
    private final String location;
    private final User user;

    public EditInfoInputData(String userName, String password, String repeatPassword,
                             String location, User user) {
        this.userName = userName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.location = removeLocationSpaces(location);
        this.user = user;
    }

    public String getUserName() {return this.userName;}

    public String getPassword() {return this.password;}

    public String getRepeatPassword() {return this.repeatPassword;}

    public String getLocation() {
        return this.location;
    }

    public User getUser() {
        return this.user;
    }

    /**
     * A method that, given a String of a list of coordinates separated by commas, will remove any spaces.
     * Necessary for sake of writing to database.
     * @param location String form of a location. May contain spaces, or none.
     * @return a stirng that is the same as location, but with spaces removed.
     */
    public String removeLocationSpaces(String location){
        while (location.contains(" ")){
            int index = location.indexOf(" ");
            location = location.substring(0, index) + location.substring(index+1);
        }
        return location;
    }
}
