package prototype.src.View;

import javax.swing.*;

/**
 * MyJPanel extends JPanel and implements Size.
 */
public abstract class MyJPanel extends JPanel {
    protected int WIDTH;
    protected int HEIGHT;
    protected AppWindow originalWindow;

    int getWIDTH() {
        return WIDTH;
    }

    int getHEIGHT() {
        return HEIGHT;
    }
}
