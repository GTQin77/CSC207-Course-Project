package use_case.edit_info;

import static org.junit.jupiter.api.Assertions.*;

import entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class EditInfoOutputDataTest {

    @Test
    void testGetUser() {
        User testUser = new User("username", "password", new ArrayList<>(Arrays.asList(22.2222, 11.1111)));
        EditInfoOutputData outputData = new EditInfoOutputData(testUser, "Test message", false);

        assertEquals(testUser, outputData.getUser());
    }

    @Test
    void testGetMessage() {
        String testMessage = "Test message";
        EditInfoOutputData outputData = new EditInfoOutputData(null, testMessage, false);

        assertEquals(testMessage, outputData.getMessage());
    }

    @Test
    void testGetUseCaseFailed() {
        boolean testFailureStatus = true;
        EditInfoOutputData outputData = new EditInfoOutputData(null, "Error occurred", testFailureStatus);

        assertEquals(testFailureStatus, outputData.getUseCaseFailed());
    }

}
