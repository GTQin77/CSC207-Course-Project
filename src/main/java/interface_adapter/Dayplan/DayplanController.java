package interface_adapter.Dayplan;

import entity.Business;
import entity.Dayplan;
import services.UserService;
import use_case.refresh.RefreshInputBoundary;
import use_case.refresh.RefreshInputData;
import view.DayplanView;
import view.ViewManager;

import java.util.ArrayList;


public class DayplanController {
    private DayplanViewModel viewModel;
    private ViewManager viewManager;
    private UserService userService;
    private DayplanView view;
    final RefreshInputBoundary refreshInteractor;

    public DayplanController(DayplanViewModel viewModel, ViewManager viewManager, UserService userService, RefreshInputBoundary refreshInteractor) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
        this.userService = userService;
        this.refreshInteractor = refreshInteractor;
    }

    public void loadBusinesses() {
        Dayplan dayplan = userService.getDayplan();
        ArrayList<Business> businesses = dayplan.getPlan();
        viewModel.setBusinesses(businesses);
    }

    public void handleRefresh() {
        Dayplan dayplan = userService.getDayplan();
        RefreshInputData refreshInputData = new RefreshInputData(dayplan);
        Dayplan refreshedDayplan = refreshInteractor.execute(refreshInputData);
        ArrayList<Business> updatedBusinesses = refreshedDayplan.getPlan();
        userService.setDayplan(refreshedDayplan);
        view.updateBusinessButtons(updatedBusinesses);
    }
}


//public class DayplanController {
//
//    final UserDayPlanInputBoundary userDayPlanUseCaseInteractor;
//
//    public DayplanController(UserDayPlanInputBoundary userDayPlanUseCaseInteractor) {
//        this.userDayPlanUseCaseInteractor = userDayPlanUseCaseInteractor;
//    }
//
//    /**
//     * Controller method execute that calls on Interactor.
//     * @param user a User object.
//     * @param location from input
//     * @param city from input
//     * @param numMeals from input
//     * @param numActivities from input
//     * @param description from input
//     */
//    public void execute(User user, String location, String city, int numMeals, int numActivities, String description) {
//        UserDayPlanInputData inputData = new UserDayPlanInputData(user, location, city, numMeals, numActivities, description);
//
//        userDayPlanUseCaseInteractor.execute(inputData);
//    }
//
//}
