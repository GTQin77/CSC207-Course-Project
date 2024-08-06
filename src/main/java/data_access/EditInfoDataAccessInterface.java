package data_access;

import entity.User;

import java.util.ArrayList;

public interface EditInfoDataAccessInterface {
    String editUsername(String newUsername, String newPassword, String newLocation);
    String editPasswordOrLocation(String newPassword, String newLocation);

}
