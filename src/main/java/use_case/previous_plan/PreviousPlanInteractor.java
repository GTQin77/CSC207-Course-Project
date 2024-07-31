package use_case.previous_plan;

import data_access.DayPlanDataAccessObject;

public class PreviousPlanInteractor implements PreviousPlanInputBoundary {

    final DayPlanDataAccessObject dayplanDAO;
    final PreviousPlanOutputBoundary previousPlanPresenter;

    public PreviousPlanInteractor(DayPlanDataAccessObject dayplanDAO,
                                  PreviousPlanOutputBoundary previousPlan) {
        this.previousPlanPresenter = previousPlan;
        this.dayplanDAO = dayplanDAO;
    }

    @Override
    public void execute(PreviousPlanInputData previousPlanInputData) {
        ;;
        // nuances to consider, what happens to a user that already exists and their dayplans
        // i.e/ how does our system write existing users to the database?
    }
}
