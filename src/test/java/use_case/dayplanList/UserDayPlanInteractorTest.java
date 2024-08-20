package use_case.dayplanList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import api.OpenInterface;
import api.YelpFusion;
import api.YelpInterface;
import data_access.DayPlanDataAccessInterface;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserDayPlanInteractorTest {

    private DayPlanDataAccessInterface dayPlanDataAccessObject;
    private UserDayPlanOutputBoundary dayplanPresenter;
    private CommonDayplanFactory dayplanFactory;
    private UserDayPlanInteractor userDayPlanInteractor;

    @BeforeEach
    public void setUp() {
        dayPlanDataAccessObject = mock(DayPlanDataAccessInterface.class);

        dayplanPresenter = mock(UserDayPlanOutputBoundary.class);

        YelpInterface yelpApi = mock(YelpInterface.class);
        OpenInterface openApi = mock(OpenInterface.class);
        YelpFusion yelpFusion = mock(YelpFusion.class);

        dayplanFactory = new CommonDayplanFactory();
        dayplanFactory.setOpenApi(openApi);
        dayplanFactory.setYelpApi(yelpApi);
        dayplanFactory.setBusinessFactory(new YelpBusinessFactory(yelpFusion));

        userDayPlanInteractor = new UserDayPlanInteractor(dayPlanDataAccessObject, dayplanPresenter, dayplanFactory);
    }


    @Test
    public void testSetAndGetDayplanFactory() {
        userDayPlanInteractor.setDayplanFactory(dayplanFactory);
        DayplanFactory actualFactory = userDayPlanInteractor.getDayplanFactory();

        assertEquals(dayplanFactory, actualFactory);
    }

    @Test
    public void testExecute() {
        ArrayList<Double> location = new ArrayList<>(Arrays.asList(123.7, 555.6));
        User user = new User("testUser", "testPassword", location);

        UserDayPlanInputData inputData = new UserDayPlanInputData(
                user, "123.7, 555.6", "City", 1, 1, "A fun day with friends"
        );


        Dayplan result = userDayPlanInteractor.execute(inputData);

        assertNotNull(result);
        verify(dayPlanDataAccessObject).saveDayPlan(any(Dayplan.class));
        verify(dayplanPresenter).prepareDayplanView(any(UserDayPlanOutputData.class));

        assertEquals("City", result.getCity());
        assertEquals(1, result.getnumActivities());
    }
}