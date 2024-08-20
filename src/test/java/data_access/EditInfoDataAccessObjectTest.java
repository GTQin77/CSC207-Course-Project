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


    @AfterEach
    void tearDown() {
        try {
            File tempUserDB = new File("src/test/test_resources/tempTestUserDB.csv");
            tempUserDB.delete();
            File tempDayplanDB = new File("src/test/test_resources/tempTestDayplanDB.csv");
            tempDayplanDB.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        // Writing to UserDB case
        // Change ALL attributes...
        editDAO.setCurrUserAndChanges(testUser, "madeline", "cookie", "3.14,1.59");
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        String[] row1 = {"martha", "caldwell", "1.23,4.59"};
        String expected = "madeline;cookie;3.14,1.59";
        assertEquals(expected, editDAO.rewriteRow("madeline", "cookie", "3.14, 1.59", row1));

        // Writing to DayplanDB case
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");
        String[] row2 = {"martha", "insert dayplan details here"};
        editDAO.setCurrUserAndChanges(testUser, "martha", "cookie", "3.14,1.59");
        // Not changing username case
        expected = "martha;3.14,1.59";
        assertEquals(expected, editDAO.rewriteRow("martha", "cookie", "3.14, 1.59", row2));
        // Changing username case
        String[] row3 = {"kevin", "insert dayplan details here"};
        expected = "kevin;5.55,1.59";
        editDAO.setCurrUserAndChanges(testUser, "kevin", "cookie", "5.55,1.59");
        assertEquals(expected, editDAO.rewriteRow("kevin", "cookie", "5.55, 1.59", row3));


    }


    @Test
    void editUsername(){
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/helloUserDatabase.csv");
        signupDAO.setcsvPathAndcsvFile("src/test/test_resources/helloUserDatabase.csv");


        try (FileWriter fw = new FileWriter(editDAO.getcsvFile(), true)) {
            // Write a mock user to the userDB
            fw.write("martha;caldwell;1.23,4.56");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/helloDayplan.csv");
        try (FileWriter fw = new FileWriter(editDAO.getcsvFile(), true)) {
            fw.write("martha;1.23,4.56;Silly, Goofy, Nasty;Business1, Location1, Distance1, Phone1, Price1, Rating1~Business2, Location2, Distance2, Phone2, Price2, Rating2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // CREATING USER
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        signupDAO.setUser(testUser);
        //////////
        // TESTING TRUE CASE
        editDAO.setCurrUserAndChanges(testUser, "dying", "smith", "3.33,3.33");
        assertTrue(editDAO.editUsername("dying", "smith", "3.33,3.33", "src/test/test_resources/helloUserDatabase.csv", "src/test/test_resources/helloDayplan.csv"));

        // TESTING FALSE CASE
        editDAO.setCurrUserAndChanges(testUser, "newuser", "smith", "3.33,3.33");

//        assertFalse(editDAO.editUsername("martha", "smith", "3.33,3.33", "src/test/test_resources/helloUserDatabase.csv", "src/test/test_resources/helloDayplan.csv"));



        try {
            File tempUserDB = new File("src/test/test_resources/helloUserDatabase.csv");
            tempUserDB.delete();
            File tempDayplanDB = new File("src/test/test_resources/helloDayplan.csv");
            tempDayplanDB.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

        signupDAO.saveUser(testUser);
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
    void editPasswordOrLocation(){
        // Should replace JESS's rows in userDB and dayplanDB with coco, and 9.99.
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
        signupDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");

        // CREATING USER
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("jess", "caldwell", location);
        //////////

        editDAO.setCurrUserAndChanges(testUser, "jess", "coco", "9.99,9.99");
        editDAO.editPasswordOrLocation("coco", "9.99,9.99", "src/test/test_resources/test_userDatabase.csv", "src/test/test_resources/test_database.csv");
    }


}
