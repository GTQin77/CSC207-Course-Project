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

public class DayplanInputView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "DayplanInput";

    private final DayplanInputViewModel dayplanInputViewModel;
    private final JTextField cityInputField = new JTextField(15);
    private final JTextField numMealsInputField = new JTextField(15);
    private final JTextField numActivitiesInputField = new JTextField(15);
    private final JTextField descriptionInputField = new JTextField(15);
    private final DayplanInputController dayplanInputController;
    private final ViewManagerModel viewManagerModel;
//    private final DayplanViewModel dayplanViewModel;

    private final JButton OK;

    private Dayplan dayplan;
    public Dayplan getDayplan() {return dayplan;}
    public void setDayplan() {this.dayplan = dayplan;}

    public DayplanInputView(DayplanInputController dayplanInputController, DayplanInputViewModel dayplanInputViewModel, ViewManagerModel viewManagerModel) {
        this.dayplanInputController = dayplanInputController;
        this.dayplanInputViewModel = dayplanInputViewModel;
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

        OK.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(OK)) {
                            try {
                                dayplanInputController.execute(cityInputField.getText(),
                                        Integer.parseInt(numMealsInputField.getText()),
                                        Integer.parseInt(numActivitiesInputField.getText()),
                                        descriptionInputField.getText());
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(DayplanInputView.this, e.getMessage());
                            }
                        }

                    }
                }
        );

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
//            this.viewManagerModel.setActiveView(dayplanViewModel.getModelName());
            this.viewManagerModel.firePropertyChanged();
        }

    }

}
