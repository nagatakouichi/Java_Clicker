package gui;

import javax.swing.*;
import java.util.function.Function;

abstract class YesNoWindow extends JFrame {
    private int width = 300;
    private int height = 150;

    public YesNoWindow(String text) {
        super();
        setSize(width, height);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel label = new JLabel(text);
        label.setAlignmentX(0.5f);
        this.add(label);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));

        JButton yesButton = new JButton("はい");
        yesButton.addActionListener(e -> this.yesAction());
        buttonPanel.add(yesButton);

        JButton noButton = new JButton("いいえ");
        noButton.addActionListener(e -> this.noAction());
        buttonPanel.add(noButton);

        this.add(buttonPanel);

        this.setVisible(true);
    }

    public abstract void yesAction();
    public abstract void noAction();
}
