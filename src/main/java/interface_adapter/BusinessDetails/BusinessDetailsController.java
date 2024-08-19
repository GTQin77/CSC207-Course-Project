package interface_adapter.BusinessDetails;

import entity.Business;
import view.*;

public class BusinessDetailsController {
    private BusinessDetailsViewModel viewModel;
    private BusinessDetailsViewInterface view;


    /**
     * Constructs a BusinessDetailsController with a specific view model and view interface.
     * @param businessDetailsViewModel The view model that holds the data and logic for business details.
     * @param view The view interface that defines how business details are displayed.
     */
    public BusinessDetailsController(BusinessDetailsViewModel businessDetailsViewModel, BusinessDetailsViewInterface view) {
        this.viewModel = businessDetailsViewModel;
        this.view = view;
    }

    /**
     * Loads business details into the view model and updates the view.
     * @param business The business entity whose details are to be displayed in the view.
     */
    public void loadBusinessDetails(Business business) {
        viewModel.updateBusinessDetails(business);
        view.displayDetails(viewModel);
    }

}
