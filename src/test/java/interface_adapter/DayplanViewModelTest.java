package interface_adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DayplanViewModelTest {

    @Test
    void getUserDescription() {
        DayplanViewModel dayplanViewModel = new DayplanViewModel();
        dayplanViewModel.userDescription = "A fun and exciting day!";
        assertEquals(dayplanViewModel.getUserDescription(), dayplanViewModel.userDescription);
    }

    // problem with testing the user input from console since setUserDescription is parameterless.
    @Test
    void setUserDescription() {
    }

    // similar with ^
    @Test
    void setUserCity() {
    }

    @Test
    void getUserCity() {
    }

    //similar problem with other .set* methods
    @Test
    void setUserNumMeals() {
    }

    @Test
    void getUserNumMeals() {
    }

    // see above
    @Test
    void setUserNumActivities() {
    }

    @Test
    void getUserNumActivities() {
    }

    @Test
    void setUser() {
    }

    @Test
    void getUser() {
    }
}