package prototype.src.View;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    GameView game;
    MenuView menu;

    public AppWindow() {
        super("Drukmákor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(1,1));
        menu = new MenuView(this);
        add(menu);

        setLocationRelativeTo(null);
        setResizable(false);

        setSize(menu.getWIDTH(), menu.getHEIGHT());
    }

    public void UpdateView(MyJPanel requester, MyJPanel requested) {
        remove(requester);
        add(requested);
        setSize(requested.getWIDTH(), requested.getHEIGHT());
        setLocationRelativeTo(null);
        validate();
        repaint();
    }
}
