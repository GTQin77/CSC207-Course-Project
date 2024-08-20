package use_case.refresh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import entity.Business;
import entity.Dayplan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import data_access.DayPlanDataAccessInterface;
import entity.RefreshBusinessFactory;
import entity.DayplanFactory;
import use_case.refresh.RefreshInputData;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RefreshInputBoundaryTest {

    @Mock
    private DayPlanDataAccessInterface dayPlanDataAccessObject;

    @Mock
    private DayplanFactory dayplanFactory;

    @Mock
    private RefreshBusinessFactory refreshBusinessFactory;

    @InjectMocks
    private RefreshInteractor refreshInteractor;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testExecute() {
        Dayplan dayplan = new Dayplan();
        dayplan.setPlan(new ArrayList<>());
        dayplan.setBusinessIDs(new ArrayList<>());

        dayplan.getPlan().add(new Business("OldBusiness1", new ArrayList<>(), 0.0, "123", "$", 4.0f, "Meal"));
        dayplan.getPlan().add(new Business("OldBusiness2", new ArrayList<>(), 0.0, "888", "$", 3.0f, "Activity"));

        dayplan.getBusinessIDs().add("OldID1");
        dayplan.getBusinessIDs().add("OldID2");

        RefreshInputData refreshInputData = new RefreshInputData(dayplan);

        Business newBusiness1 = new Business("NewBusiness1", new ArrayList<>(), 0.0, "000", "$$", 4.5f, "Meal");
        Business newBusiness2 = new Business("NewBusiness2", new ArrayList<>(), 0.0, "101", "$$$", 3.5f, "Activity");

        Map.Entry<Business, String> newBusinessEntry1 = new AbstractMap.SimpleEntry<>(newBusiness1, "NewID1");
        Map.Entry<Business, String> newBusinessEntry2 = new AbstractMap.SimpleEntry<>(newBusiness2, "NewID2");

        when(refreshBusinessFactory.generateNewBusiness(dayplan, "Meal")).thenReturn(newBusinessEntry1);
        when(refreshBusinessFactory.generateNewBusiness(dayplan, "Activity")).thenReturn(newBusinessEntry2);

        Dayplan result = refreshInteractor.execute(refreshInputData);

        // Assert the results
        verify(dayPlanDataAccessObject).saveDayPlan(dayplan);

        assertNotNull(result);
        assertEquals(2, result.getPlan().size());
        assertEquals("NewBusiness1", result.getPlan().get(0).getName());
        assertEquals("NewBusiness2", result.getPlan().get(1).getName());
        assertEquals("NewID1", result.getBusinessIDs().get(0));
        assertEquals("NewID2", result.getBusinessIDs().get(1));
    }
}
