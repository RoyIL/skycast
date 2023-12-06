package view;

import javax.swing.*;

public class LabelTextPannel extends JPanel {

    private final String text;
    private final String label;
    LabelTextPannel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
        this.text = textField.getText();
        this.label = label.getText();
    }

    public String getText() {
        return this.text;
    }
    public String getLabel() {
        return this.label;
    }
}
