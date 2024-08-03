package data_access;

import entity.User;

public interface UserLoginDataAccessInterface {
    boolean findUser(String username, String password);
}
