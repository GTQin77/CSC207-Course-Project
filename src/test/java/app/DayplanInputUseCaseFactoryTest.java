package app;

import interface_adapter.Dayplan.DayplanViewModel;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.UserService;
import view.DayplanInputView;

import static org.junit.jupiter.api.Assertions.*;

public class DayplanInputUseCaseFactoryTest {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private DayplanInputViewModel dayplanInputViewModel;
    private DayplanViewModel dayplanViewModel;
    private UserService userService;

    @BeforeEach
    void setUp() {
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        loginViewModel = Mockito.mock(LoginViewModel.class);
        dayplanInputViewModel = Mockito.mock(DayplanInputViewModel.class);
        dayplanViewModel = Mockito.mock(DayplanViewModel.class);
        userService = Mockito.mock(UserService.class);
    }

    @Test
    void testCreate() {
        DayplanInputView dayplanInputView = DayplanInputUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel, dayplanViewModel, userService);
        assertNotNull(dayplanInputView);
    }

}
