package interface_adapter.Dayplan;

import entity.Business;
import entity.Dayplan;
import services.RefreshService;
import services.UserService;
import use_case.refresh.RefreshInputData;
import view.DayplanView;
import view.IDayplanView;

import java.util.ArrayList;


public class DayplanController {
    private DayplanViewModel viewModel;
    private UserService userService;
    private RefreshService refreshService;
    private IDayplanView view;


    public DayplanController(DayplanViewModel viewModel, UserService userService, RefreshService refreshService) {
        this.viewModel = viewModel;
        this.userService = userService;
        this.refreshService = refreshService;
    }

    /**
     * Handles the refresh operation for the dayplan.
     */
    public void handleRefresh() {
        Dayplan dayplan = userService.getDayplan();
        RefreshInputData refreshInputData = new RefreshInputData(dayplan);
        Dayplan refreshedDayplan = refreshService.refreshDayplan(refreshInputData);
        ArrayList<Business> updatedBusinesses = refreshedDayplan.getPlan();
        userService.setDayplan(refreshedDayplan);
        view.updateBusinessButtons(updatedBusinesses);
    }

    /**
     * Sets the view associated with this controller.
     *
     * @param view the day plan view to be associated with this controller.
     */
    public void setView(DayplanView view) {
        this.view = view;
    }
}
