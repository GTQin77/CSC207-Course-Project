package data_access;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.doThrow;

public class UserSignUpDataAccessObjectTest {

    private UserSignupDataAccessObject signupDAO;

    @BeforeEach
    void setUp() {
        signupDAO = new UserSignupDataAccessObject();
        signupDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
    }

    @AfterAll
    static void tearDown() throws IOException {
        File tempFile = new File("src/test/test_resources/tempDB.csv");
        try (FileWriter fw = new FileWriter(tempFile, true)){
            fw.write("userName,password,location\n");
        }
        try {
            File oldFile = new File("src/test/test_resources/test_userDatabase.csv");
            oldFile.delete();
            tempFile.renameTo(oldFile);

            // Files.move(tempPath, tempPath.resolveSibling("src/test/test_resources/test_userDatabase.csv"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testGetAndSetUser() {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        signupDAO.setUser(testUser);
        assertEquals(testUser, signupDAO.getUser());
    }

    @Test
    void setcsvPathAndcsvFile() {
        String expectedPathOther = "src/test/test_resources/test_userDatabase.csv";
        String expectedPathWindows = "src\\test\\test_resources\\test_userDatabase.csv";
        assertEquals("src/test/test_resources/test_userDatabase.csv", signupDAO.getcsvPath());
        assert signupDAO.getcsvFile() != null;
        assertTrue((signupDAO.getcsvFile().getPath().equals(expectedPathOther)) || signupDAO.getcsvFile().getPath().equals(expectedPathWindows));
    }

    @Test
    void listToString() {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        String expected = "1.23,4.56";
        String actual = UserSignupDataAccessObject.listToString(location);
        assertEquals(expected, actual);
    }

    @Test
    void userToString() {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        String expected = "martha;caldwell;1.23,4.56";
        String actual = signupDAO.userToString(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void saveUser() {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        signupDAO.saveUser(testUser);
        String expected = "martha;caldwell;1.23,4.56";
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(new File("src/test/test_resources/test_userDatabase.csv")))) {
            String line;
            line = br.readLine();
            while (line != null) {
                lastLine = line;
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, lastLine);
    }


    @Test
    void userExists(){
        assertTrue(signupDAO.userExists("martha"));
        assertTrue(!signupDAO.userExists("benjamin"));
    }

    @Test
    void testThrows() throws IOException {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        signupDAO.setcsvPathAndcsvFile("/no/such/place");
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> signupDAO.userExists("denise"));
        RuntimeException thrown2 = assertThrows(RuntimeException.class, () -> signupDAO.saveUser(testUser));
        assertTrue(thrown.getMessage().contains(thrown.getMessage()));
        assertTrue(thrown2.getMessage().contains(thrown2.getMessage()));
    }


}
