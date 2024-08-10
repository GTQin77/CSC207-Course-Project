package use_case.refresh;

import data_access.DayPlanDataAccessInterface;
import entity.DayplanFactory;
import entity.Dayplan;
import entity.Business;
import entity.RefreshBusinessFactory;

import java.util.Map;

public class RefreshInteractor implements RefreshInputBoundary{
    final DayPlanDataAccessInterface dayPlanDataAccessObject;
    final DayplanFactory dayplanFactory;
    final RefreshBusinessFactory refreshBusinessFactory;

    public RefreshInteractor(DayPlanDataAccessInterface dayPlanDataAccessObject, DayplanFactory dayplanFactory, RefreshBusinessFactory businessFactory) {
        this.dayPlanDataAccessObject = dayPlanDataAccessObject;
        this.dayplanFactory = dayplanFactory;
        this.refreshBusinessFactory = businessFactory;
    }

    @Override
    public Dayplan execute(RefreshInputData refreshInputData) {
        Dayplan dayplan = refreshInputData.getDayplan();
        refreshAllBusiness(dayplan);

        this.dayPlanDataAccessObject.saveDayPlan(dayplan);
        return dayplan;
    }

    private void refreshOneBusiness(Dayplan dayplan, Integer refreshIndex) {
        Business prevBusiness = dayplan.getPlan().get(refreshIndex);
        String type = prevBusiness.getType();
        Map.Entry<Business, String> newBusinessEntry = refreshBusinessFactory.generateNewBusiness(dayplan, type);
        Business newBusiness = newBusinessEntry.getKey();
        String newBusinessID = newBusinessEntry.getValue();
        dayplan.replaceBusiness(refreshIndex, newBusiness, newBusinessID);
    }

    private void refreshAllBusiness(Dayplan dayplan) {
        int size = dayplan.getPlan().size();
        for (int i = 0; i < size; i++) {
            refreshOneBusiness(dayplan, i);
        }
    }
}
