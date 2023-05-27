package prototype.src.View;

import prototype.src.*;
import prototype.src.Elements.*;
import prototype.src.Players.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends MyJPanel {
    private JLabel elementNameLbl = new JLabel(), neigborsLbl = new JLabel("Neighbours: "), workingLbl = new JLabel(), outputLbl = new JLabel();
    private JLabel generatorLbl = new JLabel();
    private JLabel playerLbl = new JLabel(), playerInvPipeLbl = new JLabel(), playerInvPumpLbl = new JLabel(), generatorPumpLbl = new JLabel();
    private JLabel stateLbl = new JLabel(), detachedLbl = new JLabel(), sabotageableLbl = new JLabel(), modifiedStateLbl = new JLabel(), bufferLbl = new JLabel();
    private JLabel turnsLeftLbl = new JLabel(), saboteurScrLbl = new JLabel(), plumberScrLbl = new JLabel(), dotLbl = new JLabel(" : ");
    private JTextField console = new JTextField();
    private JPanel infoInventoryPanel = new JPanel(), inputPanel = new JPanel();
    private JPanel infoPanel = new JPanel(), inventoryPanel = new JPanel();
    private JPanel headerPanel = new JPanel(), headerTurnsPanel = new JPanel(), headerScorePanel = new JPanel();
    private JComboBox<String> neighboursJCB = new JComboBox<>();
    private ArrayList<String> commands = new ArrayList<>(), neighboursArr = new ArrayList<>();
    private Canvas canvas;
    private int commandPtr = 0;
    private Game game;

    private void setNeighbourLbls( final Element myEl){
        neighboursJCB.removeAllItems();
        neighboursArr.clear();
        int nPtr = 0;
        if(myEl.getID().contains("pipe")){
            for(int i = 0; i < 2; ++i) if(myEl.GetNeighbor(i) != null) neighboursArr.add(nPtr++ + ". " + myEl.GetNeighbor(i).getID());
        }else{
            int i = 0;
            Node el = (Node)myEl;
            while(nPtr != el.getNeighborSize()){
                if(el.GetNeighbor(i) != null) neighboursArr.add(nPtr++ + ". " + el.GetNeighbor(i).getID());
                i++;
            }
        }
    }

    private void updateShownElement(){
        infoPanel.removeAll();
        inventoryPanel.removeAll();
        String ID = game.activePlayer.getElement().getID(); 
        elementNameLbl.setText("Element name: " + ID);
        infoPanel.add(elementNameLbl);
        if(ID.contains("cistern")){
            Cistern myEl = (Cistern)game.activePlayer.getElement(); 
            generatorLbl.setText("generator: " + myEl.getGeneratorID());
            if(!myEl.getGeneratorID().equals("")) generatorPumpLbl.setText("No. pumps: " + myEl.getGeneratorPumps());
            setNeighbourLbls(myEl);
        }else if(ID.contains("source")){
            infoPanel.add(neigborsLbl);
            Source myEl = (Source)game.activePlayer.getElement();
            setNeighbourLbls(myEl);
        }else if(ID.contains("pipe")){
            Pipe myEl = (Pipe)game.activePlayer.getElement();
            workingLbl.setText("Working: " + Boolean.toString(myEl.getWork()));
            stateLbl.setText("State: " + myEl.getState());
            detachedLbl.setText("Detached: " + Boolean.toString(myEl.getDetached()));
            sabotageableLbl.setText("Sabotageable: " + myEl.getSabotageable());
            modifiedStateLbl.setText("Modified state: " + myEl.getModified());

            infoPanel.add(workingLbl);
            infoPanel.add(stateLbl);
            infoPanel.add(sabotageableLbl);
            infoPanel.add(modifiedStateLbl);
            infoPanel.add(neigborsLbl);
            setNeighbourLbls(myEl);
        }else if(ID.contains("pump")){
            Pump myEl = (Pump)game.activePlayer.getElement();
            workingLbl.setText("Working: " + Boolean.toString(myEl.getWork()));
            bufferLbl.setText("Buffer: " + Boolean.toString(myEl.getBuffer()));
            if(myEl.getOutput() != null) {
                outputLbl.setText("output: " + myEl.getOutput().getID());
            } else {
                outputLbl.setText("output: null");
            }

            infoPanel.add(workingLbl);
            infoPanel.add(bufferLbl);
            infoPanel.add(outputLbl);
            infoPanel.add(neigborsLbl);
            setNeighbourLbls(myEl);
        }

        playerLbl.setText(game.activePlayer.getID());
        inventoryPanel.add(playerLbl);
        if(game.activePlayer.getID().contains("saboteur")){
            Saboteur myPl = (Saboteur) game.activePlayer;
            Pipe p = myPl.getPipe();
            if(p != null) playerInvPipeLbl.setText("Pipe: " + myPl.getPipe().getID());
            else playerInvPipeLbl.setText("Pipe: null");
            inventoryPanel.add(playerInvPipeLbl);
        }else{
            Plumber myPl = (Plumber) game.activePlayer;
            Pipe p = myPl.getPipe();
            Pump pa = myPl.getPump();
            if(p != null) playerInvPipeLbl.setText("Pipe: " + myPl.getPipe().getID());
            else playerInvPipeLbl.setText("Pipe: null");
            if(pa != null) playerInvPumpLbl.setText("Pump: " + myPl.getPump().getID());
            else playerInvPumpLbl.setText("Pump: null");
            inventoryPanel.add(playerInvPipeLbl);
            inventoryPanel.add(playerInvPumpLbl);
        }

        for(String s: neighboursArr) neighboursJCB.addItem(s);

        infoPanel.add(neighboursJCB);
        turnsLeftLbl.setText(Integer.toString(Game.actionCounter));
        saboteurScrLbl.setText(Integer.toString(Game.sPoints));
        plumberScrLbl.setText(Integer.toString(Game.pPoints));

        headerTurnsPanel.add(turnsLeftLbl);
        headerScorePanel.add(plumberScrLbl);
        headerScorePanel.add(dotLbl);
        headerScorePanel.add(saboteurScrLbl);

        headerPanel.add(headerTurnsPanel, BorderLayout.LINE_START);
        headerPanel.add(headerScorePanel, BorderLayout.CENTER);

        validate();
        repaint();
    }

    public GameView(AppWindow original) {
        setLayout(new BorderLayout());
        game = new Game();

        canvas = new Canvas(/*this*/);
        canvas.initGame(game);
        canvas.update();

        JScrollPane canvasPane = new JScrollPane(canvas);
        canvasPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        canvasPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        /*canvasPane.getVerticalScrollBar().setValue(10000);
        canvasPane.getHorizontalScrollBar().setValue(10000);*/ //így ez miért csak a horizontalt állítja? xd


        infoInventoryPanel.setLayout(new GridLayout(2, 1));
        inputPanel.setLayout(new GridLayout(1, 1));

        infoInventoryPanel.setPreferredSize(new Dimension(175, 250));

        infoPanel = new JPanel();
        inventoryPanel = new JPanel();

        infoPanel.setLayout(new GridLayout(10, 1));
        inventoryPanel.setLayout(new GridLayout(5, 1));

        headerPanel.setLayout(new BorderLayout());

        plumberScrLbl.setForeground(Color.blue);
        saboteurScrLbl.setForeground(Color.red);

        updateShownElement();
        
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
        add(headerPanel, BorderLayout.PAGE_START);

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
                updateShownElement();
                if(commands.get(commands.size() - 1).equals("placePump")) {
                    int test = 5;
                }

                canvas.update();
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
