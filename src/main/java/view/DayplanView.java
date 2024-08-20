package view;

import entity.Business;
import entity.Dayplan;
import interface_adapter.BusinessDetails.BusinessDetailsController;
import interface_adapter.BusinessDetails.BusinessDetailsPresenter;
import interface_adapter.Dayplan.DayplanController;
import interface_adapter.Dayplan.DayplanPresenter;
import services.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * The view for displaying and interacting with a day plan.
 */
public class DayplanView extends JPanel implements PropertyChangeListener, IDayplanView {
    public final String viewName = "DayplanView";
    private final UserService userService;
    private final DayplanPresenter dayplanPresenter;
    private final DayplanController dayplanController;
    private final BusinessDetailsController businessDetailsController;

    public DayplanView(UserService userService, DayplanPresenter dayplanPresenter, DayplanController dayplanController, BusinessDetailsController businessDetailsController) {
        this.userService = userService;
        this.dayplanPresenter = dayplanPresenter;
        this.dayplanController = dayplanController;
        this.businessDetailsController = businessDetailsController;
        this.userService.addPropertyChangeListener(this);
        setLayout(new BorderLayout());
        initializeUI();
    }

    /**
     * Initializes the user interface components of the day plan view.
     */
    private void initializeUI() {
        updateUIComponents(userService.getDayplan());
    }


    /**
     * Updates the user interface components based on the current day plan.
     *
     * @param dayplan The day plan to use for updating UI components.
     */
    private void updateUIComponents(Dayplan dayplan) {
        removeAll();

        JLabel vibeLabel = new JLabel("Vibe: " + dayplan.getVibe());
        vibeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        vibeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        vibeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(vibeLabel, BorderLayout.SOUTH);

        JPanel businessPanel = new JPanel(new GridLayout(dayplan.getPlan().size(), 1, 5, 5));
        for (Business business : dayplan.getPlan()) {
            JButton button = new JButton(business.getName());
            businessPanel.add(button);
            button.addActionListener(e -> businessDetailsController.loadBusinessDetails(business));
        }
        add(new JScrollPane(businessPanel), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton returnButton = new JButton("Return");
        JButton editUserButton = new JButton("Edit Account");

        returnButton.addActionListener(e -> dayplanPresenter.navigateToDayplanInput());
        editUserButton.addActionListener(e -> dayplanPresenter.navigateToEditUser());

        leftButtons.add(returnButton);
        leftButtons.add(editUserButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> dayplanController.handleRefresh());
        JPanel rightButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButton.add(refreshButton);

        buttonPanel.add(leftButtons, BorderLayout.WEST);
        buttonPanel.add(rightButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }


    /**
     * Updates the buttons associated with businesses whenever the underlying business list is changed.
     *
     * @param updatedBusinesses The new list of businesses to display.
     */
    @Override
    public void updateBusinessButtons(ArrayList<Business> updatedBusinesses) {
        removeAll();
        initializeUI();
        revalidate();
        repaint();
    }

    /**
     * Responds to property changes in the associated models, particularly changes in the day plan.
     *
     * @param evt The property change event which may trigger UI updates.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("dayplan".equals(evt.getPropertyName())) {
            updateUIComponents((Dayplan) evt.getNewValue());
        }
    }
}

