package data_access;

import entity.User;

public class UserLoginDataAccessObject implements UserLoginDataAccessInterface{
    private String csvPath;

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}

