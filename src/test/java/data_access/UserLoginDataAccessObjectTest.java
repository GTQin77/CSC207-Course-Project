package data_access;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class UserLoginDataAccessObjectTest {

    private UserLoginDataAccessObject loginDAO;
    private UserSignupDataAccessObject signupDAO;

    @BeforeEach
    void setUp() {
        loginDAO = new UserLoginDataAccessObject();
        loginDAO.setcsvFileandPath("src/test/test_resources/test_userDatabase.csv");
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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void setcsvFileandPath() {
        String expectedPathOther = "src/test/test_resources/test_userDatabase.csv";
        String expectedPathWindows = "src\\test\\test_resources\\test_userDatabase.csv";
        assertEquals("src/test/test_resources/test_userDatabase.csv", loginDAO.getcsvPath());
        assert loginDAO.getcsvFile() != null;
        assertTrue((loginDAO.getcsvFile().getPath().equals(expectedPathOther)) || loginDAO.getcsvFile().getPath().equals(expectedPathWindows));
    }

    @Test
    void parseLocation(){
        String location = "1.23, 4.56";
        ArrayList<Double> expected = new ArrayList<Double>();
        expected.add(1.23);
        expected.add(4.56);
        assertEquals(expected, loginDAO.parseLocation(location));
    }


    @Test
    void findUser() throws UnsupportedEncodingException {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        signupDAO.saveUser(testUser);
        assertTrue(loginDAO.findUser("martha", "caldwell"));
        assertFalse(loginDAO.findUser("martha", "hello"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "User not found" + System.lineSeparator();
        loginDAO.findUser("damejudy", "dench");
        String actualOutput = outContent.toString("UTF-8");
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void getUser() {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);
        signupDAO.saveUser(testUser);
        loginDAO.findUser("martha", "caldwell");
        assertNotNull(loginDAO.getUser("martha", "caldwell"));
    }

    @Test
    void testIOExceptionInFindUser() {
        loginDAO.setcsvFileandPath("non_existent_directory/non_existent_file.csv");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            loginDAO.findUser("username", "password");
        });

        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("java.io.FileNotFoundException", exception.getCause().getClass().getName());
    }

}
