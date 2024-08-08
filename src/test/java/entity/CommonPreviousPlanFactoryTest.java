package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommonPreviousPlanFactoryTest {
    private CommonPreviousPlanFactory factory;

    @BeforeEach
    void setUp() {
        factory = new CommonPreviousPlanFactory();
    }

    @Test
    void convertDayplan() {
        ArrayList<PreviousBusiness> businesses = new ArrayList<>();
        businesses.add(new PreviousBusiness("testBusiness1", "testLocation1",
                "0.0,0.0", "1234567890", "$$", "5.0"));
        businesses.add(new PreviousBusiness("testBusiness2", "testLocation2",
                "0.0,0.0", "1234567890", "$$", "5.0"));

        PreviousPlan plan = factory.convertDayplan("testBusiness1;testLocation1;0.0,0.0;123456789;$$;5.0~testBusiness2;testLocation2;0.0,0.0;123456789;$$;5.0");

        assertEquals(2, plan.getSize());
        assertEquals("testBusiness1", plan.getBusinesses(0).getBusiness());
        assertEquals("testBusiness2", plan.getBusinesses(1).getBusiness());

    }
}