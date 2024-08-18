package app.setup;

import app.usecase_factory.*;
import interface_adapter.BusinessDetails.*;
import interface_adapter.Dayplan.*;
import interface_adapter.DayplanInput.DayplanInputViewModel;
import interface_adapter.EditInfo.EditInfoViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import services.RefreshService;
import services.*;
import view.*;

import javax.swing.*;

public class ViewSetup {
    private final ViewManagerModel viewManagerModel;
    private final UserService userService;
    private final RefreshService refreshService;

    public ViewSetup(ViewManagerModel viewManagerModel, UserService userService, RefreshService refreshService) {
        this.viewManagerModel = viewManagerModel;
        this.userService = userService;
        this.refreshService = refreshService;
    }

    public void setupViews(JPanel views) {
        // Setup ViewModels
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        DayplanInputViewModel dayplanInputViewModel = new DayplanInputViewModel();
        EditInfoViewModel editInfoViewModel = new EditInfoViewModel();
        DayplanViewModel dayplanViewModel = new DayplanViewModel();
        BusinessDetailsViewModel businessDetailsViewModel = new BusinessDetailsViewModel();

        // Setup Presenters and Controllers
        BusinessDetailsPresenter businessDetailsPresenter = new BusinessDetailsPresenter(viewManagerModel, businessDetailsViewModel, userService);
        DayplanController dayplanController = new DayplanController(dayplanViewModel, userService, refreshService);
        DayplanPresenter dayplanPresenter = new DayplanPresenter(viewManagerModel, userService);
        BusinessDetailsView businessDetailsView = new BusinessDetailsView(businessDetailsPresenter);
        BusinessDetailsController businessDetailsController = new BusinessDetailsController(businessDetailsViewModel, businessDetailsView);

        // Create Views
        SignupView signupView = UserSignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userService);
        LoginView loginView = UserLoginUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel, signupViewModel, userService);
        DayplanInputView dayplanInputView = DayplanInputUseCaseFactory.create(viewManagerModel, loginViewModel, dayplanInputViewModel, dayplanViewModel, userService);
        EditInfoView editInfoView = EditInfoUseCaseFactory.create(viewManagerModel, editInfoViewModel, userService);
        DayplanView dayplanView = new DayplanView(userService, dayplanPresenter, dayplanController, businessDetailsController);

        // Link controllers to views
        dayplanController.setView(dayplanView);
        userService.addPropertyChangeListener(dayplanView);

        // Add views to the JPanel managed by ViewManager
        views.add(signupView, signupView.viewName);
        views.add(loginView, loginView.viewName);
        views.add(dayplanInputView, dayplanInputView.viewName);
        views.add(editInfoView, editInfoView.viewName);
        views.add(businessDetailsView, businessDetailsView.viewName);
        views.add(dayplanView, dayplanView.viewName);
    }
}
