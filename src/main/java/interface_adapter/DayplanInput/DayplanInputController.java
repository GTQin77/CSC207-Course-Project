package interface_adapter.DayplanInput;

import entity.Dayplan;
import entity.User;
import interface_adapter.Login.LoginController;
import interface_adapter.Signup.SignupController;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;

import java.util.ArrayList;

public class DayplanInputController {
    private Dayplan dayplan;

    public Dayplan getDayplan() {return dayplan;}
    public void setDayplan(Dayplan dayplan) {this.dayplan = dayplan;}

    final UserDayPlanInputBoundary userDayPlanInteractor;
    private final LoginController loginController;
    private final SignupController signupController;

    public DayplanInputController(UserDayPlanInputBoundary userDayPlanInteractor, LoginController loginController, SignupController signupController) {
        this.userDayPlanInteractor = userDayPlanInteractor;
        this.loginController = loginController;
        this.signupController = signupController;
    }

    /**
     *
     */
    public void execute(String city, int numMeals, int numActivities, String description){
        User user = loginController.getUser();
        ArrayList<String> coordinates = signupController.getLocation();
        String latitude = coordinates.get(0);
        String longitude = coordinates.get(1);
        String location = latitude + ", " + longitude;

        UserDayPlanInputData userDayPlanInputData = new UserDayPlanInputData(
                user, location, city, numMeals, numActivities, description);

        Dayplan dayplan = userDayPlanInteractor.execute(userDayPlanInputData);

        this.setDayplan(dayplan);
    }

}
