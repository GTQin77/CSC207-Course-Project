package use_case.refresh;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import data_access.DayPlanDataAccessInterface;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Map;
import java.util.AbstractMap;



import java.util.ArrayList;

public class RefreshInteractorTest {

    @Mock
    private DayPlanDataAccessInterface dayPlanDataAccessObject;

    @Mock
    private RefreshOutputBoundary refreshPresenter;

    @Mock
    private DayplanFactory dayplanFactory;

    @Mock
    private CommonRefreshBusinessFactory refreshBusinessFactory;

    @InjectMocks
    private RefreshInteractor refreshInteractor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        ArrayList<Double> userLocation = new ArrayList<>();
        userLocation.add(40.7128);
        userLocation.add(-74.0060);

        User user = new User("testUser", "testPassword", userLocation);

        ArrayList<Double> location1 = new ArrayList<>();
        location1.add(40.7128);
        location1.add(-74.0060);

        ArrayList<Double> location2 = new ArrayList<>();
        location2.add(34.0522);
        location2.add(-118.2437);

        Business business1 = new Business("Eaton Centre", location1, 10.0, "123-456-7890", "$$", 4.5f, "Type1");
        Business business2 = new Business("McDonald's", location2, 15.0, "098-765-4321", "$$$", 3.8f, "Type2");

        ArrayList<Business> businesses = new ArrayList<>();
        businesses.add(business1);
        businesses.add(business2);

        Dayplan dayplan = new Dayplan();
        dayplan.setUser(user);
        dayplan.setPlan(businesses);
        dayplan.setCity("Toronto");
        dayplan.setNumMeals(2);
        dayplan.setnumActivities(3);
        dayplan.setDescription("Test Description");
        dayplan.setVibe("Cozy relaxing day with friends");
        dayplan.setBusinessIDs(new ArrayList<>());

        RefreshInputData refreshInputData = mock(RefreshInputData.class);
        when(refreshInputData.getDayplan()).thenReturn(dayplan);

        Business newBusiness1 = new Business("Eaton Centre", location1, 10.0, "123-456-7890", "$$", 4.5f, "Type1");
        Business newBusiness2 = new Business("McDonald's", location2, 15.0, "098-765-4321", "$$$", 3.8f, "Type2");

        Map.Entry<Business, String> newBusinessEntry1 = new AbstractMap.SimpleEntry<>(newBusiness1, "NewBusinessID1");
        Map.Entry<Business, String> newBusinessEntry2 = new AbstractMap.SimpleEntry<>(newBusiness2, "NewBusinessID2");

        when(refreshBusinessFactory.generateNewBusiness(dayplan, "Type1")).thenReturn(newBusinessEntry1);
        when(refreshBusinessFactory.generateNewBusiness(dayplan, "Type2")).thenReturn(newBusinessEntry2);

        Dayplan result = refreshInteractor.execute(refreshInputData);

        verify(dayPlanDataAccessObject).saveDayPlan(dayplan);

        // Asserting the result
        assertNotNull(result);
        assertEquals(2, result.getPlan().size());
        assertEquals(newBusiness1, result.getPlan().get(0));
        assertEquals(newBusiness2, result.getPlan().get(1));
        assertEquals("NewBusinessID1", dayplan.getBusinessIDs().get(0));
        assertEquals("NewBusinessID2", dayplan.getBusinessIDs().get(1));
    }
}
