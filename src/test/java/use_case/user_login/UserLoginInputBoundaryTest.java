package use_case.user_login;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserLoginInputBoundaryTest {


    private UserLoginInputBoundary loginInputBoundary;


    @BeforeEach
    void setUp() {
        loginInputBoundary = Mockito.mock(UserLoginInputBoundary.class);
    }

    @Test
    void testLoginUser() {
        // Arrange
        String username = "testUser";
        String password = "password123";
        String location = "40.7128,-74.0060";  // Coordinates for New York

        UserLoginInputData loginInputData = new UserLoginInputData(username, password);

        ArrayList<Double> expectedCoordinates = new ArrayList<>(Arrays.asList(40.7128, -74.0060));

        User expectedUser = new User(username, "Test User", expectedCoordinates);
        when(loginInputBoundary.loginUser(loginInputData)).thenReturn(expectedUser);

        // Act
        User resultUser = loginInputBoundary.loginUser(loginInputData);

        // Assert
        ArgumentCaptor<UserLoginInputData> captor = ArgumentCaptor.forClass(UserLoginInputData.class);
        verify(loginInputBoundary).loginUser(captor.capture());

        UserLoginInputData capturedData = captor.getValue();
        assertEquals(username, capturedData.getUsername());
        assertEquals(password, capturedData.getPassword());
        assertEquals(expectedUser, resultUser);
        assertEquals(location, resultUser.getLocation());
    }
}