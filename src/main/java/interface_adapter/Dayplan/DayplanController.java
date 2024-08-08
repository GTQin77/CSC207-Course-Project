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
