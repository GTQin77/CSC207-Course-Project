package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {
    private Activity activity;
    private ArrayList<Double> location;

    @BeforeEach
    void setUp() {
        location = new ArrayList<>();
        location.add(43.6532);
        location.add(79.3832);
        activity = new Activity("testBusiness", location, 1.2, "334-556-7788", "$$$$", 4.1f);
    }

    @Test
    public void getType(){
        assertEquals("activity", activity.getType());
    }
}