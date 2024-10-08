package numberCount;

import adder.AdderManager;
import adder.AdderType;
import gui.SaveDeleteWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberCountGUI {
    private final int frameWidth = 600;
    private final int frameHeight = 600;
    private final String autoAdderFormat1 = "AutoAdder[Tier%2d] Own:%4d";
    private final String autoAdderFormat2 = "Power:%15d";
    private final String powerUpButtonFormat = "<html>PowerUp<br>%d<html>";
    private final String ownButtonFormat = "<html>Own<br>%d<html>";
    private JFrame frame;
    private JPanel autoAddersPanel;
    private JLabel numberLabel;
    private JLabel autoAddPower;
    private JLabel manualAddPower;
    private JButton manualAddPowerUpButton;
    private Map<Integer, JButton> autoAdderPowerUpButtonMap = new HashMap<>();
    private Map<Integer, JLabel> autoAdderLabelMap1 = new HashMap<>();
    private Map<Integer, JLabel> autoAdderLabelMap2 = new HashMap<>();
    private Map<Integer, JButton> ownButtonMap = new HashMap<>();
    private NumberCountThread thread;
    private AdderManager adderManager;
    public NumberCountGUI(NumberCountThread thread) {
        this.thread = thread;

        frame = new NumberCountWindow(frameWidth, frameHeight, this.thread);

        JLabel label = new JLabel("現在のNumber");
        label.setAlignmentX(0.5f);
        frame.add(label);

        numberLabel = new JLabel(String.valueOf(this.thread.getNumber()));
        numberLabel.setAlignmentX(0.5f);
        frame.add(numberLabel);

        JPanel manualAddPowerPanel = new JPanel();
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));

        JLabel manualAddPowerLabel = new JLabel("現在の Manual Add Power");
        manualAddPowerLabel.setAlignmentX(0.5f);
        labelsPanel.add(manualAddPowerLabel);

        manualAddPower = new JLabel(String.valueOf(1));
        manualAddPower.setAlignmentX(0.5f);
        labelsPanel.add(manualAddPower);

        manualAddPowerUpButton = new JButton("PowerUp");
        manualAddPowerUpButton.setText(String.format(powerUpButtonFormat, 1));
        manualAddPowerUpButton.addActionListener(e -> adderManager.buyPowerUp(AdderType.MANUAL, 0));

        manualAddPowerPanel.add(labelsPanel);
        manualAddPowerPanel.add(manualAddPowerUpButton);
        this.frame.add(manualAddPowerPanel);

        JLabel autoAddPowerLabel = new JLabel("現在のTotal Auto Add Power");
        autoAddPowerLabel.setAlignmentX(0.5f);
        frame.add(autoAddPowerLabel);

        autoAddPower = new JLabel(String.valueOf(0));
        autoAddPower.setAlignmentX(0.5f);
        frame.add(autoAddPower);

        autoAddersPanel = new JPanel();
        autoAddersPanel.setLayout(new BoxLayout(autoAddersPanel, BoxLayout.Y_AXIS));
        this.frame.add(autoAddersPanel);

        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 50));
        addButton.setAlignmentX(0.5f);
        addButton.addActionListener(e -> adderManager.manualAdd());
        addPanel.add(addButton);
        this.frame.add(addPanel);

        JPanel closePanel = new JPanel();
        closePanel.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
        JButton closeButton = new JButton("終了");
        closeButton.setAlignmentX(0.5f);
        closeButton.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
        closePanel.add(closeButton);
        this.frame.add(closePanel);

        JPanel saveDeletePanel = new JPanel();
        saveDeletePanel.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
        JButton saveDeleteButton = new JButton("セーブデータ消去");
        saveDeleteButton.setAlignmentX(0.5f);
        saveDeleteButton.addActionListener(e -> new SaveDeleteWindow(thread));
        saveDeletePanel.add(saveDeleteButton);
        this.frame.add(saveDeletePanel);

        frame.setVisible(true);
    }

    public void addAutoAdderGUI(AdderManager adderManager) {
        this.adderManager = adderManager;

        for (int i = 1; i <= this.adderManager.getMaxTier(); i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            long own = this.adderManager.getNumberOwned(i);
            long power = this.adderManager.getPower(AdderType.AUTO, i);
            JLabel autoAdderLabel1 = new JLabel(String.format(autoAdderFormat1, i, own));
            autoAdderLabelMap1.put(i, autoAdderLabel1);
            panel.add(autoAdderLabel1);

            int tier = i;
            JButton ownButton = new JButton("Own");
            ownButton.addActionListener(e -> adderManager.buyAdder(AdderType.AUTO, tier));
            ownButtonMap.put(i, ownButton);
            panel.add(ownButton);

            JLabel autoAdderLabel2 = new JLabel(String.format(autoAdderFormat2, power));
            autoAdderLabelMap2.put(i, autoAdderLabel2);
            panel.add(autoAdderLabel2);

            JButton autoAdderPowerUpButton = new JButton("PowerUp");
            autoAdderPowerUpButton.addActionListener(e -> adderManager.buyPowerUp(AdderType.AUTO, tier));
            autoAdderPowerUpButtonMap.put(i, autoAdderPowerUpButton);
            panel.add(autoAdderPowerUpButton);

            autoAddersPanel.add(panel);
        }
        this.frame.validate();
    }

    public void update(long manualPowerAmount, long autoAddPowerAmount) {
        numberLabel.setText(String.valueOf(thread.getNumber()));
        manualAddPower.setText(String.valueOf(manualPowerAmount));
        manualAddPowerUpButton.setText(String.format(powerUpButtonFormat, adderManager.getPowerUpPrice(AdderType.MANUAL, 0)));
        autoAddPower.setText(String.valueOf(autoAddPowerAmount));
        for (int i = 1; i <= this.adderManager.getMaxTier(); i++) {
            long own = this.adderManager.getNumberOwned(i);
            long power = this.adderManager.getPower(AdderType.AUTO, i);
            JLabel autoAdderLabel1 = autoAdderLabelMap1.get(i);
            autoAdderLabel1.setText(String.format(autoAdderFormat1, i, own));
            JLabel autoAdderLabel2 = autoAdderLabelMap2.get(i);
            autoAdderLabel2.setText(String.format(autoAdderFormat2, power));

            JButton ownButton = ownButtonMap.get(i);
            ownButton.setText(String.format(ownButtonFormat, this.adderManager.getOwnPrice(AdderType.AUTO, i)));

            JButton powerUpButton = autoAdderPowerUpButtonMap.get(i);
            powerUpButton.setText(String.format(powerUpButtonFormat, this.adderManager.getPowerUpPrice(AdderType.AUTO, i)));
        }
    }
}
