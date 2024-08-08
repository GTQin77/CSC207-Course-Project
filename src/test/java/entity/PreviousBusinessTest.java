package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreviousBusinessTest {

    @Test
    void getBusiness() {
        PreviousBusiness business = new PreviousBusiness("testBusiness", "testLocation", "0", "1234567890", "$$", "5.0");
        assertEquals("testBusiness", business.getBusiness());
    }

    @Test
    void getLocation() {
        PreviousBusiness business = new PreviousBusiness("testBusiness", "testLocation", "0", "1234567890", "$$", "5.0");
        assertEquals("testLocation", business.getLocation());
    }

    @Test
    void getDistance() {
        PreviousBusiness business = new PreviousBusiness("testBusiness", "testLocation", "0", "1234567890", "$$", "5.0");
        assertEquals("0", business.getDistance());
    }

    @Test
    void getPhoneNumber() {
        PreviousBusiness business = new PreviousBusiness("testBusiness", "testLocation", "0", "1234567890", "$$", "5.0");
        assertEquals("1234567890", business.getPhoneNumber());
    }

    @Test
    void getPrice() {
        PreviousBusiness business = new PreviousBusiness("testBusiness", "testLocation", "0", "1234567890", "$$", "5.0");
        assertEquals("$$", business.getPrice());
    }

    @Test
    void getRating() {
        PreviousBusiness business = new PreviousBusiness("testBusiness", "testLocation", "0", "1234567890", "$$", "5.0");
        assertEquals("5.0", business.getRating());
    }
}