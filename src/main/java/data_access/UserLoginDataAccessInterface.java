package data_access;

import entity.User;

public interface UserLoginDataAccessInterface {
    User checkPassword(String username, String password);
}
