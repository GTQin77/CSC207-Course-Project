package data_access;

import entity.Business;
import entity.User;
import entity.Dayplan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;


public class DayPlanDataAccessObjectTest {

    private DayPlanDataAccessObject dayPlanDAO;
    private Dayplan dayplan;
    private User user;

    @BeforeEach
    void setUp() {
        dayPlanDAO = new DayPlanDataAccessObject();
        dayPlanDAO.setcsvPathAndcsvFile("src/test/test_resources/test_Database.csv");

        ArrayList<Double> location = new ArrayList<>();
        location.add(22.3344);
        location.add(-22.3344);
        user = new User("testUser", "123", location);

        ArrayList<Business> plan = new ArrayList<>();
        plan.add(new Business("test business one", location, 11.0, "223-334-5566", "$", 3.9f, "meal"));
        plan.add(new Business("test business two", location, 13.0, "443-334-5566", "$$$", 4.7f, "activity"));

        ArrayList<String> businessIDs = new ArrayList<>();
        businessIDs.add("123");
        businessIDs.add("456");

        dayplan = new Dayplan();
        dayplan.setBusinessIDs(businessIDs);
        dayplan.setUser(user);
        dayplan.setPlan(plan);
        dayplan.setLocation(location);
        dayplan.setDescription("A happy day");
        dayplan.setnumActivities(1);
        dayplan.setNumMeals(1);
        dayplan.setVibe("Active, Inspiring, Motivational");
        dayplan.setCity("Toronto");
    }

    @AfterAll
    static void tearDown() throws IOException {
        File tempFile = new File("src/test/test_resources/tempDB.csv");
        try (FileWriter fw = new FileWriter(tempFile, true)){
            fw.write("userName,location,vibe,Dayplan\n"); // Example header for dayplan database
        }
        try {
            File oldFile = new File("src/test/test_resources/test_Database.csv");
            oldFile.delete();
            tempFile.renameTo(oldFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAndSetUser() {
        dayPlanDAO.setUser(user);
        assertEquals(user, dayPlanDAO.getUser());
    }

    @Test
    void setcsvPathAndcsvFile() {
        assertEquals("src/test/test_resources/test_Database.csv", dayPlanDAO.getcsvPath());
        assertNotNull(dayPlanDAO.getcsvFile());
        assertTrue(dayPlanDAO.getcsvFile().getPath().contains("test_Database.csv"));
    }

    @Test
    void saveDayPlan() throws IOException {
        dayPlanDAO.saveDayPlan(dayplan);
        System.out.println("Dayplan saved successfully!");
    }

    @Test
    void saveDayPlanException() {
        File nonWritableFile = new File("src/test/test_resources/non_writable.csv");
        try {
            nonWritableFile.createNewFile();
            nonWritableFile.setWritable(false);

            dayPlanDAO.setcsvPathAndcsvFile(nonWritableFile.getPath());

            assertThrows(RuntimeException.class, () -> dayPlanDAO.saveDayPlan(dayplan),
                    "Expected saveDayPlan to throw RuntimeException due to IO issues, but it did not");
        } catch (IOException e) {
            fail("Setup failed: could not create or modify file properties.");
        } finally {
            nonWritableFile.setWritable(true);
            nonWritableFile.delete();
        }
    }


    @Test
    void businessToString() {
        Business testBusiness = new Business("Cafe Delight", new ArrayList<>(Arrays.asList(10.123, 20.456)), 1.5, "123456789", "$$$", 4.5f, "activity");
        String expectedString = "Cafe Delight,10.123,20.456,1.5,123456789,$$$,4.5";
        assertEquals(expectedString, dayPlanDAO.businessToString(testBusiness));
    }
}
