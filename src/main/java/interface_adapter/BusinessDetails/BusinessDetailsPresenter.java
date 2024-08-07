package interface_adapter.BusinessDetails;

import entity.Business;
import view.BusinessDetailsViewInterface;

public class BusinessDetailsPresenter {
    private BusinessDetailsViewModel viewModel;
    private BusinessDetailsViewInterface view;

    public BusinessDetailsPresenter(BusinessDetailsViewModel viewModel, BusinessDetailsViewInterface view) {
        this.viewModel = viewModel;
        this.view = view;
    }

    public void loadBusinessDetails(Business business) {
        viewModel.setName(business.getName());
        viewModel.setLocation(String.format("%.2f, %.2f", business.getLocation().get(0), business.getLocation().get(1)));
        viewModel.setDistance(String.format("%.2f km", business.getDistance()));
        viewModel.setContactNum(business.getContactNum());
        viewModel.setPrice(business.getPrice());
        viewModel.setRatings(String.format("%.1f / 5.0", business.getRatings()));
        viewModel.setType(business.getType());

        view.updateDetails(viewModel);
    }
}
