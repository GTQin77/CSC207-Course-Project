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

public class DayplanView extends JPanel implements PropertyChangeListener {
    public final String viewName = "DayplanView";
    private UserService userService;
    private DayplanPresenter dayplanPresenter;
    private DayplanController dayplanController;
    private BusinessDetailsPresenter businessDetailsPresenter;
    private BusinessDetailsController businessDetailsController;

    public DayplanView(UserService userService, DayplanPresenter dayplanPresenter, DayplanController dayplanController, BusinessDetailsPresenter businessDetailsPresenter, BusinessDetailsController businessDetailsController) {
        this.userService = userService;
        this.dayplanPresenter = dayplanPresenter;
        this.dayplanController = dayplanController;
        this.businessDetailsPresenter = businessDetailsPresenter;
        this.businessDetailsController = businessDetailsController;
        this.userService.addPropertyChangeListener(this);
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        updateUIComponents(userService.getDayplan());
    }


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
        JButton previousDayplanButton = new JButton("Previous Dayplan");

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

    public void updateBusinessButtons(ArrayList<Business> updatedBusinesses) {
        removeAll();
        initializeUI();
        revalidate();
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("dayplan".equals(evt.getPropertyName())) {
            updateUIComponents((Dayplan) evt.getNewValue());
        }
    }
}

