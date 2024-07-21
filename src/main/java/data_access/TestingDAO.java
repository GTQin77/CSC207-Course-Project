package data_access;
import entity.User;
import entity.UserFactory;
import java.util.ArrayList;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class TestingDAO {
    public static void main(String[] args) {
        UserSignupDataAccessObject fileUserDAO = new UserSignupDataAccessObject();
        fileUserDAO.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        ArrayList<Double> location = new ArrayList<Double>(Arrays.asList(3.145, 5.413, 7.8999));
        System.out.println(location);


        User testUser = new User(123456, "D-man", location, "sad", 3, 2);
        System.out.println(testUser.getLocation());

        fileUserDAO.userExists(testUser);
        fileUserDAO.saveUser(testUser);
        fileUserDAO.userExists(testUser);
        }
    }


