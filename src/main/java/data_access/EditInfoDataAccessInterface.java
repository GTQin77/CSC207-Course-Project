package data_access;

import entity.User;

import java.util.ArrayList;

public interface EditInfoDataAccessInterface {
    void editUsername(String username);
    void editPasswordOrLocation(String password, ArrayList<Double> location);
}
