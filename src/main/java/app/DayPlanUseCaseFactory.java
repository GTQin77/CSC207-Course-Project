package app;

import data_access.DayPlanDataAccessInterface;
import data_access.DayPlanDataAccessObject;
import entity.Dayplan;
import entity.Business;
import entity.BusinessFactory;
import entity.DayplanFactory;
import entity.CommonDayplanFactory;
import interface_adapter.*;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInteractor;
import use_case.dayplanList.UserDayPlanOutputBoundary;
import view.DayplanView;

import java.io.IOException;

public class DayPlanUseCaseFactory {

    /**
     * Prevent instantiation.
     * Taken from PaulGries' LoginCleanArchitecture repo.
     */
    private DayPlanUseCaseFactory(){}

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
