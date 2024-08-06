package use_case.previous_plan;

import data_access.PrevPlanDataAccessObject;
import entity.CommonPreviousPlanFactory;
import entity.PreviousPlan;
import entity.PreviousPlanFactory;
import entity.User;

public class PreviousPlanInteractor implements PreviousPlanInputBoundary {

    final PrevPlanDataAccessObject previousPlanDAO;
    final PreviousPlanOutputBoundary previousPlanPresenter;
    final PreviousPlanFactory previousPlanFactory;

    public PreviousPlanInteractor(PrevPlanDataAccessObject dayplanDAO,
                                  PreviousPlanOutputBoundary previousPlan,
                                  PreviousPlanFactory previousPlanFactory) {
        this.previousPlanPresenter = previousPlan;
        this.previousPlanDAO = dayplanDAO;
        this.previousPlanFactory = previousPlanFactory;
    }

    @Override
    public PreviousPlan execute(PreviousPlanInputData previousPlanInputData) {
        User currentUser = previousPlanInputData.getUser();
        String previousDayplanString = this.previousPlanDAO.getPreviousDayplan(currentUser);
        CommonPreviousPlanFactory previousPlanFactory = new CommonPreviousPlanFactory();

        PreviousPlan previousPlan = previousPlanFactory.convertDayplan(previousDayplanString);

        PreviousPlanOutputData outputData = new PreviousPlanOutputData(previousPlan);
        this.previousPlanPresenter.preparePreviousPlanView(outputData);
        return previousPlan;
    }
}
