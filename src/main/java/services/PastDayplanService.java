package services;

import entity.PreviousPlan;
import entity.User;
import use_case.previous_plan.PreviousPlanInputData;
import use_case.previous_plan.PreviousPlanInteractor;

public class PastDayplanService {
    private PreviousPlanInteractor previousPlanInteractor;


    public PastDayplanService(PreviousPlanInteractor previousPlanInteractor) {
        this.previousPlanInteractor = previousPlanInteractor;
    }

    public PreviousPlan getPrevPlan(PreviousPlanInputData previousPlanInputData) {
        PreviousPlan previousPlan = previousPlanInteractor.execute(previousPlanInputData);
        return  previousPlan;
    }
}
