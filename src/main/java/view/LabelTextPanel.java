package view;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 * <p>
 * This implementation referenced the Pualgries' Clean Architecture code for LabelTextPanel on
 * <a href="https://github.com/paulgries/LoginCleanArchitecture">github.com</a>.
 * </p>
 */
class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}