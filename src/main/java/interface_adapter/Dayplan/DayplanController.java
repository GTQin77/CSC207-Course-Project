package interface_adapter.Dayplan;

import entity.User;
import services.UserService;
import use_case.business_detail.BusinessInputBoundary;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;
import use_case.edit_info.EditInfoInputBoundary;
import use_case.edit_info.EditInfoInputData;
import use_case.refresh.RefreshInputBoundary;
import entity.Dayplan;
import use_case.refresh.RefreshInputData;
import use_case.refresh.RefreshInteractor;

import java.util.ArrayList;

public class DayplanController {

    ArrayList<String> businessNames = new ArrayList<>();
    int dayplanLength;
    final RefreshInputBoundary refreshInteractor;
    final BusinessInputBoundary businessInteractor;
    UserService userService;

    public DayplanController(RefreshInputBoundary refreshInteractor,
                             BusinessInputBoundary businessInteractor,
                             UserService userService) {
        this.refreshInteractor = refreshInteractor;
        this.userService = userService;
        this.businessInteractor = businessInteractor;
    }
    /**
     * Refreshes the current Dayplan assuming that User would like to refresh all.
     * */
    public void refresh() {
        Dayplan currentDayplan = this.userService.getDayplan();
        RefreshInputData refreshInputData = new RefreshInputData(currentDayplan, true, 0);
        Dayplan refreshedPlan = this.refreshInteractor.execute(refreshInputData);
        this.userService.setDayplan(refreshedPlan);
    }

    public void dayplanInfo() {
        ;;
    }

    public int getDayplanLength() {
        this.dayplanLength = this.userService.getDayplan().getPlan().size();
        return this.dayplanLength;
    }

    public ArrayList<String> getBusinessNames() {
        this.businessNames = this.userService.getDayplan().getBusinessNames();
        return this.businessNames;
    }

}
