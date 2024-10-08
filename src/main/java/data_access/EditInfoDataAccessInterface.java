package data_access;

import entity.User;

import java.util.ArrayList;

public interface EditInfoDataAccessInterface {
    boolean editUsername(String newUsername, String newPassword, String newLocation, String userPath, String dayplanPath);
    void editPasswordOrLocation(String newPassword, String newLocation, String userPath, String dayplanPath);
    void setCurrUserAndChanges(User user, String newUsername, String newPassword, String newLocation);

}
