package use_case.dayplanList;

import data_access.DayPlanDataAccessInterface;
import entity.DayplanFactory;
import entity.User;
import org.junit.jupiter.api.Test;
import entity.Dayplan;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.*;

public class UserDayPlanInteractorTest {

    private DayPlanDataAccessInterface dayPlanDataAccessObject;
    private UserDayPlanOutputBoundary dayplanPresenter;
    private DayplanFactory dayplanFactory;
    private UserDayPlanInteractor userDayPlanInteractor;

    @BeforeEach
    public void setUp() {
        dayPlanDataAccessObject = mock(DayPlanDataAccessInterface.class);
        dayplanPresenter = mock(UserDayPlanOutputBoundary.class);
        dayplanFactory = mock(DayplanFactory.class);

        userDayPlanInteractor = new UserDayPlanInteractor(dayPlanDataAccessObject, dayplanPresenter, dayplanFactory);
    }

    @Test
    public void testExecute() {
        // Arrange
        String locationString = "[123.7, 555.6]";
        User user = new User("testUser", "testPassword", new ArrayList<>());

        UserDayPlanInputData inputData = new UserDayPlanInputData(
                user, locationString, "City", 3, 2, "A fun day with friends"
        );

        ArrayList<Double> expectedLocation = new ArrayList<>(Arrays.asList(123.7, 555.6));
        Dayplan dayplan = new Dayplan();
        dayplan.setUser(user);
        dayplan.setLocation(expectedLocation);
        dayplan.setCity("City");
        dayplan.setNumMeals(3);
        dayplan.setnumActivities(2);
        dayplan.setDescription("A fun day with friends");
        dayplan.setBusinessIDs(new ArrayList<>());
        dayplan.setDescription("BusinessID1");


        when(dayplanFactory.create(
                user, expectedLocation, "City", 3, 2, "A fun day with friends"
        )).thenReturn(dayplan);

        Dayplan result = userDayPlanInteractor.execute(inputData);
        verify(dayplanFactory).create(
                eq(user), eq(expectedLocation), eq("City"), eq(3), eq(2), eq("A fun day with friends")
        );
        verify(dayPlanDataAccessObject).saveDayPlan(dayplan);

        ArgumentCaptor<UserDayPlanOutputData> outputCaptor = ArgumentCaptor.forClass(UserDayPlanOutputData.class);
        verify(dayplanPresenter).prepareDayplanView(outputCaptor.capture());

        assertEquals(dayplan, outputCaptor.getValue().getDayplan());

        // Verify the result is the expected dayplan
        assertEquals(dayplan, result);
    }
}

