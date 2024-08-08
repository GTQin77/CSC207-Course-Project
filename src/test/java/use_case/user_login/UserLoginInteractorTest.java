package use_case.user_login;

import data_access.UserLoginDataAccessInterface;
import entity.CommonUserFactory;
import entity.User;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Login.UserLoginPresenter;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginInteractorTest {

    private UserLoginDataAccessInterface userLoginDataAccess;
    private UserLoginPresenter userLoginPresenter;
    private UserLoginInteractor userLoginInteractor;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private DayplanInputViewModel dayplanInputViewModel;
    private CommonUserFactory userFactory;

    @BeforeEach
    void setUp() {
        userLoginDataAccess = new UserLoginDataAccessInterface() {
            private User user;

            @Override
            public boolean findUser(String username, String password) {
                if ("testUser".equals(username) && "testPass".equals(password)) {
                    user = new User(username, password, new ArrayList<>(Arrays.asList(43.6532, 79.3832)));
                    return true;
                }
                return false;
            }

            @Override
            public User getUser(String username, String password) {
                return user;
            }
        };

        loginViewModel = new LoginViewModel();
        viewManagerModel = new ViewManagerModel();
        dayplanInputViewModel = new DayplanInputViewModel();
        userLoginPresenter = new UserLoginPresenter(viewManagerModel, loginViewModel, dayplanInputViewModel);
        userFactory = new CommonUserFactory();
        userLoginInteractor = new UserLoginInteractor(userLoginDataAccess, userLoginPresenter, userFactory);
    }

    @Test
    void testLoginSuccess() {
        String username = "testUser";
        String password = "testPass";

        UserLoginInputData inputData = new UserLoginInputData(username, password);

        User user = userLoginInteractor.loginUser(inputData);
        assertNotNull(user);
        assertEquals(username, user.getUserName());

        LoginState loginState = loginViewModel.getState();
        assertEquals(username, loginState.getUsername());
        assertNotNull(loginState.getLoginTime());
        assertTrue(loginState.loginSuccessful());

        ArrayList<Double> expectedLocation = new ArrayList<>(Arrays.asList(43.6532, 79.3832)); // Example coordinates for Toronto
        assertEquals(expectedLocation, user.getLocation());

        // Check if the view switched to dayplan input view
        assertEquals(dayplanInputViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void testLoginFailure() {
        // Arrange
        String username = "testUser";
        String incorrectPassword = "wrongPass";
        UserLoginInputData inputData = new UserLoginInputData(username, incorrectPassword);
        User user = userLoginInteractor.loginUser(inputData);

        // Assert
        assertNull(user);

        LoginState loginState = loginViewModel.getState();
        assertFalse(loginState.loginSuccessful());
        assertNotNull(loginState.getErrorMessage());
        assertEquals("Incorrect username or password!", loginState.getErrorMessage());
    }

    @Test
    void testLoginFailureWithIncorrectUsername() {
        String incorrectUsername = "wrongUser";
        String password = "testPass";
        UserLoginInputData inputData = new UserLoginInputData(incorrectUsername, password);
        User user = userLoginInteractor.loginUser(inputData);

        // Assert
        assertNull(user);

        LoginState loginState = loginViewModel.getState();
        assertFalse(loginState.loginSuccessful());
        assertNotNull(loginState.getErrorMessage());
        assertEquals("Incorrect username or password!", loginState.getErrorMessage());
    }
}
