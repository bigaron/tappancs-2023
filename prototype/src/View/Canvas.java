package prototype.src.View;

import prototype.src.Players.Saboteur;

import java.awt.*;
import java.util.ArrayList;

public class Canvas extends MyJPanel {
    ArrayList<ElementView> elementViews;
    ArrayList<PlayerView> playerViews;

    public Canvas() {
        WIDTH = 20000;
        HEIGHT = 20000;
        elementViews = new ArrayList<>();
        playerViews = new ArrayList<>();

        playerViews.add(new SaboteurView(null));      //for testing
        playerViews.add(new PlumberView(null));      //for testing

        setPreferredSize(new Dimension(20000, 20000));
        setBackground (Color.cyan);
        setLayout(new GridLayout(1, 1));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (ElementView elementView : elementViews) {
            elementView.draw(g);
        }

        for(PlayerView playerView : playerViews) {
            playerView.draw(g);
        }
    }

}
