package services;

import entity.Dayplan;
import use_case.refresh.RefreshInputData;
import use_case.refresh.RefreshInteractor;

public class RefreshService {
    private RefreshInteractor refreshInteractor;  // Handles the actual refresh logic

    public void RefreshService(RefreshInteractor refreshInteractor) {
        this.refreshInteractor = refreshInteractor;
    }


    public Dayplan refreshDayplan(RefreshInputData refreshInputData) {
        Dayplan refreshedDayplan = refreshInteractor.execute(refreshInputData);
        return  refreshedDayplan;
    }

}
