package entity;

import api.YelpFusion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YelpBusinessFactoryTest {

    @Test
    void createBusiness() {
        String businessID = "north-india-restaurant-san-francisco";
        YelpFusion yelpFusion = new YelpFusion();
        YelpBusinessFactory factory = new YelpBusinessFactory(yelpFusion);
        Business business = factory.createBusiness(businessID);

        assertNotNull(business);
    }
}