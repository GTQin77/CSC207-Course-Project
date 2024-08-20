package use_case.user_signup;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class UserSignupOutputDataTest {

    @Test
    void testGetterUser() {
        User expectedUser = new User("username", "password", new ArrayList<>(Arrays.asList(11.1111, 22.2222)));
        String creationTime = "2024-08-01T12:00:00";
        boolean useCaseFailed = false;

        UserSignupOutputData outputData = new UserSignupOutputData(expectedUser, creationTime, useCaseFailed);

        assertEquals(expectedUser, outputData.getUser());
    }

    @Test
    void testGetCreationTime() {
        User user = new User("username", "password", new ArrayList<>(Arrays.asList(11.1111, 22.2222)));
        String expectedCreationTime = "2024-08-01T12:00:00";
        boolean useCaseFailed = false;

        UserSignupOutputData outputData = new UserSignupOutputData(user, expectedCreationTime, useCaseFailed);

        assertEquals(expectedCreationTime, outputData.getCreationTime());
    }

    @Test
    void testSetCreationTime() {
        User user = new User("username", "password", new ArrayList<>(Arrays.asList(11.1111, 22.2222)));
        String initialCreationTime = "2024-08-01T12:00:00";
        String updatedCreationTime = "2024-08-01T15:00:00";
        boolean useCaseFailed = false;

        UserSignupOutputData outputData = new UserSignupOutputData(user, initialCreationTime, useCaseFailed);
        outputData.setCreationTime(updatedCreationTime);

        assertEquals(updatedCreationTime, outputData.getCreationTime());
    }
}
