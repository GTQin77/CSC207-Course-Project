package use_case.previous_plan;

import data_access.PrevPlanDataAccessObject;
import entity.User;

public class PreviousPlanInteractor implements PreviousPlanInputBoundary {

    final PrevPlanDataAccessObject previousPlanDAO;
    final PreviousPlanOutputBoundary previousPlanPresenter;

    public PreviousPlanInteractor(PrevPlanDataAccessObject dayplanDAO,
                                  PreviousPlanOutputBoundary previousPlan) {
        this.previousPlanPresenter = previousPlan;
        this.previousPlanDAO = dayplanDAO;
    }

    @Override
    public void execute(PreviousPlanInputData previousPlanInputData) {
        User currentUser = previousPlanInputData.getUser();

    }
}
