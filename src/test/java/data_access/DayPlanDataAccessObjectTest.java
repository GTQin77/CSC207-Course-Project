package data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayPlanDataAccessObjectTest {

    @BeforeEach
    void setUp() {
        DayPlanDataAccessObject dayPlanDataAccessObjectTest = new DayPlanDataAccessObject();
        dayPlanDataAccessObjectTest.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");
    }

    @Test
    void saveDayPlan() {
    }
}