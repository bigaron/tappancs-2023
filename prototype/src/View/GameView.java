package prototype.src.View;

import prototype.src.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends MyJPanel {


    private JTextField console = new JTextField();
    private JPanel infoInventoryPanel = new JPanel(), inputPanel = new JPanel();
    private ArrayList<String> commands = new ArrayList<>();
    private int commandPtr = 0;
    private Game game;

    public GameView(AppWindow original) {
        setLayout(new BorderLayout());

        Canvas canvas = new Canvas();

        JScrollPane canvasPane = new JScrollPane(canvas);
        canvasPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        canvasPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        infoInventoryPanel.setLayout(new GridLayout(2, 1));
        inputPanel.setLayout(new GridLayout(1, 1));

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
        
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        infoInventoryPanel.add(infoPanel);
        infoInventoryPanel.add(inventoryPanel);

        console.setBackground(Color.black);
        console.setForeground(Color.white);
        console.setCaretColor(Color.white);
        console.setBorder(BorderFactory.createLineBorder(Color.black));

        console.addKeyListener(new EnterKeyListener());

        inputPanel.add(console);

        add(inputPanel, BorderLayout.PAGE_END);
        add(infoInventoryPanel, BorderLayout.LINE_END);
        add(canvasPane, BorderLayout.CENTER);

        game = new Game();

        originalWindow = original;
        WIDTH = 1200;
        HEIGHT = 800;
    }

    private class EnterKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                commands.add(console.getText());
                commandPtr = commands.size() - 1;
                console.setText("");
                game.parseInput(commands.get(commandPtr));
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                if(!commands.get(commands.size() - 1).equals("")) {
                    commands.add("");
                    commandPtr = commands.size() - 1;
                }
                if(commandPtr != 0) commandPtr--; 
                console.setText(commands.get(commandPtr));
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(commandPtr == commands.size() - 1) return;
                commandPtr++;
                if(commands.get(commandPtr).equals("")) {
                    commands.remove(commandPtr);
                    commandPtr--;
                    console.setText("");
                    return;
                }
                
                console.setText(commands.get(commandPtr));
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
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
