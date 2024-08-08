package view;

import interface_adapter.EditInfo.EditInfoController;
import interface_adapter.EditInfo.EditInfoViewModel;
import entity.User;
import interface_adapter.EditInfo.EditInfoState;
import interface_adapter.ViewManagerModel;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



public class EditInfoView extends JPanel implements PropertyChangeListener{
    public final String viewName = "EditUserInfo";
    private final EditInfoViewModel editInfoViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField locationInputField = new JTextField(15);
    private final EditInfoController editInfoController;
    private UserService userService;


    private final JButton submitChanges;
    private final JButton cancel;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public EditInfoView(EditInfoController controller, EditInfoViewModel editInfoViewModel, ViewManagerModel viewManagerModel, UserService userService) {
        this.editInfoController = controller;
        this.editInfoViewModel = editInfoViewModel;
        this.viewManagerModel = viewManagerModel;

        editInfoViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(editInfoViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(editInfoViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(editInfoViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(editInfoViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel(editInfoViewModel.LOCATION_LABEL), locationInputField);


        JPanel buttons = new JPanel();
        submitChanges = new JButton(editInfoViewModel.SUBMIT_CHANGES_BUTTON_LABEL);
        buttons.add(submitChanges);

        cancel = new JButton(editInfoViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);


        submitChanges.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(submitChanges)) {
                            try {
                                editInfoController.execute(usernameInputField.getText(),
                                        String.valueOf(passwordInputField.getPassword()),
                                        String.valueOf(repeatPasswordInputField.getPassword()),
                                        locationInputField.getText(), userService);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(EditInfoView.this, e.getMessage());
                            }
                        }
                    }
                }
        );
        cancel.addActionListener(e -> {
            viewManagerModel.setActiveView(userService.getPrevView());
            viewManagerModel.firePropertyChanged();
        });

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditInfoState currentState = editInfoViewModel.getState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        editInfoViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(locationInfo);
        this.add(buttons);

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        this.viewManagerModel.setActiveView(editInfoViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
//    }
//
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EditInfoState state = (EditInfoState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
