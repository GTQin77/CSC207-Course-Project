//package interface_adapter.PastDayplan;
//
//import entity.PreviousBusiness;
//import entity.PreviousPlan;
//import interface_adapter.ViewModel;
//
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//import java.util.ArrayList;
//
//public class PastDayplanViewModel extends ViewModel {
//    private PropertyChangeSupport support;
//
//    public PreviousPlan getPastPlan() {
//        return pastPlan;
//    }
//
//    public void setPastPlan(PreviousPlan pastPlan) {
//        this.pastPlan = pastPlan;
//        firePropertyChanged();
//    }
//
//    private PreviousPlan pastPlan;
//
//    public PastDayplanViewModel() {
//        super("PastDayplan");
//        this.support = new PropertyChangeSupport(this);
//    }
////
////    public void updatePastDayplan(PreviousPlan previousPlan) {
////
////
////    }
//
//
//    @Override
//    public void firePropertyChanged() {
//        // This method could be used to notify all listeners of a generic change.
//        support.firePropertyChange("previousPlanUpdated", null, this);
//    }
//
//    @Override
//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        support.addPropertyChangeListener(listener);
//    }
//
//    public void getPreviousDayplan(PreviousPlan previousPlan) {
//        setPastPlan(previousPlan);
//
//    }
//}
