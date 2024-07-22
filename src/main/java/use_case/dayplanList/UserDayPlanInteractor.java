package use_case.dayplanList;

import api.OpenAI;
import api.YelpFusion;
import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessInterface;
import entity.*;
import use_case.user.UserSignupOutputBoundary;
// NOTE TO SELF: Should import a DayplanFactory interface instead, no time :(

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDayPlanInteractor implements UserDayPlanInputBoundary{

    final DayPlanDataAccessInterface dayPlanDataAccessObject;
    final UserDayPlanOutputBoundary dayplanPresenter;

    public DayplanFactory getDayplanFactory() {
        return dayplanFactory;
    }

    public void setDayplanFactory(DayplanFactory dayplanFactory) {
        this.dayplanFactory = dayplanFactory;
    }

    private DayplanFactory dayplanFactory;

    public UserDayPlanInteractor(DayPlanDataAccessInterface dayPlanDataAccessInterface,
                                UserDayPlanOutputBoundary userDayPlanOutputBoundary,
                                DayplanFactory dayplanFactory) {
        this.dayPlanDataAccessObject = dayPlanDataAccessInterface;
        this.dayplanPresenter = userDayPlanOutputBoundary;
        this.dayplanFactory = dayplanFactory;
    }

    /**
     * Interactor method that creates a dayplan, creates all API objects, and factories necessary for flow.
     * @param input UserDayPlanInputData.
     */
    @Override
    public void execute(UserDayPlanInputData input) {
        // 1. Process Input Data
        ArrayList<Double> location = new ArrayList<Double>();
        for (int i = 0; i < input.getLocation().size(); i++){
            Double coord = Double.valueOf(input.getLocation().get(i));
            location.add(coord);
        }
        // 2. Initialize API objects and set our attributes to them
        OpenAI openAI = new OpenAI();
        YelpFusion yelpFusion = new YelpFusion();

        // 3. Initialize our DayplanFactory trait using the API objects we made previously
        CommonDayplanFactory commonDayplanFactory = new CommonDayplanFactory();
        BusinessFactory businessFactory = new YelpBusinessFactory(yelpFusion);
        commonDayplanFactory.setOpenApi(openAI);
        commonDayplanFactory.setYelpApi(yelpFusion);
        commonDayplanFactory.setBusinessFactory(businessFactory);

        // 4. Create a new Dayplan object using factories
        Dayplan dayplan = commonDayplanFactory.create(input.getUser(), location, input.getCity(), input.getNumMeals(),
                input.getNumActivities(), input.getDescription());

        // 5. Write new Dayplan to database using DAO
        this.dayPlanDataAccessObject.saveDayPlan(dayplan);
        // 6. Prepare Output Data
        UserDayPlanOutputData outputData = new UserDayPlanOutputData(dayplan);
        this.dayplanPresenter.prepareDayplanView(outputData);
    }
}
