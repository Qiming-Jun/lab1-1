package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
//import javax.swing.JMenuBar;

/**
 * @author admin d
 *
 */
public final class GuiFrame {
    /**
     * aa.
     */
    private static Toolkit kit = Toolkit.getDefaultToolkit();
    /**
     * bb.
     */
    private static Dimension screensize = kit.getScreenSize();
    /**
     * dd.
     */
    private GuiFrame() {
    }
    /**
     * @param args ae
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
                JFrame frame = new SimpleFrame();
                frame.setLocation((screensize.width - frame.getWidth()) / 2,
                    (screensize.height - frame.getHeight()) / 2);
                frame.setTitle("软工实验一");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            });
    }
}
