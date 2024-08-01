package data_access;

import entity.User;

public interface UserLoginDataAccessInterface {
    User findUser(String username, String password);
}
