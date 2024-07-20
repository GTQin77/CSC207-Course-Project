package interface_adapter;

import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;

public class DayplanController {

    final UserDayPlanInputBoundary userDayPlanUseCaseInteractor;

    public DayplanController(UserDayPlanInputBoundary userDayPlanUseCaseInteractor) {
        this.userDayPlanUseCaseInteractor = userDayPlanUseCaseInteractor;
    }

    public void execute(String userInput) {
        UserDayPlanInputData inputData = new UserDayPlanInputData(userInput);

        userDayPlanUseCaseInteractor.execute(inputData);
    }

}
