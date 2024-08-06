package view;

import interface_adapter.Login.LoginViewManagerModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupViewManagerModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewModel;
import interface_adapter.Welcome.WelcomeViewManagerModel;
import interface_adapter.Welcome.WelcomeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel implements ActionListener {
    public final String viewName = "welcome";

    private final WelcomeViewModel welcomeViewModel;
    private WelcomeViewManagerModel welcomeViewManagerModel;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final JButton logIn;
    private final JButton signUp;

    /**
     * A window with a title and a JButton.
     */
    public WelcomeView(WelcomeViewModel welcomeViewModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, WelcomeViewManagerModel welcomeViewManagerModel) {
        this.welcomeViewModel = welcomeViewModel;
        this.welcomeViewManagerModel = welcomeViewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;

        JLabel title = new JLabel("Welcome Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signUp = new JButton(welcomeViewModel.SIGNUP_WELCOME_BUTTON_LABEL);
        buttons.add(signUp);

        logIn = new JButton(welcomeViewModel.LOGIN_WELCOME_BUTTON_LABEL);
        buttons.add(logIn);


        logIn.addActionListener(this);
        signUp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            this.welcomeViewManagerModel.setActiveView(loginViewModel.getViewName());
            this.welcomeViewManagerModel.firePropertyChanged();
        } else if (evt.getSource().equals(signUp)) {
            this.welcomeViewManagerModel.setActiveView(signupViewModel.getViewName());
            this.welcomeViewManagerModel.firePropertyChanged();
        }
    }

}
