//package interface_adapter.DayplanInput;
//
//import interface_adapter.Dayplan.DayplanViewModel;
//import use_case.dayplanList.UserDayPlanOutputBoundary;
//import use_case.dayplanList.UserDayPlanOutputData;
//
//public class DayplanInputPresenter implements UserDayPlanOutputBoundary {
//    private final DayplanInputViewModel dayplanInputViewModel;
//    private final DayplanViewModel dayplanViewModel;
//    private DayplanInputViewManagerModel dayplanInputViewManagerModel;
//
//    public DayplanInputPresenter(DayplanInputViewManagerModel dayplanInputViewManagerModel, DayplanInputViewModel dayplanInputViewModel, DayplanViewModel dayplanViewModel) {
//        this.dayplanInputViewManagerModel = dayplanInputViewManagerModel;
//        this.dayplanInputViewModel = dayplanInputViewModel;
//        this.dayplanViewModel = dayplanViewModel;
//    }
//
//    /**
//     * This switch to dayplan view.
//     * @param data response from the UserDayPlanOutputData.
//     */
//    @Override
//    public void prepareDayplanView(UserDayPlanOutputData data) {
//        DayplanInputState dayplanInputState = dayplanInputViewModel.getState();
//        DayplanState dayplanState = dayplanViewModel.getState();
//        dayplanState.setDayplan(data.getDayplan());
//
//        this.dayplanViewModel.setState(dayplanState);
//        dayplanViewModel.firePropertyChanged();
//        dayplanInputViewManagerModel.setActiveView(dayplanViewModel.getViewName());
//        dayplanInputViewManagerModel.firePropertyChanged();
//    }
//}
