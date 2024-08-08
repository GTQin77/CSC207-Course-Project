package interface_adapter.BusinessDetails;

import interface_adapter.ViewManagerModel;
import view.BusinessDetailsViewInterface;

public class BusinessDetailsPresenter {
    private BusinessDetailsViewModel viewModel;
    private BusinessDetailsViewInterface view;
    private ViewManagerModel viewManagerModel;

    public BusinessDetailsPresenter(BusinessDetailsViewModel viewModel, BusinessDetailsViewInterface view, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.view = view;
        this.viewManagerModel = viewManagerModel;
    }

//    public void navigateToBusinessDetails() {
//        viewManagerModel.setActiveView("BusinessDetails");
//        viewManagerModel.firePropertyChanged();
//    }

    public void navigateToEditUserInfo() {
        viewManagerModel.setActiveView("EditUserInfo");
        viewManagerModel.firePropertyChanged();
    }

    public void nagivateToDayplan() {
        viewManagerModel.setActiveView("DayplanView");
        viewManagerModel.firePropertyChanged();
    }

}
