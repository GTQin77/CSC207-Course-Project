package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    private ArrayList<Double> location;
    private ArrayList<Dayplan> dayplans;


    @BeforeEach
    void setUp() {
        location = new ArrayList<>(Arrays.asList(43.6532, 79.3832));
        dayplans = new ArrayList<>();
        user = new User("testName", "testPassword", location);
        user.setDayPlans(dayplans);
    }

    @Test
    void getUserName() {
        assertEquals("testName", user.getUserName());
    }

    @Test
    void setUserName() {
        user.setUserName("testingName");
        assertEquals("testingName", user.getUserName());
    }

    @Test
    void getLocation() {
        assertEquals(location, user.getLocation());
    }

    @Test
    void setLocation() {
        ArrayList<Double> newLocation = new ArrayList<>(Arrays.asList(22.3344, 33.2211));
        user.setLocation(newLocation);
        assertEquals(newLocation, user.getLocation());
    }

    @Test
    void getDayPlans() {
        assertEquals(dayplans, user.getDayPlans());
    }

    @Test
    void setDayPlans() {
        ArrayList<Dayplan> newDayplans = new ArrayList<>();
        user.setDayPlans(newDayplans);
        assertEquals(newDayplans, user.getDayPlans());
    }
}