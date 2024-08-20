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

    /**
     * Executes the refresh operation on a given day plan based on input data.
     *
     * @param refreshInputData The data containing the day plan to be refreshed.
     * @return The updated day plan after applying the refresh operations.
     */
    @Override
    public Dayplan execute(RefreshInputData refreshInputData) {
        Dayplan dayplan = refreshInputData.getDayplan();
        refreshAllBusiness(dayplan);

        this.dayPlanDataAccessObject.saveDayPlan(dayplan);
        return dayplan;
    }

    /**
     * Refreshes a single business entity within a day plan at the specified index.
     *
     * @param dayplan The day plan containing the business to refresh.
     * @param refreshIndex The index of the business within the day plan's list of businesses to refresh.
     */
    private void refreshOneBusiness(Dayplan dayplan, Integer refreshIndex) {
        Business prevBusiness = dayplan.getPlan().get(refreshIndex);
        String type = prevBusiness.getType();
        Map.Entry<Business, String> newBusinessEntry = refreshBusinessFactory.generateNewBusiness(dayplan, type);
        Business newBusiness = newBusinessEntry.getKey();
        String newBusinessID = newBusinessEntry.getValue();
        dayplan.replaceBusiness(refreshIndex, newBusiness, newBusinessID);
    }

    /**
     * Refreshes all business entities within a day plan.
     *
     * @param dayplan The day plan containing the businesses to be refreshed.
     */
    private void refreshAllBusiness(Dayplan dayplan) {
        int size = dayplan.getPlan().size();
        for (int i = 0; i < size; i++) {
            refreshOneBusiness(dayplan, i);
        }
    }
}
