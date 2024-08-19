package entity;

import api.YelpFusion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class YelpBusinessFactoryTest {

    @Test
    void createBusiness() {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(43.6532);
        location.add(79.3832);
        User user = new User("amelia", "wu", location);

        String businessID = "north-india-restaurant-san-francisco";
        YelpFusion yelpFusion = new YelpFusion();
        YelpBusinessFactory factory = new YelpBusinessFactory(yelpFusion);
        factory.setUser(user);
        Business business = factory.createBusiness(businessID, location);

        assertNotNull(business);
    }
}