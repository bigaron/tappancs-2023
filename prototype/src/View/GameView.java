package prototype.src.View;

import javax.swing.*;
import java.awt.*;

public class GameView extends MyJPanel {

    public GameView(AppWindow original) {
        setLayout(new BorderLayout());
        JPanel infoInventoryPanel = new JPanel();
        JPanel inputPanel = new JPanel();

        infoInventoryPanel.setLayout(new GridLayout(2, 1));
        inputPanel.setLayout(new GridLayout(2, 1));

        infoInventoryPanel.setPreferredSize(new Dimension(175, 250));

        JPanel infoPanel = new JPanel();
        JPanel inventoryPanel = new JPanel();

        infoPanel.setLayout(new GridLayout(8, 1));
        inventoryPanel.setLayout(new GridLayout(2, 1));

        JLabel elementNameLabel = new JLabel("Element name: pump1");
        JLabel neighborsLabel = new JLabel("Neighbors:");
        JLabel neighbor1Label = new JLabel("-");
        JLabel neighbor2Label = new JLabel("-");
        JLabel neighbor3Label = new JLabel("-");
        JLabel neighbor4Label = new JLabel("-");
        JLabel workingLabel = new JLabel("working: true");
        JLabel outputLabel = new JLabel("output: pipe1");

        infoPanel.add(elementNameLabel);
        infoPanel.add(neighborsLabel);
        infoPanel.add(neighbor1Label);
        infoPanel.add(neighbor2Label);
        infoPanel.add(neighbor3Label);
        infoPanel.add(neighbor4Label);
        infoPanel.add(workingLabel);
        infoPanel.add(outputLabel);

        JLabel inventoryPipeLabel = new JLabel("pipe: -");
        inventoryPipeLabel.setPreferredSize(new Dimension(50, 50));
        JLabel inventoryPumpLabel = new JLabel("pump: -");
        inventoryPumpLabel.setPreferredSize(new Dimension(50, 50));

        inventoryPanel.add(inventoryPipeLabel);
        inventoryPanel.add(inventoryPumpLabel);

        infoInventoryPanel.add(infoPanel);
        infoInventoryPanel.add(inventoryPanel);

        JTextField currentInput = new JTextField();
        JTextField latestInput = new JTextField();

        currentInput.setPreferredSize(new Dimension(590, 15));
        latestInput.setPreferredSize(new Dimension(590, 15));

        inputPanel.add(latestInput);
        inputPanel.add(currentInput);

        add(inputPanel, BorderLayout.PAGE_END);
        add(infoInventoryPanel, BorderLayout.LINE_END);

        originalWindow = original;
        WIDTH = 1200;
        HEIGHT = 800;
    }

    public void initGame() {

    }

    public void UpdateView() {

    }

    private void initImages() {

    }

    public void AddObjectView(/*ObjectView view*/) {

    }

    public void Draw() {

    }

    public void ParseInput(String cmd) {

    }
}
