package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {
    private Business business;
    private ArrayList<Double> location;

    @BeforeEach
    void setUp() {
        location = new ArrayList<>();
        location.add(43.6532);
        location.add(79.3832);
        business = new Business("testBusiness", location, 1.2, "334-556-7788", "$$$$", 4.1f, "meal");
    }


    @Test
    void getName() {
        assertEquals("testBusiness", business.getName());
    }

    @Test
    void setName() {
        business.setName("testBusiness2");
        assertEquals("testBusiness2", business.getName());
    }

    @Test
    void getLocation() {
        assertEquals(location, business.getLocation());
    }

    @Test
    void setLocation() {
        ArrayList<Double> newLocation = new ArrayList<>();
        newLocation.add(33.4455);
        newLocation.add(66.7788);
        business.setLocation(newLocation);
        assertEquals(newLocation, business.getLocation());
    }

    @Test
    void getDistance() {
        assertEquals(1.2, business.getDistance(), 0.001);
    }

    @Test
    void setDistance() {
        business.setDistance(1.4);
        assertEquals(1.4, business.getDistance(), 0.001);
    }

    @Test
    void getContactNum() {
        assertEquals("334-556-7788", business.getContactNum());
    }

    @Test
    void setContactNum() {
        business.setContactNum("883-112-5566");
        assertEquals("883-112-5566", business.getContactNum());
    }

    @Test
    void getPrice() {
        assertEquals("$$$$", business.getPrice());
    }

    @Test
    void setPrice() {
        business.setPrice("$");
        assertEquals("$", business.getPrice());
    }

    @Test
    void getRatings() {
        assertEquals(4.1f, business.getRatings());
    }

    @Test
    void setRatings() {
        business.setRatings(4.6f);
        assertEquals(4.6f, business.getRatings());
    }

    @Test
    void getType() {
        assertEquals("meal", business.getType());
    }

    @Test
    void setType() {
        business.setType("activity");
        assertEquals("activity", business.getType());
    }
}