//package app;
//
//import interface_adapter.DayplanInput.DayplanInputController;
//import interface_adapter.DayplanInput.DayplanInputViewModel;
//import interface_adapter.Login.LoginViewModel;
//import interface_adapter.ViewManagerModel;
//import view.DayplanInputView;
//import view.LoginView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class DayplanInputUseCaseFactory {
//    private DayplanInputUseCaseFactory() {}
//
//    public static DayplanInputView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, DayplanInputViewModel dayplanInputViewModel) {
//        try {
//            DayplanInputController dayplanInputController = createDayplanInputUseCase(viewManagerModel, loginViewModel);
//            return new DayplanInputView(dayplanInputController, dayplanInputViewModel);
//        } catch(IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//
//        }
//        return null;
//    }
//
//    private static DayplanInputController createDayplanInputUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
//
//    }
//}
