package use_case.refresh;

import data_access.DayPlanDataAccessInterface;
import entity.DayplanFactory;
import entity.Dayplan;
import entity.Business;
import entity.refreshBusinessFactory;

import java.util.ArrayList;

public class RefreshInteractor implements RefreshInputBoundary{
    final DayPlanDataAccessInterface dayPlanDataAccessObject;
    final RefreshOutputBoundary refreshPresenter;
    final DayplanFactory dayplanFactory;
    final refreshBusinessFactory refreshBusinessFactory;


    public RefreshInteractor(DayPlanDataAccessInterface dayPlanDataAccessObject, RefreshOutputBoundary refreshPresenter, DayplanFactory dayplanFactory, refreshBusinessFactory businessFactory) {
        this.dayPlanDataAccessObject = dayPlanDataAccessObject;
        this.refreshPresenter = refreshPresenter;
        this.dayplanFactory = dayplanFactory;
        this.refreshBusinessFactory = businessFactory;
    }

    @Override
    public Dayplan execute(RefreshInputData refreshInputData) {
        Dayplan dayplan = refreshInputData.getDayplan();
        Boolean refreshAll = refreshInputData.getRefreshAll();
        Integer refreshIndex = refreshInputData.getRefreshIndex();

        // 1. get the names of businesses in the current dayplan:
        ArrayList<String> businessNames = dayplan.getBusinessNames();

        // 2. refresh the entire plan if needed
        if (refreshAll){
            refreshAllBusiness(dayplan);
        }
        else {
            refreshOneBusiness(dayplan, refreshIndex);
        }
        this.dayPlanDataAccessObject.saveDayPlan(dayplan);
        return dayplan;
    }

    private void refreshOneBusiness(Dayplan dayplan, Integer refreshIndex) {
        Business prevBusiness = dayplan.getPlan().get(refreshIndex);
        String type = prevBusiness.getType();
        Business newBusiness = refreshBusinessFactory.generateNewBusiness(dayplan, type);
        dayplan.replaceBusiness(refreshIndex, newBusiness);
    }

    private void refreshAllBusiness(Dayplan dayplan) {
        int size = dayplan.getPlan().size();
        for (int i = 0; i < size; i++) {
            refreshOneBusiness(dayplan, i);
        }
    }
}
