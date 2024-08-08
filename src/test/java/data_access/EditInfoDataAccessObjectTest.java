package data_access;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import entity.User;
import java.nio.file.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EditInfoDataAccessObjectTest {

    private static EditInfoDataAccessObject editDAO;
    private static UserSignupDataAccessObject signupDAO;

    @BeforeAll
    static void setUp() {
        editDAO = new EditInfoDataAccessObject();
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");
        try (FileWriter fw = new FileWriter(editDAO.getcsvFile(), true)) {
            // Write a mock user to the userDB
            fw.write("\nmartha;1.23,4.56;Silly, Goofy, Nasty;Business1, Location1, Distance1, Phone1, Price1, Rating1~Business2, Location2, Distance2, Phone2, Price2, Rating2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        try (FileWriter fw = new FileWriter(editDAO.getcsvFile(), true)) {
            // Write a mock user to the userDB
            fw.write("\nmartha;caldwell;1.23,4.56");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        signupDAO = new UserSignupDataAccessObject();
    }


    @AfterAll
    static void tearDown() {
        try {
            File tempUserDB = new File("src/test/test_resources/tempTestUserDB.csv");
            tempUserDB.delete();
            File tempDayplanDB = new File("src/test/test_resources/tempTestDayplanDB.csv");
            tempDayplanDB.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        File tempFile = new File("src/test/test_resources/tempDB.csv");
//        try (FileWriter fw = new FileWriter(tempFile, true)){
//            fw.write("userName,password,location\n");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            File oldFile = new File("src/test/test_resources/test_userDatabase.csv");
//            oldFile.delete();
//            tempFile.renameTo(oldFile);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }



    @Test
    void processLocation(){
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(1.23);
        expected.add(4.56);
        assertEquals(expected, editDAO.processLocation("1.23, 4.56"));
        assertEquals(expected, editDAO.processLocation("1.23,4.56"));
    }

    @Test
    void setCurrUserandChanges(){
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        editDAO.setCurrUserAndChanges(testUser, "madeline", "chocolate", "3.14, 1.59");
        assertEquals(testUser, editDAO.getCurrUser());
        assertTrue(editDAO.getUsernameChanged());
        assertTrue(editDAO.getPasswordChanged());
        assertTrue(editDAO.getLocationChanged());
    }

    @Test
    void setcsvPathAndcsvFile(){
        String expectedPathOther = "src/test/test_resources/test_userDatabase.csv";
        String expectedPathWindows = "src\\test\\test_resources\\test_userDatabase.csv";
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        assertEquals("src/test/test_resources/test_userDatabase.csv", editDAO.getcsvPath());
        assert editDAO.getcsvFile() != null;
        assertTrue((editDAO.getcsvFile().getPath().equals(expectedPathOther)) || editDAO.getcsvFile().getPath().equals(expectedPathWindows));
    }

    @Test
    void rewriteRow(){
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        editDAO.setCurrUserAndChanges(testUser, "madeline", "cookie", "3.14,1.59");
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        String[] row = {"martha", "caldwell", "1.23,4.59"};
        String expected = "madeline;cookie;3.14,1.59";
        assertEquals(expected, editDAO.rewriteRow("madeline", "cookie", "3.14, 1.59", row));
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");
        String[] row2 = {"kevin", "insert dayplan details here"};
        expected = "craig;3.14,1.59";
        assertEquals(expected, editDAO.rewriteRow("craig", "cookie", "3.14, 1.59", row2));
    }


    @Test
    void updateUserDatabase() {
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        File userDB = editDAO.getcsvFile();

        // CREATING USER
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        //////////

        // CREATING TEMP DB
        File tempFile = new File("src/test/test_resources/tempTestUserDB.csv");

        editDAO.setCurrUserAndChanges(testUser, "kevin", "bacon", "3.14,1.59");
        editDAO.updateDatabase("kevin", "bacon", "3.14,1.59", tempFile);

        try (BufferedReader br = new BufferedReader(new FileReader(tempFile))) {
            String line = br.readLine();
            String lastLine = "";
            line = br.readLine();
            while (line != null) {
                lastLine = line;
                System.out.println(lastLine);
                line = br.readLine();
            }
        assertEquals("kevin;bacon;3.14,1.59", lastLine);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


    @Test
    void updateDayplanDatabase() {
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");
        File userDB = editDAO.getcsvFile();

        // CREATING USER
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        //////////

        // CREATING TEMP DB
        File tempFile = new File("src/test/test_resources/tempTestDayplanDB.csv");

        editDAO.setCurrUserAndChanges(testUser, "kevin", "bacon", "3.14,1.59");
        editDAO.updateDatabase("kevin", "bacon", "3.14,1.59", tempFile);

        try (BufferedReader br = new BufferedReader(new FileReader(tempFile))) {
            String line = br.readLine();
            String lastLine = "";
            line = br.readLine();
            while (line != null) {
                lastLine = line;
                System.out.println(lastLine);
                line = br.readLine();
            }
            assertEquals("kevin;3.14,1.59;Silly, Goofy, Nasty;Business1, Location1, Distance1, Phone1, " +
                    "Price1, Rating1~Business2, Location2, Distance2, Phone2, Price2, Rating2", lastLine);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    void HandleFile(){
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");

        // CREATING USER
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        //////////
        editDAO.setCurrUserAndChanges(testUser, "lola", "bing", "1.00,1.00");
        editDAO.HandleFile("lola", "bing", "1.00,1.00");

    }

    @Test
    void editUsername(){
        // Should take every instance of KEVIN within both test_database and test_userDatabase.
        // Should change every KEVIN to JESS.
        // No temporary files other than the 2 in prev. test cases should be created.
        //
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        signupDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");

        // CREATING USER
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("kevin", "caldwell", location);
        //////////


        editDAO.setCurrUserAndChanges(testUser, "jess", "smith", "3.33,3.33");
        editDAO.editUsername("jess", "smith", "3.33,3.33", "src/test/test_resources/test_userDatabase.csv", "src/test/test_resources/test_database.csv");
        assertFalse(editDAO.editUsername("jess", "smith", "3.33,3.33", "src/test/test_resources/test_userDatabase.csv", "src/test/test_resources/test_database.csv"));
    }

    @Test
    void editPasswordOrLocation(){
        // Should replace JESS's rows in userDB and dayplanDB with coco, and 9.99.
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        signupDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");

        // CREATING USER
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(3.33);
        location.add(3.33);
        User testUser = new User("jess", "smith", location);
        //////////

        editDAO.setCurrUserAndChanges(testUser, "jess", "coco", "9.99,9.99");
        editDAO.editPasswordOrLocation("coco", "9.99,9.99", "src/test/test_resources/test_userDatabase.csv", "src/test/test_resources/test_database.csv");
    }


}
