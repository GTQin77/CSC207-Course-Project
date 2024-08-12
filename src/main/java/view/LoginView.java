package view;

import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * Login view of the program.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for LoginView on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final LoginController loginController;
    private final DayplanInputViewModel dayplanInputViewModel;


    /**
     * The username chosen by the user
     */
    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    /**
     * The password
     */
    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton signUp;

    /**
     * A window with a title and a JButton.
     */
    public LoginView(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginController loginController, DayplanInputViewModel dayplanInputViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginController = loginController;
        this.dayplanInputViewModel = dayplanInputViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        signUp = new JButton(loginViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            try {
                                loginController.execute(usernameInputField.getText(),
                                        String.valueOf(passwordInputField.getPassword()));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(LoginView.this, e.getMessage());
                            }
                        }
                    }
                }
        );
        signUp.addActionListener(this);

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(signUp)) {
            this.viewManagerModel.setActiveView(signupViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }
    }

}