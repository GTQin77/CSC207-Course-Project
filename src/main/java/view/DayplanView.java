package view;

import interface_adapter.Dayplan.DayplanController;
import interface_adapter.Dayplan.DayplanViewModel;
import entity.User;

import java.util.ArrayList;

public class DayplanView {

    private final DayplanController dayplanController;
    private final DayplanViewModel dayplanViewModel;

    public DayplanView(DayplanController dayplanController, DayplanViewModel dayplanViewModel, User user) {
        this.dayplanController = dayplanController;
        this.dayplanViewModel = dayplanViewModel;

        //automatically get user from signup (for now, login for later)

        dayplanViewModel.setUser(user);
        User userObject = dayplanViewModel.getUser();
        String userName = user.getUserName();
        ArrayList<Double> userLocation = user.getLocation();

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

        dayplanController.execute(user, userLocation.toString(), userCity, userNumMeals, userNumActivities, userDescription);
    }
}
