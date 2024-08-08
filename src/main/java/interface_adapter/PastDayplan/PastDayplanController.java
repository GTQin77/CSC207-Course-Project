//package interface_adapter.PastDayplan;
//
//import entity.PreviousPlan;
//import entity.User;
//import services.PastDayplanService;
//import services.UserService;
//import use_case.previous_plan.PreviousPlanInputBoundary;
//import use_case.previous_plan.PreviousPlanInputData;
//import view.PastDayplanView;
//
//public class PastDayplanController {
//
//    private UserService userService;
//    private PastDayplanViewModel pastDayplanViewModel;
//    private PastDayplanService pastDayplanService;
//    private PastDayplanView view;
//
//
//    public PastDayplanController(UserService userService, PastDayplanViewModel pastDayplanViewModel, PastDayplanService pastDayplanService) {
//
//        this.userService = userService;
//        this.pastDayplanViewModel = pastDayplanViewModel;
//        this.pastDayplanService = pastDayplanService;
//    }
//
//    public PreviousPlan execute() {
//        User user = userService.getCurrentUser();
//        PreviousPlanInputData previousPlanInputData = new PreviousPlanInputData(user);
//
//        PreviousPlan previousPlan = pastDayplanService.getPrevPlan(previousPlanInputData);
//        userService.setPreviousPlan(previousPlan);
//        pastDayplanViewModel.setPastPlan(previousPlan);
//        return previousPlan;
//    }
//
//    public void setView(PastDayplanView pastDayplanView) {
//        this.view = view;
//    }
//
////    public void loadPreviousDayplan() {
////        PreviousPlan previousPlan = userService.getPreviousPlan();
////        pastDayplanViewModel.setPastPlan(previousPlan);
////        view.displayDayplan(pastDayplanViewModel);
////    }
//}
