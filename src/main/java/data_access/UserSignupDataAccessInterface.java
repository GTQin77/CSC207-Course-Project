package data_access;

import entity.User;

public interface UserSignupDataAccessInterface {

    boolean userExists(String username);
    void saveUser(User user);
}