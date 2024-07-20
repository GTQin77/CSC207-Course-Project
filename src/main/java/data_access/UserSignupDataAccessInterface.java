package data_access;

import entity.User;

public interface UserSignupDataAccessInterface {

    boolean userExists(User user);
    void saveUser(User user);
}