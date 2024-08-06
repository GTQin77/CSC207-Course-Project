//package app;
//
//import data_access.DayPlanDataAccessInterface;
//import data_access.DayPlanDataAccessObject;
//import entity.*;
//import interface_adapter.Dayplan.DayplanController;
//import interface_adapter.Dayplan.DayplanPresenter;
//import interface_adapter.Dayplan.DayplanViewModel;
//import use_case.dayplanList.UserDayPlanInputBoundary;
//import use_case.dayplanList.UserDayPlanInteractor;
//import use_case.dayplanList.UserDayPlanOutputBoundary;
//import view.DayplanView;
//
//public class DayPlanUseCaseFactory {
//
//    /**
//     * Prevent instantiation.
//     * Class Structure based off of from PaulGries' LoginCleanArchitecture repo, https://github.com/paulgries/LoginCleanArchitecture
//     */
//    private DayPlanUseCaseFactory(){}
//
//    /**
//     * Create method that controls flow of Use Case.
//     * @param user User object that the dayplan should belong to, carried over from User SignUp case.
//     * @param dayplanViewModel View Model for dayplan info.
//     * @return a DayplanView
//     */
//    public static DayplanView create(User user, DayplanViewModel dayplanViewModel){
//        DayplanController dayplanController = createDayplanUseCase(dayplanViewModel);
//        return new DayplanView(dayplanController, dayplanViewModel, user);
//    }
//
//    /**
//     * Helper method for .create, instantiates Interactor and relevant factories.
//     * @param dayplanViewModel View model for dayplan info.
//     * @return a DayplanView
//     */
//    public static DayplanController createDayplanUseCase(DayplanViewModel dayplanViewModel){
//        DayPlanDataAccessInterface dayplanDAO = new DayPlanDataAccessObject();
//        ((DayPlanDataAccessObject) dayplanDAO).setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
//
//        UserDayPlanOutputBoundary dayplanOutputBoundary = new DayplanPresenter(dayplanViewModel);
//
//        DayplanFactory dayplanFactory = new CommonDayplanFactory();
//
//        UserDayPlanInputBoundary dayplanInteractor = new UserDayPlanInteractor(dayplanDAO,
//                dayplanOutputBoundary, dayplanFactory);
//
//        return new DayplanController(dayplanInteractor);
//    }
//}
