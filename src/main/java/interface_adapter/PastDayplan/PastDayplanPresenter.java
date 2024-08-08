//package interface_adapter.PastDayplan;
//
//import interface_adapter.ViewManagerModel;
//import services.UserService;
//import use_case.previous_plan.PreviousPlanOutputBoundary;
//import use_case.previous_plan.PreviousPlanOutputData;
//import view.PastDayplanView;
//
//public class PastDayplanPresenter implements PreviousPlanOutputBoundary {
//    private ViewManagerModel viewManagerModel;
//    private UserService userService;
//
//    public PastDayplanPresenter(ViewManagerModel viewManagerModel, UserService userService) {
//        this.viewManagerModel = viewManagerModel;
//        this.userService = userService;
//    }
//
//    public void nagivateToBusinessDetails() {
//        viewManagerModel.setActiveView("BusinessDetails");
//        viewManagerModel.firePropertyChanged();
//    }
//
//    public void navigateToEditUserInfo() {
//        userService.setPrevView("PastDayplan");
//        viewManagerModel.setActiveView("EditUserInfo");
//        viewManagerModel.firePropertyChanged();
//    }
//
//    public void nagivateToDayplan() {
//        viewManagerModel.setActiveView("DayplanView");
//        viewManagerModel.firePropertyChanged();
//    }
//
//
//    @Override
//    public void preparePreviousPlanView(PreviousPlanOutputData previousPlanOutputData) {
//
//    }
//}
