package use_case.dayplanList;

import entity.Dayplan;
import entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDayPlanOutputDataTest {

    @Test
    public void testGetDayplan() {
        // Arrange
        ArrayList<Double> location = new ArrayList<>(Arrays.asList(42.0, 82.0));
        User user = new User("testUser", "testPassword", new ArrayList<>());
        Dayplan dayplan = new Dayplan();
        dayplan.setUser(user);
        dayplan.setLocation(location);
        dayplan.setCity("New York");
        dayplan.setNumMeals(3);
        dayplan.setnumActivities(2);
        dayplan.setDescription("A fun and spontaneous day");
        dayplan.setPlan(new ArrayList<>());
        dayplan.setBusinessIDs(new ArrayList<>());
        UserDayPlanOutputData outputData = new UserDayPlanOutputData(dayplan);
        Dayplan result = outputData.getDayplan();

        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(location, result.getLocation());
        assertEquals("New York", result.getCity());
        assertEquals(3, result.getNumMeals());
        assertEquals(2, result.getnumActivities());
        assertEquals("A fun and spontaneous day", result.getDescription());
        assertNotNull(result.getPlan());
        assertTrue(result.getPlan().isEmpty());
        assertNotNull(result.getBusinessIDs());
        assertTrue(result.getBusinessIDs().isEmpty());
    }
}
