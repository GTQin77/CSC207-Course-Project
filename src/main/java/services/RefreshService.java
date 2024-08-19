package services;

import entity.Dayplan;
import use_case.refresh.RefreshInputData;
import use_case.refresh.RefreshInteractor;

/**
 * Service class for handling refresh operations related to dayplan.
 */
public class RefreshService {
    private RefreshInteractor refreshInteractor;  // Handles the actual refresh logic

    public RefreshService(RefreshInteractor refreshInteractor) {
        this.refreshInteractor = refreshInteractor;
    }


    /**
     * Refreshes a day plan based on given input data.
     *
     * @param refreshInputData The input data needed for refreshing the dayplan.
     * @return The refreshed {@link Dayplan}.
     */
    public Dayplan refreshDayplan(RefreshInputData refreshInputData) {
        Dayplan refreshedDayplan = refreshInteractor.execute(refreshInputData);
        return  refreshedDayplan;
    }

}
