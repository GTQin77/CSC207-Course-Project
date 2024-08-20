package use_case.user_login;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;

class UserLoginOutputDataTest {

    @Test
    void getUser() {
        User expectedUser = new User("username", "password", new ArrayList<>(Arrays.asList(11.1111, 22.2222)));
        UserLoginOutputData loginOutputData = new UserLoginOutputData(expectedUser, true, "2024-08-01T12:00:00");

        assertEquals(expectedUser, loginOutputData.getUser());
    }

    @Test
    void isLoginSuccessful() {
        User user = new User("username", "password", new ArrayList<>(Arrays.asList(11.1111, 22.2222)));
        boolean expectedSuccess = true;
        UserLoginOutputData loginOutputData = new UserLoginOutputData(user, expectedSuccess, "2024-08-01T12:00:00");

        assertTrue(loginOutputData.isLoginSuccessful());
    }

    @Test
    void getLoginTime() {
        User user = new User("username", "password", new ArrayList<>(Arrays.asList(11.1111, 22.2222)));
        String expectedLoginTime = "2024-08-01T12:00:00";
        UserLoginOutputData loginOutputData = new UserLoginOutputData(user, true, expectedLoginTime);

        assertEquals(expectedLoginTime, loginOutputData.getLoginTime());
    }

    @Test
    void setLoginTime() {
        User user = new User("username", "password", new ArrayList<>(Arrays.asList(11.1111, 22.2222)));
        String initialLoginTime = "2024-08-01T12:00:00";
        String updatedLoginTime = "2024-08-01T15:00:00";
        UserLoginOutputData loginOutputData = new UserLoginOutputData(user, true, initialLoginTime);

        loginOutputData.setLoginTime(updatedLoginTime);

        assertEquals(updatedLoginTime, loginOutputData.getLoginTime());
    }
}
