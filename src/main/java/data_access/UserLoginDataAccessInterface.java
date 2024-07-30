package data_access;

import entity.User;

public interface UserLoginDataAccessInterface {
    User findUserByUsernameAndPassword(String username, String password);
}
