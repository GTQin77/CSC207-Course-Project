package app;

import interface_adapter.Dayplan.DayplanViewModel;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.UserService;
import view.LoginView;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginUseCaseFactoryTest {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private DayplanInputViewModel dayplanInputViewModel;
    private SignupViewModel signupViewModel;
    private UserService userService;

    @BeforeEach
    void setUp() {
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        loginViewModel = Mockito.mock(LoginViewModel.class);
        dayplanInputViewModel = Mockito.mock(DayplanInputViewModel.class);
        signupViewModel = Mockito.mock(SignupViewModel.class);
        userService = Mockito.mock(UserService.class);
    }

    @Test
    void testCreate() {
        LoginView loginView = UserLoginUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel,
                signupViewModel,userService);
        assertNotNull(loginView);
    }
}