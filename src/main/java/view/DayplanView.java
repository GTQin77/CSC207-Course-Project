package view;

import interface_adapter.DayplanController;
import interface_adapter.DayplanViewModel;
import use_case.user.UserSignupOutputData;
import entity.User;

import java.util.ArrayList;

public class DayplanView {

    private final DayplanController dayplanController;
    private final DayplanViewModel dayplanViewModel;

    public DayplanView(DayplanController dayplanController, DayplanViewModel dayplanViewModel, UserSignupOutputData user) {
        this.dayplanController = dayplanController;
        this.dayplanViewModel = dayplanViewModel;

        //automatically get user from signup (for now, login for later)

        dayplanViewModel.setUser(user);
        User userObject = dayplanViewModel.getUser();
        String userName = userObject.getUserName();
        ArrayList<Double> userLocation = userObject.getLocation();

        //input user city
        dayplanViewModel.setUserCity();
        String userCity = dayplanViewModel.getUserCity();

        //input user number of meals
        dayplanViewModel.setUserNumMeals();
        int userNumMeals = dayplanViewModel.getUserNumMeals();

        //input user number of activities
        dayplanViewModel.setUserNumActivities();
        int userNumActivities = dayplanViewModel.getUserNumActivities();

        //input user description
        dayplanViewModel.setUserDescription();
        String userDescription = dayplanViewModel.getUserDescription();

        dayplanController.execute(userName, userLocation.toString(), userCity, userNumMeals, userNumActivities, userDescription);
    }
}
