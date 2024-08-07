package view;

import interface_adapter.Dayplan.DayplanController;
import interface_adapter.Dayplan.DayplanViewModel;
import entity.User;
import interface_adapter.DayplanInput.DayplanInputController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DayplanView extends JPanel {

    ArrayList<String> businessNames = new ArrayList<>();
    private int numBusinesses;

    private final DayplanController dayplanController;
    private final DayplanViewModel dayplanViewModel;

    public DayplanView(DayplanController dayplanController,
                       DayplanViewModel dayplanViewModel) {
        this.dayplanController = dayplanController;
        this.dayplanViewModel = dayplanViewModel;
        this.numBusinesses = dayplanController.getDayplanLength();

        JLabel title = new JLabel(dayplanViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        businessNames = dayplanController.getBusinessNames();

        JPanel dayplanPanel = new JPanel();
        dayplanPanel.setLayout(new BoxLayout(dayplanPanel, BoxLayout.PAGE_AXIS));

        for (String business : businessNames) {
            JButton singleBusiness = getjButton(business);

            dayplanPanel.add(singleBusiness);
        }
        mainPanel.add(dayplanPanel);
    }

    private static @NotNull JButton getjButton(String business) {
        JButton singleBusiness = new JButton(business);
        singleBusiness.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(singleBusiness)) {
                            // if the button clicked is a business button do something
                            // if the button label matches whichever business
                        }
                    }
                }
        );
        return singleBusiness;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(420,420);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.add(Box.createHorizontalGlue());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JPanel rightside = new JPanel();

        ArrayList<String> test = new ArrayList<>();
        test.add("hello");
        test.add("arg");

        for (String business : test) {
            JButton butt = new JButton(business);
            buttonPanel.add(butt);
        }

        JButton test2 = new JButton();
        rightside.add(test2);

        mainPanel.add(buttonPanel);
        mainPanel.add(rightside);

        frame.add(mainPanel);
        buttonPanel.setVisible(true);
        rightside.setVisible(true);
        mainPanel.setVisible(true);
        frame.setVisible(true);

    }
}
