package services;

import entity.Dayplan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.refresh.RefreshInputData;
import use_case.refresh.RefreshInteractor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

class RefreshServiceTest {

    private RefreshService refreshService;
    private RefreshInteractor refreshInteractor;

    @BeforeEach
    void setUp() {
        refreshInteractor = mock(RefreshInteractor.class);
        refreshService = new RefreshService(refreshInteractor);
    }

    @Test
    void returnsUpdatedDayplan() {
        Dayplan expectedDayplan = new Dayplan();
        RefreshInputData inputData = new RefreshInputData(expectedDayplan);

        when(refreshInteractor.execute(inputData)).thenReturn(expectedDayplan);
        Dayplan resultDayplan = refreshService.refreshDayplan(inputData);
        verify(refreshInteractor, times(1)).execute(inputData);

        assertEquals(expectedDayplan, resultDayplan);
    }

    @Test
    void invokesRefreshInteractor() {
        RefreshInputData inputData = new RefreshInputData(new Dayplan());
        refreshService.refreshDayplan(inputData);

        verify(refreshInteractor, times(1)).execute(inputData);
    }
}
