package numberCount;

import adder.AdderManager;
import adder.AdderType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NumberCountGUI {
    private final int frameWidth = 400;
    private final int frameHeight = 250;
    private final String autoAdderFormat = "AutoAdder[Tier%1d] Own:%2d, Power:%d";
    private JFrame frame;
    private JLabel numberLabel;
    private JLabel autoAddPower;
    private JLabel manualAddPower;
    private List<JLabel> autoAdderLabelList = new ArrayList<>();
    private NumberCountThread thread;
    private AdderManager adderManager;
    public NumberCountGUI(NumberCountThread thread) {
        this.thread = thread;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel closeLabel = new JLabel("（このウインドウを閉じるとゲームは終了します)");
        closeLabel.setAlignmentX(0.5f);
        frame.add(closeLabel);

        JLabel label = new JLabel("現在のNumber");
        label.setAlignmentX(0.5f);
        frame.add(label);

        numberLabel = new JLabel(String.valueOf(this.thread.getNumber()));
        numberLabel.setAlignmentX(0.5f);
        frame.add(numberLabel);

        JLabel manualAddPowerLabel = new JLabel("現在の Manual Add Power");
        manualAddPowerLabel.setAlignmentX(0.5f);
        frame.add(manualAddPowerLabel);

        manualAddPower = new JLabel(String.valueOf(1));
        manualAddPower.setAlignmentX(0.5f);
        frame.add(manualAddPower);

        JLabel autoAddPowerLabel = new JLabel("現在のTotal Auto Add Power");
        autoAddPowerLabel.setAlignmentX(0.5f);
        frame.add(autoAddPowerLabel);

        autoAddPower = new JLabel(String.valueOf(0));
        autoAddPower.setAlignmentX(0.5f);
        frame.add(autoAddPower);

        frame.setVisible(true);
    }

    public void addAutoAdderGUI(AdderManager adderManager) {
        this.adderManager = adderManager;

        for (int i = 1; i <= this.adderManager.getMaxTier(); i++) {
            long own = this.adderManager.getNumberOwned(i);
            long power = this.adderManager.getPower(AdderType.AUTO, i);
            JLabel autoAdderLabel = new JLabel(String.format(autoAdderFormat, i, own, power));
            autoAdderLabel.setAlignmentX(0.5f);
            autoAdderLabelList.add(autoAdderLabel);
            this.frame.add(autoAdderLabel);
        }
    }

    public void update(long manualPowerAmount, long autoAddPowerAmount) {
        numberLabel.setText(String.valueOf(thread.getNumber()));
        manualAddPower.setText(String.valueOf(manualPowerAmount));
        autoAddPower.setText(String.valueOf(autoAddPowerAmount));
        for (int i = 1; i <= this.autoAdderLabelList.size(); i++) {
            long own = this.adderManager.getNumberOwned(i);
            long power = this.adderManager.getPower(AdderType.AUTO, i);
            JLabel autoAdderLabel = autoAdderLabelList.get(i-1);
            autoAdderLabel.setText(String.format(autoAdderFormat, i, own, power));
        }
    }
    public void close() {
        frame.dispose();
    }
}
