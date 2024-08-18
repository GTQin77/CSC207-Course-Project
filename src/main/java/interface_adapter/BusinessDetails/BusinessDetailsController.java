package interface_adapter.BusinessDetails;

import entity.Business;
import view.*;

public class BusinessDetailsController {
    private BusinessDetailsViewModel viewModel;
    private BusinessDetailsViewInterface view;


    public BusinessDetailsController(BusinessDetailsViewModel businessDetailsViewModel, BusinessDetailsViewInterface view) {
        this.viewModel = businessDetailsViewModel;
        this.view = view;
    }

    public void loadBusinessDetails(Business business) {
        viewModel.updateBusinessDetails(business);
        view.displayDetails(viewModel);
    }

}
