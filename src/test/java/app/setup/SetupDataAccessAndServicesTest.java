package app.setup;

import static org.mockito.Mockito.*;

import data_access.DayPlanDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.RefreshService;
import services.UserService;
import view.ViewManager;

import javax.swing.JPanel;

class SetupDataAccessAndServicesTest {

    private SetupDataAccessAndServices setupDataAccessAndServices;
    private ViewManagerModel viewManagerModel;
    private ViewManager viewManager;
    private JPanel views;
    private UserDataAccessSetup dataAccessSetup;
    private ServiceFactory serviceFactory;
    private ViewSetup viewSetup;

    @BeforeEach
    void setUp() {
        setupDataAccessAndServices = new SetupDataAccessAndServices();
        viewManagerModel = mock(ViewManagerModel.class);
        viewManager = mock(ViewManager.class);
        views = mock(JPanel.class);

        dataAccessSetup = mock(UserDataAccessSetup.class);
        serviceFactory = mock(ServiceFactory.class);
        viewSetup = mock(ViewSetup.class);
    }

    @Test
    void testConfigureServicesAndView() {
        UserSignupDataAccessInterface userDAO = mock(UserSignupDataAccessInterface.class);
        DayPlanDataAccessInterface dayplanDAO = mock(DayPlanDataAccessInterface.class);
        UserService userService = mock(UserService.class);
        RefreshService refreshService = mock(RefreshService.class);

        when(dataAccessSetup.createUserSignupDataAccess()).thenReturn(userDAO);
        when(dataAccessSetup.createDayPlanDataAccess()).thenReturn(dayplanDAO);
        when(serviceFactory.createUserService()).thenReturn(userService);
        when(serviceFactory.createRefreshService()).thenReturn(refreshService);

        setupDataAccessAndServices.setup(viewManagerModel, viewManager, views);
    }
}
