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
        ArrayList<Float> location = new ArrayList<Float>(2);
        Float longitude = 3.145f;
        location.add(longitude);


        User testUser = new User(123456, "Devansh", location, "sad", 3, 2);
        fileUserDAO.saveUser(testUser);
        }
    }


