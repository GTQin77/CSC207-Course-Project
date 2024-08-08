package use_case.user_signup;

import entity.User;
import data_access.UserSignupDataAccessInterface;
import org.mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.UserFactory;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserSignupInteractorTest {

    @Mock
    private UserSignupDataAccessInterface userDataAccessObject;

    @Mock
    private UserSignupOutputBoundary userPresenter;

    @Mock
    private UserFactory userFactory;

    @InjectMocks
    private UserSignupInteractor userSignupInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_SuccessfulSignup() {
        UserSignupInputData inputData = new UserSignupInputData("testUser", "password123", "password123", "34.0522,-118.2437");

        ArrayList<Double> location = new ArrayList<>(Arrays.asList(34.0522, -118.2437));
        User mockUser = new User("testUser", "password123", location);

        when(userFactory.create(anyString(), anyString(), any())).thenReturn(mockUser);
        when(userDataAccessObject.userExists(anyString())).thenReturn(false);

        userSignupInteractor.execute(inputData);

        ArgumentCaptor<UserSignupOutputData> captor = ArgumentCaptor.forClass(UserSignupOutputData.class);
        verify(userPresenter).prepareSuccessView(captor.capture(), eq("testUser created."));
        UserSignupOutputData capturedOutputData = captor.getValue();

        assertEquals(mockUser, capturedOutputData.getUser());
    }

    @Test
    void testExecute_UsernameAlreadyExists() {
        UserSignupInputData inputData = new UserSignupInputData("testUser", "password123", "password123", "34.0522,-118.2437");

        ArrayList<Double> location = new ArrayList<>(Arrays.asList(34.0522, -118.2437));
        User mockUser = new User("testUser", "password123", location);

        when(userFactory.create(anyString(), anyString(), any())).thenReturn(mockUser);
        when(userDataAccessObject.userExists(anyString())).thenReturn(true);

        userSignupInteractor.execute(inputData);

        verify(userPresenter).prepareFailView("Oops! This username already exists.");
    }

    @Test
    void testExecute_PasswordsDoNotMatch() {
        UserSignupInputData inputData = new UserSignupInputData("testUser", "password123", "password321", "34.0522,-118.2437");

        ArrayList<Double> location = new ArrayList<>(Arrays.asList(34.0522, -118.2437));
        User mockUser = new User("testUser", "password123", location);

        when(userFactory.create(anyString(), anyString(), any())).thenReturn(mockUser);
        when(userDataAccessObject.userExists(anyString())).thenReturn(false);

        userSignupInteractor.execute(inputData);

        verify(userPresenter).prepareFailView("Passwords don't match.");
    }
}


