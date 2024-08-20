package view;

import entity.Dayplan;
import interface_adapter.DayplanInput.DayplanInputController;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.DayplanInput.DayplanInputState;
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
 * The view for user input of day plan details including city, number of meals,
 * number of activities, and a description.
 */
public class DayplanInputView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "DayplanInput";

    private final JTextField cityInputField = new JTextField(15);
    private final JTextField numMealsInputField = new JTextField(15);
    private final JTextField numActivitiesInputField = new JTextField(15);
    private final JTextField descriptionInputField = new JTextField(15);
    private final ViewManagerModel viewManagerModel;

    private final JButton OK;

    private Dayplan dayplan;
    public Dayplan getDayplan() {return dayplan;}
    public void setDayplan() {this.dayplan = dayplan;}

    public DayplanInputView(DayplanInputController dayplanInputController, DayplanInputViewModel dayplanInputViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        dayplanInputViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(dayplanInputViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel cityInfo = new LabelTextPanel(
                new JLabel(dayplanInputViewModel.CITY_LABEL), cityInputField);

        LabelTextPanel numMealsInfo = new LabelTextPanel(
                new JLabel(dayplanInputViewModel.MEALS_LABEL), numMealsInputField);

        LabelTextPanel numActivitiesInfo = new LabelTextPanel(
                new JLabel(dayplanInputViewModel.ACTIVITIES_LABEL), numActivitiesInputField);

        LabelTextPanel descriptionInfo = new LabelTextPanel(
                new JLabel(dayplanInputViewModel.DESCRIPTION_LABEL), descriptionInputField);

        JPanel buttons = new JPanel();
        OK = new JButton(dayplanInputViewModel.OK_BUTTON_LABEL);
        buttons.add(OK);

        OK.addActionListener(evt -> {
            try {
                String city = cityInputField.getText().trim();
                if (city.isEmpty()) {
                    JOptionPane.showMessageDialog(DayplanInputView.this, "City cannot be empty.");
                    return;
                }

                String description = descriptionInputField.getText().trim();
                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(DayplanInputView.this, "Description cannot be empty.");
                    return;
                }

                int numMeals = Integer.parseInt(numMealsInputField.getText().trim());
                int numActivities = Integer.parseInt(numActivitiesInputField.getText().trim());

                if (numMeals > 5 || numActivities > 5) {
                    JOptionPane.showMessageDialog(DayplanInputView.this, "Both meals and activities numbers must be less than or equal to 5.");
                    return;
                }

                dayplanInputController.execute(cityInputField.getText(), numMeals, numActivities, descriptionInputField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(DayplanInputView.this, "Please enter valid integers for meals and activities.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(DayplanInputView.this, e.getMessage());
            }
        });

        cityInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        DayplanInputState currentState = dayplanInputViewModel.getState();
                        currentState.setCity(cityInputField.getText() + e.getKeyChar());
                        dayplanInputViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(cityInfo);
        this.add(numMealsInfo);
        this.add(numActivitiesInfo);
        this.add(descriptionInfo);

        this.add(buttons);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        DayplanInputState state = (DayplanInputState) evt.getNewValue();
        if (state.getCityError() != null) {
            JOptionPane.showMessageDialog(this, state.getCityError());
        }

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(OK)) {
            this.viewManagerModel.firePropertyChanged();
        }
    }
}
