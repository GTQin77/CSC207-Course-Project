//package app;
//
//import data_access.DayPlanDataAccessInterface;
//import data_access.DayPlanDataAccessObject;
//import entity.*;
//import interface_adapter.Dayplan.DayplanController;
//import interface_adapter.Dayplan.DayplanPresenter;
//import interface_adapter.Dayplan.DayplanViewModel;
//import interface_adapter.ViewManagerModel;
//import use_case.dayplanList.UserDayPlanInputBoundary;
//import use_case.dayplanList.UserDayPlanInteractor;
//import use_case.dayplanList.UserDayPlanOutputBoundary;
//import use_case.refresh.RefreshInteractor;
//import use_case.refresh.RefreshOutputBoundary;
//import view.BusinessDetailsView;
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
//     * @param dayplanViewModel View Model for dayplan info.
//     * @return a DayplanView
//     */
//    public static DayplanView create(DayplanViewModel dayplanViewModel, BusinessDetailsView businessDetailsView, ViewManagerModel viewManagerModel){
//        DayplanController dayplanController = createDayplanUseCase(viewManagerModel);
//        return new DayplanView(businessDetailsView, dayplanController);
//    }
//
//    /**
//     * Helper method for .create, instantiates Interactor and relevant factories.
//     * @return a DayplanView
//     */
//    public static DayplanController createDayplanUseCase(ViewManagerModel viewManagerModel){
//        DayPlanDataAccessInterface dayplanDAO = new DayPlanDataAccessObject();
//        ((DayPlanDataAccessObject) dayplanDAO).setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
//
//        RefreshOutputBoundary refreshOutputBoundary = new DayplanPresenter(viewManagerModel);
//
//        DayplanFactory dayplanFactory = new CommonDayplanFactory();
//        RefreshBusinessFactory refreshBusinessFactory = new CommonRefreshBusinessFactory();
//
//        UserDayPlanInputBoundary dayplanInteractor = new DayplanInteractor(dayplanDAO, refreshOutputBoundary, dayplanFactory, );
//
//        return new DayplanController(dayplanInteractor);
//    }
//}
