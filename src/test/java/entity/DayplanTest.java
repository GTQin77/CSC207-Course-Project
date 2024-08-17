package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class DayplanTest {
    private Dayplan dayplan;
    private User user;
    private ArrayList<Double> location;
    private ArrayList<Business> plan;
    private ArrayList<String> businessIDs;

    @BeforeEach
    void setUp() {
        dayplan = new Dayplan();
        location = new ArrayList<>();
        location.add(22.3344);
        location.add(-22.3344);
        user = new User("testUser","123", location);

        plan = new ArrayList<>();

        plan.add(new Business("test business one", location, 11.0, "223-334-5566", "$", 3.9f, "meal"));
        plan.add(new Business("test business two", location, 13.0, "443-334-5566", "$$$", 4.7f, "activity"));

        businessIDs = new ArrayList<>();
        businessIDs.add("123");
        businessIDs.add("456");

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


    @Test
    void setUser() {
        User newUser = new User("testUser111","1111", location);
        dayplan.setUser(newUser);
        assertEquals(newUser, dayplan.getUser());
    }

    @Test
    void getUser() {
        assertEquals(user,dayplan.getUser());
    }

    @Test
    void setLocation() {
        ArrayList<Double> locationTest = new ArrayList<>();
        locationTest.add(22.3344);
        locationTest.add(-22.3344);
        dayplan.setLocation(locationTest);
        assertEquals(locationTest,dayplan.getLocation());
    }

    @Test
    void getLocation() {
        assertEquals(location,dayplan.getLocation());
    }

    @Test
    void setCity() {
        String cityTest = "New York";
        dayplan.setCity(cityTest);
        assertEquals(cityTest,dayplan.getCity());
    }

    @Test
    void getCity() {
        assertEquals("Toronto",dayplan.getCity());
    }

    @Test
    void setNumMeals() {
        dayplan.setNumMeals(2);
        assertEquals(2, dayplan.getNumMeals());
    }

    @Test
    void getNumMeals() {
        assertEquals(1, dayplan.getNumMeals());
    }

    @Test
    void setnumActivities() {
        dayplan.setnumActivities(2);
        assertEquals(2, dayplan.getnumActivities());
    }

    @Test
    void getnumActivities() {
        assertEquals(1, dayplan.getnumActivities());
    }

    @Test
    void setDescription() {
        dayplan.setDescription("can we finish this project on time");
        assertEquals("can we finish this project on time",dayplan.getDescription());
    }

    @Test
    void getDescription() {
        assertEquals("A happy day",dayplan.getDescription());
    }

    @Test
    void setVibe() {
        dayplan.setVibe("Active111, Inspiring, Motivational");
        assertEquals("Active111, Inspiring, Motivational", dayplan.getVibe());
    }

    @Test
    void getVibe() {
        assertEquals("Active, Inspiring, Motivational",dayplan.getVibe());
    }

    @Test
    void setPlan() {
        ArrayList<Business> planTest = new ArrayList<>();
        dayplan.setPlan(planTest);
        assertEquals(planTest, dayplan.getPlan());
    }

    @Test
    void getPlan() {
        assertEquals(plan, dayplan.getPlan());
    }

    @Test
    void getBusinessIDs() {
        assertEquals(businessIDs, dayplan.getBusinessIDs());
    }

    @Test
    void setBusinessIDs() {
        ArrayList<String> businessID = new ArrayList<>();
        businessID.add("so long");
        dayplan.setBusinessIDs(businessID);
        assertEquals(businessID, dayplan.getBusinessIDs());
    }

    @Test
    void dayplanToString() {
        String expected = """
                Active, Inspiring, Motivational
                Test Business 1, Location: [40.7128, -74.006], Distance: 10.0, Contact: 123-456-7890, Price: $$, Ratings: 4.5
                Test Activity, Location: [40.7128, -74.006], Distance: 5.0, Contact: 987-654-3210, Price: $, Ratings: 4.0
                """;
        assertEquals(expected, dayplan.dayplanToString());
    }

    @Test
    void replaceBusiness() {
        Business newBusiness = new Business("Replaced Business", location, 9.0, "222-333-4444", "$$$", 4.8f, "meal");
        String newBusinessID = "newID";
        assertTrue(dayplan.replaceBusiness(1, newBusiness, newBusinessID));
        assertEquals(newBusiness, dayplan.getPlan().get(1));

        assertFalse(dayplan.replaceBusiness(-1, newBusiness, newBusinessID)); // test invalid index
    }

    @Test
    void getBusinessNames() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("test business one");
        expected.add("test business one");
        assertEquals(expected, dayplan.getBusinessNames());
    }
}