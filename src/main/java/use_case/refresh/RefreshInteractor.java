package use_case.refresh;

import data_access.DayPlanDataAccessInterface;
import entity.BusinessFactory;
import entity.DayplanFactory;
import entity.Dayplan;

import java.util.ArrayList;

public class RefreshInteractor implements RefreshInputBoundary{
    final DayPlanDataAccessInterface dayPlanDataAccessObject;
    final RefreshOutputBoundary refreshPresenter;
    final DayplanFactory dayplanFactory;
    final BusinessFactory businessFactory;

    public RefreshInteractor(DayPlanDataAccessInterface dayPlanDataAccessObject, RefreshOutputBoundary refreshPresenter, DayplanFactory dayplanFactory, BusinessFactory businessFactory) {
        this.dayPlanDataAccessObject = dayPlanDataAccessObject;
        this.refreshPresenter = refreshPresenter;
        this.dayplanFactory = dayplanFactory;
        this.businessFactory = businessFactory;
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
            refreshAllBusiness(dayplan, businessNames);
        }
        else {
            refreshOneBusiness(dayplan, refreshIndex, businessNames);
        }
        this.dayPlanDataAccessObject.saveDayPlan(dayplan);
        return dayplan;
    }

    private void refreshOneBusiness(Dayplan dayplan, Integer refreshIndex, ArrayList<String> businessNames) {

    }

    private void refreshAllBusiness(Dayplan dayplan, ArrayList<String> businessNames) {
    }

    private void uniqueBusiness(Dayplan dayplan, String ){

    }
}
