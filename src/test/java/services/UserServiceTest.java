package services;

import data_access.DayPlanDataAccessInterface;
import data_access.UserSignupDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.*;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    private UserService userService;
    private UserSignupDataAccessInterface userSignupDAO;
    private DayPlanDataAccessInterface dayplanDAO;
    private PropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        userSignupDAO = mock(UserSignupDataAccessInterface.class);
        dayplanDAO = mock(DayPlanDataAccessInterface.class);
        userService = new UserService(userSignupDAO, dayplanDAO);
        listener = mock(PropertyChangeListener.class);
        userService.addPropertyChangeListener(listener);
    }

    @Test
    void loadInitialUser() {
        User result = userService.getCurrentUser();
        assertNotNull(result);
    }

    @Test
    void loadInitialDayplan() {
        Dayplan result = userService.getDayplan();
        assertNotNull(result);
    }

    @Test
    void getAndSetBusiness() {
        Business business = new Business("Test Business", new ArrayList<>(), 12.0, "1234567890", "$$", 5.0f, "meal");
        userService.setBusiness(business);
        assertEquals(business, userService.getBusiness());
    }

    @Test
    void getAndSetPrevView() {
        String prevView = "log in";
        userService.setPrevView(prevView);
        assertEquals(prevView, userService.getPrevView());
    }

    @Test
    void testSetDayplanFiresPropertyChange() {
        Dayplan newDayplan = new Dayplan();
        ArgumentCaptor<Dayplan> dayplanCaptor = ArgumentCaptor.forClass(Dayplan.class);

        userService.setDayplan(newDayplan);
        verify(listener).propertyChange(any());

        verify(listener).propertyChange(argThat(evt -> "dayplan".equals(evt.getPropertyName())
                && evt.getOldValue() != evt.getNewValue()));
    }

    @Test
    void setCurrentLocation() {
        String location = "11.11,22.22";
        userService.setCurrentLocation(location);
        assertNotNull(userService.getCurrentLocation());
        assertEquals("11.11", userService.getCurrentLocation().get(0));
        assertEquals("22.22", userService.getCurrentLocation().get(1));
    }

    @Test
    void setCurrentUser() {
        User newUser = new User("testUserId", "password", new ArrayList<>());
        userService.setCurrentUser(newUser);
        assertEquals(newUser, userService.getCurrentUser());
    }
}