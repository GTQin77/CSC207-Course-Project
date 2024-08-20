package use_case.edit_info;

import static org.junit.jupiter.api.Assertions.*;


import entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class EditInfoInputDataTest {

    @Test
    void testConstructor() {
        User testUser = new User("username", "password", new ArrayList<>(Arrays.asList(40.7128, -74.0060)));
        String userName = "testUser";
        String password = "testPass";
        String repeatPassword = "testPass";
        String location = "40.7128, -74.0060";

        EditInfoInputData inputData = new EditInfoInputData(userName, password, repeatPassword, location, testUser);

        assertAll(
                () -> assertEquals(userName, inputData.getUserName()),
                () -> assertEquals(password, inputData.getPassword()),
                () -> assertEquals(repeatPassword, inputData.getRepeatPassword()),
                () -> assertEquals("40.7128,-74.0060", inputData.getLocation()),
                () -> assertEquals(testUser, inputData.getUser())
        );
    }

    @Test
    void testRemoveLocationSpaces() {
        String locationWithSpaces = "40.7128, -74.0060";
        String expectedLocation = "40.7128,-74.0060";

        String processedLocation = EditInfoInputData.removeLocationSpaces(locationWithSpaces);

        assertEquals(expectedLocation, processedLocation, "Location should be processed to remove spaces.");
    }
}
