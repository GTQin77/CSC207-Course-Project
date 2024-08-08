package interface_adapter.BusinessDetails;

import entity.Business;
import interface_adapter.ViewManagerModel;
import services.UserService;
import view.BusinessDetailsViewInterface;

public class BusinessDetailsPresenter {
    private ViewManagerModel viewManagerModel;
    private UserService userService;
    private BusinessDetailsViewModel viewModel;
    private BusinessDetailsViewInterface view;

    public BusinessDetailsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

//    public void navigateToBusinessDetails() {
//        viewManagerModel.setActiveView("BusinessDetails");
//        viewManagerModel.firePropertyChanged();
//    }

    public void navigateToEditUserInfo() {
        userService.setPrevView("BusinessDetails");
        viewManagerModel.setActiveView("EditUserInfo");
        viewManagerModel.firePropertyChanged();
    }

    public void nagivateToDayplan() {
        viewManagerModel.setActiveView("DayplanView");
        viewManagerModel.firePropertyChanged();
    }

    public void loadBusinessDetails(Business business) {
        viewModel.updateBusinessDetails(business);
        view.displayDetails(viewModel);
    }

}
