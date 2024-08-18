package app;


import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.UserService;
import view.SignupView;

import static org.junit.jupiter.api.Assertions.*;

class UserSignUpUseCaseFactoryTest {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;
    private UserService userService;

    @BeforeEach
    void setUp() {
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        loginViewModel = Mockito.mock(LoginViewModel.class);
        signupViewModel = Mockito.mock(SignupViewModel.class);
        userService = Mockito.mock(UserService.class);
    }

    @Test
    void create() {
        SignupView signupView = UserSignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userService);
        assertNotNull(signupView);
    }
}
//*