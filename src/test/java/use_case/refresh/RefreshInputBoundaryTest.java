package use_case.refresh;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import data_access.DayPlanDataAccessInterface;
import entity.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;

public class RefreshInteractorTest {

    @Mock
    private DayPlanDataAccessInterface dayPlanDataAccessObject;

    @Mock
    private RefreshOutputBoundary refreshPresenter;

    @Mock
    private DayplanFactory dayplanFactory;

    @Mock
    private RefreshBusinessFactory refreshBusinessFactory;

    @InjectMocks
    private RefreshInteractor refreshInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        // Create test data
        ArrayList<Double> location1 = new ArrayList<>();
        location1.add(40.7128);
        location1.add(-74.0060);

        ArrayList<Double> location2 = new ArrayList<>();
        location2.add(34.0522);
        location2.add(-118.2437);

        Business business1 = new Business("Business1", location1, 10.0, "123-456-7890", "$$", 4.5f, "Type1");
        Business business2 = new Business("Business2", location2, 15.0, "098-765-4321", "$$$", 3.8f, "Type2");

        Dayplan dayplan = new Dayplan();
        dayplan.addBusiness(business1);
        dayplan.addBusiness(business2);

        RefreshInputData refreshInputData = mock(RefreshInputData.class);
        when(refreshInputData.getDayplan()).thenReturn(dayplan);

        Business newBusiness1 = new Business("NewBusiness1", location1, 10.0, "123-456-7890", "$$", 4.5f, "Type1");
        Business newBusiness2 = new Business("NewBusiness2", location2, 15.0, "098-765-4321", "$$$", 3.8f, "Type2");

        when(refreshBusinessFactory.generateNewBusiness(dayplan, "Type1")).thenReturn(newBusiness1);
        when(refreshBusinessFactory.generateNewBusiness(dayplan, "Type2")).thenReturn(newBusiness2);

        // Execute the method
        Dayplan result = refreshInteractor.execute(refreshInputData);

        // Verify interactions
        verify(dayPlanDataAccessObject).saveDayPlan(dayplan);

        // Validate the result
        assertNotNull(result);
        assertEquals(2, result.getPlan().size());
        assertEquals(newBusiness1, result.getPlan().get(0));
        assertEquals(newBusiness2, result.getPlan().get(1));
    }
}
