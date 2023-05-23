package prototype.src.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends MyJPanel {
    private AppWindow originalWindow;

    public MenuView(AppWindow original) {
        WIDTH = 250;
        HEIGHT = 300;
        originalWindow = original;
        setLayout(new GridLayout(3, 1));

        JPanel namePanel = new JPanel();
        JPanel startPanel = new JPanel();
        JPanel exitPanel = new JPanel();

        JLabel nameLabel = new JLabel("Drukmákori háború");
        JButton startButton = new JButton("Play");
        JButton exitButton = new JButton("Exit");

        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,5));
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,5));
        exitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,5));

        startButton.addActionListener(new PlayButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        namePanel.add(nameLabel);
        startPanel.add(startButton);
        exitPanel.add(exitButton);

        add(namePanel);
        add(startPanel);
        add(exitPanel);

    }

    /**
     * Internal ActionListener that changes the current view to GameView.
     */
    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            originalWindow.game = new GameView(originalWindow);
            originalWindow.UpdateView(MenuView.this, originalWindow.game);
        }
    }

    /**
     * Internal ActionListener that closes the program.
     */
    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            //if there's something to do before exiting we can change this
            System.exit(0);
        }
    }
}
