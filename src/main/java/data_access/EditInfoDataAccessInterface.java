package data_access;

import entity.User;

import java.util.ArrayList;

public interface EditInfoDataAccessInterface {
    String editUsername(String username);
    void editPasswordOrLocation(String password, ArrayList<Double> location);
}
