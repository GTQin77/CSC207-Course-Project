package view;

import entity.Business;
import interface_adapter.BusinessDetails.BusinessDetailsController;
import interface_adapter.Dayplan.DayplanController;
import interface_adapter.Dayplan.DayplanPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DayplanView extends JPanel {
    public final String viewName = "DayplanView";
    private ArrayList<Business> businesses;
    private BusinessDetailsView businessDetailsView;
    private DayplanController dayplanController;
    private DayplanPresenter dayplanPresenter;
    private BusinessDetailsController businessDetailsController;

    public DayplanView(ArrayList<Business> businesses, BusinessDetailsView businessDetailsView, DayplanController dayplanController, BusinessDetailsController businessDetailsController) {
        this.businesses = businesses;
        this.businessDetailsView = businessDetailsView;
        this.dayplanController = dayplanController;
        this.businessDetailsController = businessDetailsController;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel businessPanel = new JPanel();
        businessPanel.setLayout(new GridLayout(businesses.size(), 1));
        for (Business business : businesses) {
            JButton button = new JButton(business.getName());
            button.addActionListener(e -> businessDetailsController.loadBusinessDetails(business));
            businessPanel.add(button);
        }
        add(new JScrollPane(businessPanel), BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton returnButton = new JButton("Return");
        JButton editUserButton = new JButton("Edit User Info");
        returnButton.addActionListener(e -> dayplanPresenter.navigateToDayplanInput());
        editUserButton.addActionListener(e -> dayplanPresenter.navigateToEditUser());
        topPanel.add(returnButton);
        topPanel.add(editUserButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> dayplanController.handleRefresh());
        bottomPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }


    public void updateBusinessButtons(ArrayList<Business> updatedBusinesses) {
        removeAll();
        initializeUI();
        revalidate();
        repaint();
    }
}