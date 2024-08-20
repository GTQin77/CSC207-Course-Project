package use_case.edit_info;

import static org.junit.jupiter.api.Assertions.*;

import entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class EditInfoOutputDataTest {

    @Test
    void testGetUser() {
        User testUser = new User("username", "password", new ArrayList<>(Arrays.asList(40.7128, -74.0060)));
        EditInfoOutputData outputData = new EditInfoOutputData(testUser, "Test message", false);

        assertEquals(testUser, outputData.getUser(), "The getUser method should return the correct user object.");
    }

    @Test
    void testGetMessage() {
        String testMessage = "Test message";
        EditInfoOutputData outputData = new EditInfoOutputData(null, testMessage, false);

        assertEquals(testMessage, outputData.getMessage(), "The getMessage method should return the correct message.");
    }

    @Test
    void testGetUseCaseFailed() {
        boolean testFailureStatus = true;
        EditInfoOutputData outputData = new EditInfoOutputData(null, "Error occurred", testFailureStatus);

        assertEquals(testFailureStatus, outputData.getUseCaseFailed(), "The getUseCaseFailed method should return the correct failure status.");
    }

}
