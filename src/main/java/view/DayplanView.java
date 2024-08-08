package view;

import entity.Business;
import entity.Dayplan;
import interface_adapter.BusinessDetails.BusinessDetailsController;
import interface_adapter.BusinessDetails.BusinessDetailsPresenter;
import interface_adapter.Dayplan.DayplanController;
import interface_adapter.Dayplan.DayplanPresenter;
import services.UserService;

import javax.swing.*;
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
        JPanel businessPanel = new JPanel(new GridLayout(dayplan.getPlan().size(), 1));
        for (Business business : dayplan.getPlan()) {
            JButton button = new JButton(business.getName());
            button.addActionListener(e -> businessDetailsController.loadBusinessDetails(business));
            businessPanel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(businessPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton returnButton = new JButton("Return");
        JButton editUserButton = new JButton("Edit User Info");
        returnButton.addActionListener(e -> dayplanPresenter.navigateToDayplanInput());
        editUserButton.addActionListener(e -> dayplanPresenter.navigateToEditUser());
        topPanel.add(returnButton);
        topPanel.add(editUserButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> dayplanController.handleRefresh());
        bottomPanel.add(refreshButton);
        add(bottomPanel, BorderLayout.SOUTH);

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


//public class DayplanView extends JPanel {
//    public final String viewName = "DayplanView";
//    private BusinessDetailsView businessDetailsView;
//    private DayplanController dayplanController;
//    private DayplanPresenter dayplanPresenter;
//    private UserService userService;
//    private BusinessDetailsPresenter businessDetailsPresenter;
//
//    public DayplanView(BusinessDetailsView businessDetailsView, DayplanController dayplanController, DayplanPresenter dayplanPresenter, UserService userService) {
//        this.businessDetailsView = businessDetailsView;
//        this.dayplanController = dayplanController;
//        this.userService = userService;
//        this.dayplanPresenter = dayplanPresenter;
//        initializeUI();
//    }
//
//    private void initializeUI() {
//        setLayout(new BorderLayout());
//        ArrayList<Business> businesses = userService.getDayplan().getPlan();
//
//        JPanel businessPanel = new JPanel();
//        businessPanel.setLayout(new GridLayout(businesses.size(), 1));
//        for (Business business : businesses) {
//            JButton button = new JButton(business.getName());
//            button.addActionListener(e -> businessDetailsPresenter.loadBusinessDetails(business));
//            businessPanel.add(button);
//        }
//        add(new JScrollPane(businessPanel), BorderLayout.CENTER);
//
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JButton returnButton = new JButton("Return");
//        JButton editUserButton = new JButton("Edit User Info");
//        returnButton.addActionListener(e -> dayplanPresenter.navigateToDayplanInput());
//        editUserButton.addActionListener(e -> dayplanPresenter.navigateToEditUser());
//        topPanel.add(returnButton);
//        topPanel.add(editUserButton);
//
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JButton refreshButton = new JButton("Refresh");
//        refreshButton.addActionListener(e -> dayplanController.handleRefresh());
//        bottomPanel.add(refreshButton);
//
//        add(topPanel, BorderLayout.NORTH);
//        add(bottomPanel, BorderLayout.SOUTH);
//    }
//
//

//}