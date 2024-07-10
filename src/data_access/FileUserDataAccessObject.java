package data_access;

import entity.User;

import java.io.*;

// A DAO object that writes to a Microsoft Excel .csv file.
// Records new Users and existing day lists associated with their account into the file.
public class FileUserDataAccessObject implements UserSignupDataAccessInterface{

    private final File csvFile;

    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(User user) {

    }
}
