package numberCount;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NumberCountWindow extends JFrame {
    NumberCountThread thread;
    public NumberCountWindow(int width, int height, NumberCountThread thread) {
        super();
        this.thread = thread;
        setSize(width, height);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        addWindowListener(new NumberCountWindowAdaptor());
    }

    class NumberCountWindowAdaptor extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            thread.setIsStopRunning(true);
            dispose();
        }
    }
}
