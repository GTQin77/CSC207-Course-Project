package view;

import entity.PreviousBusiness;
import entity.PreviousPlan;

import interface_adapter.ViewManagerModel;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//public class PastDayplanView extends JPanel implements PropertyChangeListener {
//    public final String viewName = "PastDayplan";
//    private JButton returnButton;
//    private JButton editUserButton;
//    private JList<String> businessList;
//    private UserService userService;
//    private PastDayplanPresenter presenter;
//    private PastDayplanController pastDayplanController;
//    private final ViewManagerModel viewManagerModel;
//
//    public PastDayplanView(PastDayplanPresenter presenter, UserService userService, PastDayplanController pastDayplanController, ViewManagerModel viewManagerModel) {
//        this.presenter = presenter;
//        this.userService = userService;
//        this.pastDayplanController = pastDayplanController;
//        this.viewManagerModel = viewManagerModel;
//        this.userService.addPropertyChangeListener(this);
//        setLayout(new BorderLayout());
//        initializeUI();
//    }
//
//    private void initializeUI() {
//        businessList = new JList<>();
//        add(new JScrollPane(businessList), BorderLayout.CENTER);
//        updateBusinessList();
//
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        returnButton = new JButton("Return");
//        editUserButton = new JButton("Edit User Info");
//
//        returnButton.addActionListener(e -> {
//            viewManagerModel.setActiveView(userService.getPrevView());
//            viewManagerModel.firePropertyChanged();
//        });
//
//        buttonPanel.add(returnButton);
//        add(buttonPanel, BorderLayout.NORTH);
//    }
//
//    public void updateBusinessList() {
//        DefaultListModel<String> model = new DefaultListModel<>();
//        PreviousPlan previousPlan = userService.getPreviousPlan();
//        if (previousPlan != null) {
//            int size = previousPlan.getPlanSize();
//            for (int i = 0; i < size; i++) {
//                PreviousBusiness business = previousPlan.getBusinesses(i);
//                model.addElement(business.getBusiness());
//            }
//            businessList.setModel(model);
//        }
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        if ("previousPlanUpdated".equals(evt.getPropertyName())) {
//            updateBusinessList();
//        }
//    }
//}
