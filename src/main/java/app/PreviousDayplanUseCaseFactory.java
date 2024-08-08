//package app;
//
//import data_access.PrevPlanDataAccessObject;
//import entity.CommonPreviousPlanFactory;
//import entity.PreviousPlanFactory;
//import entity.User;
//import interface_adapter.PastDayplan.PastDayplanController;
//import interface_adapter.PastDayplan.PastDayplanPresenter;
//import interface_adapter.PastDayplan.PastDayplanViewModel;
//import interface_adapter.ViewManagerModel;
//import services.UserService;
//import use_case.previous_plan.PreviousPlanInputBoundary;
//import use_case.previous_plan.PreviousPlanInteractor;
//import use_case.previous_plan.PreviousPlanOutputBoundary;
//import view.PastDayplanView;
//
//public class PreviousDayplanUseCaseFactory {
//    private PreviousDayplanUseCaseFactory() {}
//
//    public static PastDayplanView create(PastDayplanPresenter pastDayplanPresenter, UserService userService, ViewManagerModel viewManagerModel, PastDayplanViewModel pastDayplanViewModel) {
//        PastDayplanController pastDayplanController = createPreviousDayplanUseCase(viewManagerModel, userService, pastDayplanViewModel);
//        return new PastDayplanView(pastDayplanPresenter, userService, pastDayplanController, viewManagerModel);
//    }
//
//    private static PastDayplanController createPreviousDayplanUseCase(ViewManagerModel viewManagerModel, UserService userService, PastDayplanViewModel pastDayplanViewModel) {
//        PrevPlanDataAccessObject prevPlanDataAccessObject = new PrevPlanDataAccessObject();
//        prevPlanDataAccessObject.setcsvPathAndcsvFile("./src/main/resources/DayplanDatabase.csv");
//
//        User user = userService.getCurrentUser();
//
//        PreviousPlanOutputBoundary previousPlanOutputBoundary = new PastDayplanPresenter(viewManagerModel, userService);
//        PreviousPlanFactory previousPlanFactory = new CommonPreviousPlanFactory();
//
//        PreviousPlanInputBoundary previousPlanInteractor = new PreviousPlanInteractor(prevPlanDataAccessObject, previousPlanOutputBoundary, previousPlanFactory);
//
//        return new PastDayplanController(userService, pastDayplanViewModel,previousPlanInteractor);
//    }
//}
