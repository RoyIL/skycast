package view;

import javax.swing.*;
public class LabelTextPannel extends JPanel {
    LabelTextPannel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
