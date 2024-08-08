package use_case.user_signup;

import entity.User;
import data_access.UserSignupDataAccessInterface;
import entity.CommonUserFactory;
import interface_adapter.Signup.SignupPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import entity.UserFactory;
import interface_adapter.Signup.SignupViewModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserSignupInteractorTest {

    private UserSignupDataAccessInterface userDataAccessObject;
    private UserSignupOutputBoundary userPresenter;
    private CommonUserFactory userFactory;
    private UserSignupInteractor userSignupInteractor;

    @BeforeEach
    void setUp() {
        userDataAccessObject = mock(UserSignupDataAccessInterface.class);
        userPresenter = mock(UserSignupOutputBoundary.class);
        userFactory = mock(CommonUserFactory.class);
        userSignupInteractor = new UserSignupInteractor(userDataAccessObject, userPresenter, userFactory);
    }

    @Test
    void testSignupSuccess() {
        // Arrange
        String username = "Hannah24";
        String password = "password123";
        String repeatPassword = "password123";
        ArrayList<Double> location = new ArrayList<>();
        location.add(1.23);
        location.add(4.56);

        UserSignupInputData inputData = new UserSignupInputData(username, password, repeatPassword, location);
        User user = new User(username, password, location);

        when(userDataAccessObject.userExists(username)).thenReturn(false);
        when(userFactory.create(username, password, location)).thenReturn(user);

        // Act
        userSignupInteractor.execute(inputData);

        // Assert
        verify(userDataAccessObject, times(1)).saveUser(user);
        verify(userPresenter, times(1)).prepareSuccessView(any(UserSignupOutputData.class), eq("%s created.".formatted(username)));
    }

    @Test
    void testUsernameAlreadyExists() {
        // Arrange
        String username = "existingUser";
        String password = "password123";
        String repeatPassword = "password123";
        ArrayList<Double> location = new ArrayList<>();
        location.add(1.23);
        location.add(4.56);

        UserSignupInputData inputData = new UserSignupInputData(username, password, repeatPassword, location);

        when(userDataAccessObject.userExists(username)).thenReturn(true);

        // Act
        userSignupInteractor.execute(inputData);

        // Assert
        verify(userPresenter, times(1)).prepareFailView("Oops! This username already exists.");
        verify(userDataAccessObject, never()).saveUser(any(User.class));
    }

    @Test
    void testPasswordsDontMatch() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        String repeatPassword = "password456";
        ArrayList<Double> location = new ArrayList<>();
        location.add(1.23);
        location.add(4.56);

        UserSignupInputData inputData = new UserSignupInputData(username, password, repeatPassword, location);

        // Act
        userSignupInteractor.execute(inputData);

        // Assert
        verify(userPresenter, times(1)).prepareFailView("Passwords don't match.");
        verify(userDataAccessObject, never()).saveUser(any(User.class));
    }
}