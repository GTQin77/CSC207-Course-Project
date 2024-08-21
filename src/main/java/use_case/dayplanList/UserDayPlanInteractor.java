package use_case.dayplanList;

import api.OpenAI;
import api.YelpFusion;
import data_access.DayPlanDataAccessInterface;
import entity.*;

import java.util.ArrayList;

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
     *
     * @param input UserDayPlanInputData.
     * @return a dayplan with the given input data.
     */
    @Override
    public Dayplan execute(UserDayPlanInputData input) {
        ArrayList<Double> location = new ArrayList<Double>();
        for (int i = 0; i < input.getLocation().size(); i++){
            Double coord = Double.valueOf(input.getLocation().get(i));
            location.add(coord);
        }
        OpenAI openAI = new OpenAI();
        YelpFusion yelpFusion = new YelpFusion();

        CommonDayplanFactory commonDayplanFactory = new CommonDayplanFactory();
        BusinessFactory businessFactory = new YelpBusinessFactory(yelpFusion);
        commonDayplanFactory.setOpenApi(openAI);
        commonDayplanFactory.setYelpApi(yelpFusion);
        commonDayplanFactory.setBusinessFactory(businessFactory);

        // 4. Create a new Dayplan object using factories
        Dayplan dayplan = commonDayplanFactory.create(input.getUser(), location, input.getCity(), input.getNumMeals(),
                input.getNumActivities(), input.getDescription());

        this.dayPlanDataAccessObject.saveDayPlan(dayplan);
        UserDayPlanOutputData outputData = new UserDayPlanOutputData(dayplan);
        this.dayplanPresenter.prepareDayplanView(outputData);
        return dayplan;
    }
}
