package interface_adapter.DayplanInput;

import entity.Dayplan;
import entity.User;
import interface_adapter.Login.LoginController;
import interface_adapter.Signup.SignupController;
import services.UserService;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInputData;

import java.util.ArrayList;

public class DayplanInputController {
    private UserService userService;

    final UserDayPlanInputBoundary userDayPlanInteractor;
//    private final LoginController loginController;
//    private final SignupController signupController;

    public DayplanInputController(UserService userService, UserDayPlanInputBoundary userDayPlanInteractor) {
        this.userService = userService;
        this.userDayPlanInteractor = userDayPlanInteractor;
//        this.loginController = loginController;
//        this.signupController = signupController;
    }

    /**
     *
     */
    public void execute(String city, int numMeals, int numActivities, String description){
//        User user = loginController.getUser();
//        ArrayList<String> coordinates = signupController.getLocation();
        User user = userService.getCurrentUser();

        ArrayList<Double> coordinates = user.getLocation();
        String location = coordinates.getFirst() + "," + coordinates.getLast();

        UserDayPlanInputData userDayPlanInputData = new UserDayPlanInputData(
                user, location, city, numMeals, numActivities, description);

        Dayplan dayplan = userDayPlanInteractor.execute(userDayPlanInputData);
        userService.setDayplan(dayplan);
    }

}
