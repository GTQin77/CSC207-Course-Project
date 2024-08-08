package app;

import data_access.DayPlanDataAccessObject;
import entity.CommonDayplanFactory;
import entity.DayplanFactory;
import interface_adapter.Dayplan.DayplanViewModel;
import interface_adapter.DayplanInput.DayplanInputController;
import interface_adapter.DayplanInput.DayplanInputPresenter;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import services.UserService;
import use_case.dayplanList.UserDayPlanInputBoundary;
import use_case.dayplanList.UserDayPlanInteractor;
import use_case.dayplanList.UserDayPlanOutputBoundary;
import view.DayplanInputView;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class DayplanInputUseCaseFactory {
    private DayplanInputUseCaseFactory() {}

    public static DayplanInputView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel, DayplanViewModel dayplanViewModel, UserService userService) {
            DayplanInputController dayplanInputController = createDayplanInputUseCase(viewManagerModel, loginViewModel, dayplanInputViewModel, dayplanViewModel, userService);
            return new DayplanInputView(dayplanInputController, dayplanInputViewModel, viewManagerModel);
    }

    private static DayplanInputController createDayplanInputUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel, DayplanViewModel dayplanViewModel, UserService userService) {
        DayPlanDataAccessObject dayPlanDataAccessObject = new DayPlanDataAccessObject();
        dayPlanDataAccessObject.setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
        UserDayPlanOutputBoundary userDayPlanOutputBoundary = new DayplanInputPresenter(viewManagerModel, dayplanInputViewModel, dayplanViewModel);

        DayplanFactory dayplanFactory = new CommonDayplanFactory();
        UserDayPlanInputBoundary userDayPlanInteractor = new UserDayPlanInteractor(dayPlanDataAccessObject, userDayPlanOutputBoundary, dayplanFactory);

        return new DayplanInputController(userService, userDayPlanInteractor);

    }
}
