package view;

import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Signup.SignupViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.Welcome.WelcomeViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Map.Entry;

/**
 * View manager of the program.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for ViewManager on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private WelcomeViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, WelcomeViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}