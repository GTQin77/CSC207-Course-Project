package view;

import entity.Business;
import interface_adapter.BusinessDetails.BusinessDetailsPresenter;
import interface_adapter.BusinessDetails.BusinessDetailsViewModel;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class BusinessDetailsView extends JPanel implements BusinessDetailsViewInterface {
    public final String viewName = "BusinessDetails";
    private JLabel nameLabel;
    private JLabel locationLabel;
    private JLabel distanceLabel;
    private JLabel contactNumLabel;
    private JLabel priceLabel;
    private JLabel ratingsLabel;
    private JLabel typeLabel;
    private JButton returnButton;
    private JButton editUserButton;
    private BusinessDetailsPresenter presenter;

    public BusinessDetailsView(BusinessDetailsPresenter presenter) {
        this.presenter = presenter;
        setLayout(new BorderLayout());



        JPanel detailsPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        nameLabel = new JLabel();
        locationLabel = new JLabel();
        distanceLabel = new JLabel();
        contactNumLabel = new JLabel();
        priceLabel = new JLabel();
        ratingsLabel = new JLabel();
        typeLabel = new JLabel();

        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(nameLabel);
        detailsPanel.add(new JLabel("Location:"));
        detailsPanel.add(locationLabel);
        detailsPanel.add(new JLabel("Distance:"));
        detailsPanel.add(distanceLabel);
        detailsPanel.add(new JLabel("Contact Number:"));
        detailsPanel.add(contactNumLabel);
        detailsPanel.add(new JLabel("Price:"));
        detailsPanel.add(priceLabel);
        detailsPanel.add(new JLabel("Ratings:"));
        detailsPanel.add(ratingsLabel);
        detailsPanel.add(new JLabel("Type:"));
        detailsPanel.add(typeLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        returnButton = new JButton("Return");
        editUserButton = new JButton("Edit User");

        returnButton.addActionListener(e -> presenter.nagivateToDayplan());
        editUserButton.addActionListener(e -> presenter.navigateToEditUserInfo());

        buttonPanel.add(returnButton);
        buttonPanel.add(editUserButton);

        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
    }

    @Override
    public void displayDetails(BusinessDetailsViewModel viewModel) {
        nameLabel.setText(viewModel.getName());
        locationLabel.setText(viewModel.getLocation());
        distanceLabel.setText(viewModel.getDistance());
        contactNumLabel.setText(viewModel.getContactNum());
        priceLabel.setText(viewModel.getPrice());
        ratingsLabel.setText(viewModel.getRatings());
        typeLabel.setText(viewModel.getType());
    }
}