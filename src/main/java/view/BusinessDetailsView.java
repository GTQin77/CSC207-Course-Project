package view;

import interface_adapter.BusinessDetails.BusinessDetailsPresenter;
import interface_adapter.BusinessDetails.BusinessDetailsViewModel;

import javax.swing.*;
import java.awt.*;


public class BusinessDetailsView extends JPanel implements BusinessDetailsViewInterface {
    public final String viewName = "BusinessDetails";
    private final JLabel nameLabel;
    private final JLabel locationLabel;
    private final JLabel distanceLabel;
    private final JLabel contactNumLabel;
    private final JLabel priceLabel;
    private final JLabel ratingsLabel;
    private final JButton returnButton;
    private final JButton editUserButton;
    private final BusinessDetailsPresenter presenter;

    public BusinessDetailsView(BusinessDetailsPresenter presenter) {
        this.presenter = presenter;
        setLayout(new BorderLayout());

        JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        nameLabel = new JLabel();
        locationLabel = new JLabel();
        distanceLabel = new JLabel();
        contactNumLabel = new JLabel();
        priceLabel = new JLabel();
        ratingsLabel = new JLabel();

        JLabel[] labels = {
                new JLabel("Name:", JLabel.RIGHT),
                nameLabel,
                new JLabel("Location:", JLabel.RIGHT),
                locationLabel,
                new JLabel("Distance:", JLabel.RIGHT),
                distanceLabel,
                new JLabel("Contact Number:", JLabel.RIGHT),
                contactNumLabel,
                new JLabel("Price:", JLabel.RIGHT),
                priceLabel,
                new JLabel("Ratings:", JLabel.RIGHT),
                ratingsLabel
        };


        for (JLabel label : labels) {
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            detailsPanel.add(label);
        }


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnButton = new JButton("Return");
        editUserButton = new JButton("Edit Account");

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

        presenter.navigateToBusinessDetails();
    }
}