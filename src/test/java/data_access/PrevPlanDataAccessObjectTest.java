package data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class PrevPlanDataAccessObjectTest {

    PrevPlanDataAccessObject prevPlanDataAccessObjectTest

    @BeforeEach
    void setUp() {
        prevPlanDataAccessObjectTest = new PrevPlanDataAccessObject();
        prevPlanDataAccessObjectTest.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");
    }

    @Test
    void getPreviousDayplan() {
        ArrayList<Double> location = new ArrayList<>(2);
        User testUser = new User("Frank", "123", location);
        String expected = "Business1, Location1, Distance1, Phone1, Price1, Rating1~Business2, Location2, Distance2, Phone2, Price2, Rating2";
        String test = prevPlanDataAccessObjectTest.getPreviousDayplan(testUser);
        assertEquals(expected, test);
    }
}