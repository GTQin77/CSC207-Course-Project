package interface_adapter.BusinessDetails;

import interface_adapter.ViewManagerModel;
import view.*;

public class BusinessDetailsController {
    private BusinessDetailsPresenter presenter;
    private ViewManagerModel viewManagerModel;

    public BusinessDetailsController(BusinessDetailsPresenter presenter, ViewManagerModel viewManagerModel) {
        this.presenter = presenter;
        this.viewManagerModel = viewManagerModel;
    }

    public void onReturnClicked() {
        viewManagerModel.setActiveView(DayplanView.viewName);
        viewManagerModel.firePropertyChanged();
    }

    public void onEditUserClicked() {
        viewManagerModel.setActiveView(EditInfoView.viewName);
        viewManagerModel.firePropertyChanged();
    }
}
