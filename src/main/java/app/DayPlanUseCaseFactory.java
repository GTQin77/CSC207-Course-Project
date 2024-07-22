package app;

import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessObject;
import entity.*;
import interface_adapter.*;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInteractor;
import use_case.dayplanList.UserDayPlanOutputBoundary;
import use_case.user.UserSignupOutputData;
import view.DayplanView;

import java.io.IOException;

public class DayPlanUseCaseFactory {

    /**
     * Prevent instantiation.
     * Taken from PaulGries' LoginCleanArchitecture repo.
     */
    private DayPlanUseCaseFactory(){}

    public static DayplanView create(User user, DayplanViewModel dayplanViewModel){
        DayplanController dayplanController = createDayplanUseCase(dayplanViewModel);
        return new DayplanView(dayplanController, dayplanViewModel, user);
    }

    public static DayplanController createDayplanUseCase(DayplanViewModel dayplanViewModel){
        DayPlanDataAccessInterface dayplanDAO = new DayPlanDataAccessObject();
        ((DayPlanDataAccessObject) dayplanDAO).setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");

        UserDayPlanOutputBoundary dayplanOutputBoundary = new DayplanPresenter(dayplanViewModel);

        DayplanFactory dayplanFactory = new CommonDayplanFactory();

        UserDayPlanInputBoundary dayplanInteractor = new UserDayPlanInteractor(dayplanDAO,
                dayplanOutputBoundary, dayplanFactory);

        return new DayplanController(dayplanInteractor);
    }
}
