package data_access;
import entity.User;
import entity.UserFactory;
import java.util.ArrayList;

import java.io.File;
import java.util.Objects;

public class TestingDAO {
    public static void main(String[] args) {
        FileUserDataAccessObject fileUserDAO = new FileUserDataAccessObject();
        fileUserDAO.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        ArrayList<Double> location = new ArrayList<Double>(3);
        location.add(3.145);
        location.add(5.413);
        location.add(7.8999);


        User testUser = new User(123456, "D-man", location, "sad", 3, 2);
        fileUserDAO.userExists(testUser);
        fileUserDAO.saveUser(testUser);
        fileUserDAO.userExists(testUser);
        }
    }


