package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreviousPlanTest {
    private PreviousPlan previousPlan;

    @BeforeEach
    void setUp() {
        previousPlan = new PreviousPlan();
    }

    @Test
    void addBusiness() {
        PreviousBusiness business = new PreviousBusiness("testBusiness", "testLocation", "0", "1234567890", "$$", "5.0");
        previousPlan.addBusiness(business);
        assertEquals(business, previousPlan.getBusinesses(0));
    }

    @Test
    void getBusinesses() {
        PreviousBusiness business1 = new PreviousBusiness("testBusiness1", "testLocation1", "0", "1234567890", "$$", "5.0");
        PreviousBusiness business2 = new PreviousBusiness("testBusiness2", "testLocation2", "0", "1234567890", "$$", "5.0");
        previousPlan.addBusiness(business1);
        previousPlan.addBusiness(business2);
        assertEquals(business1, previousPlan.getBusinesses(0));
        assertEquals(business2, previousPlan.getBusinesses(1));
    }
}