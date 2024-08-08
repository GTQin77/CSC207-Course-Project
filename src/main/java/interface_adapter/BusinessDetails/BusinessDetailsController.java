package interface_adapter.BusinessDetails;

import entity.Business;
import interface_adapter.ViewManagerModel;
import view.*;

public class BusinessDetailsController {
    private BusinessDetailsPresenter presenter;
    private ViewManagerModel viewManagerModel;
    private BusinessDetailsViewModel viewModel;
    private BusinessDetailsViewInterface view;


    public BusinessDetailsController(BusinessDetailsPresenter presenter, ViewManagerModel viewManagerModel, BusinessDetailsViewModel businessDetailsViewModel) {
        this.presenter = presenter;
        this.viewManagerModel = viewManagerModel;
        this.viewModel = businessDetailsViewModel;
    }

    public void loadBusinessDetails(Business business) {
        viewModel.setName(business.getName());
        viewModel.setLocation(String.format("%.2f, %.2f", business.getLocation().get(0), business.getLocation().get(1)));
        viewModel.setDistance(String.format("%.2f km", business.getDistance()));
        viewModel.setContactNum(business.getContactNum());
        viewModel.setPrice(business.getPrice());
        viewModel.setRatings(String.format("%.1f / 5.0", business.getRatings()));
        viewModel.setType(business.getType());

        view.displayDetails(viewModel);
    }


}
