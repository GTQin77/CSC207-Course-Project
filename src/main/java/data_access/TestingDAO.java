package data_access;


import java.io.File;
import java.util.Objects;

public class TestingDAO {
    public static void main(String[] args) {
        FileUserDataAccessObject fileUserDAO = new FileUserDataAccessObject();
        fileUserDAO.setcsvPathAndcsvFile("./src/main/resources/UserDatabase.csv");
        fileUserDAO.existsByName("whoareyou");
        }
    }


