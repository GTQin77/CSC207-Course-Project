//package interface_adapter.Welcome;
//
//import interface_adapter.Signup.SignupState;
//import interface_adapter.ViewModel;
//import view.WelcomeView;
//
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//
//public class WelcomeViewModel extends ViewModel {
//    public final String TITLE_LABEL = "Welcome View";
//    public final String LOGIN_WELCOME_BUTTON_LABEL = "Login";
//    public final String SIGNUP_WELCOME_BUTTON_LABEL = "Sign Up";
//    private WelcomeState state = new WelcomeState();
//
//    public WelcomeViewModel(){
//        super("welcome");
//    }
//
//    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
//
//
//    @Override
//    public void firePropertyChanged() {
//        support.firePropertyChange("state", null, this.state);
//    }
//
//    @Override
//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        support.addPropertyChangeListener(listener);
//    }
//
//    public void setState(WelcomeState state) {
//        this.state = state;
//    }
//
//    public WelcomeState getState() {
//        return state;
//    }
//}
