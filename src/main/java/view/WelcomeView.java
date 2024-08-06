package view;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewManagerModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewManagerModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JPanel implements ActionListener {
    public final String viewName = "welcome";

    private final ViewModel viewModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private SignupViewManagerModel signupViewManagerModel;
    private LoginViewManagerModel loginViewManagerModel;
    private final JButton logIn = new JButton("Log in");
    private final JButton signUp = new JButton("Sign up");

    /**
     * A window with a title and a JButton.
     */
    public WelcomeView(ViewModel viewModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewModel = viewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;

        JLabel title = new JLabel("Welcome Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(signUp);

        logIn.addActionListener(this);
        signUp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            LoginState loginState = loginViewModel.getState();
            this.loginViewModel.setState(loginState);
            loginViewModel.firePropertyChanged();
            loginViewManagerModel.setActiveView(loginViewModel.getViewName());
            loginViewManagerModel.firePropertyChanged();
        } else if (evt.getSource().equals(signUp)) {
            SignupState signupState = signupViewModel.getState();
            this.signupViewModel.setState(signupState);
            signupViewModel.firePropertyChanged();
            signupViewManagerModel.setActiveView(signupViewModel.getViewName());
            signupViewManagerModel.firePropertyChanged();
        }
    }

}
