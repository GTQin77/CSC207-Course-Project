package use_case.user_signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserSignupInputBoundaryTest {

    private UserSignupInputBoundary inputBoundary;

    @BeforeEach
    void setUp() {
        inputBoundary = Mockito.mock(UserSignupInputBoundary.class);
    }

    @Test
    void testExecute() {
        // Arrange
        String username = "Ashley";
        String password = "password123";
        String repeatPassword = "password123";
        String location = "New York,USA";

        UserSignupInputData signupInputData = new UserSignupInputData(username, password, repeatPassword, location);
        inputBoundary.execute(signupInputData);
        ArgumentCaptor<UserSignupInputData> captor = ArgumentCaptor.forClass(UserSignupInputData.class);
        verify(inputBoundary).execute(captor.capture());

        UserSignupInputData capturedData = captor.getValue();
        assertEquals(username, capturedData.getUsername());
        assertEquals(password, capturedData.getPassword());
        assertEquals(repeatPassword, capturedData.getRepeatPassword());

        List<String> expectedLocation = new ArrayList<>(List.of("New York", "USA"));
        assertEquals(expectedLocation, capturedData.getLocation());
    }
}