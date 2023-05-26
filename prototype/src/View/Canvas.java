package prototype.src.View;

import prototype.src.Elements.*;
import prototype.src.Game;
import prototype.src.Generator;
import prototype.src.Players.Plumber;
import prototype.src.Players.Saboteur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends MyJPanel {
    ArrayList<ElementView> elementViews;
    ArrayList<PlayerView> playerViews;
    //GameView gameView;

    public Canvas(/*GameView view*/) {
        //gameView = view;
        WIDTH = 20000;
        HEIGHT = 20000;
        elementViews = new ArrayList<>();
        playerViews = new ArrayList<>();

        setPreferredSize(new Dimension(20000, 20000));
        setBackground (new Color(220, 197, 142));
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

    public void initGame(Game game) {
        ArrayList<Plumber> plumbers = game.getPlumbers();
        ArrayList<Saboteur> saboteurs = game.getSaboteurs();
        ArrayList<Element> elements = Game.desert; //mivel tudjuk, hogy mik a kezdő objektumok tudjuk hogy a tömbben kik vannak milyen sorrendben ezt kihasználjuk a forciklusokban és az inicializálásnál
        int i = 0;

        for(Plumber plumber : plumbers) {
            playerViews.add(new PlumberView(plumber));
        }
        for(Saboteur saboteur : saboteurs) {
            playerViews.add(new SaboteurView(saboteur));
        }
        for(; i < Cistern.counter; ++i) {
            elementViews.add(new CisternView((Cistern)elements.get(i)));
        }
        for(; i < Cistern.counter + Source.counter; ++i) {
            elementViews.add(new SourceView((Source) elements.get(i)));
        }
        for(; i < Cistern.counter + Source.counter + Pump.counter; ++i) {
            elementViews.add(new PumpView((Pump) elements.get(i)));
        }
        for(; i < Cistern.counter + Source.counter + Pump.counter + Pipe.counter; ++i) {
            elementViews.add(new PipeView((Pipe) elements.get(i)));
        }

        //Tudjuk, hogy a 0. elem ciszterna megadjuk az egyetlen ciszterna pozícióját
        elementViews.get(0).setX(100);
        elementViews.get(0).setY(340);

        //Tudjuk, hogy az 1. elem source megadjuk az egyetlen source pozícióját
        elementViews.get(1).setX(980);
        elementViews.get(1).setY(340);

        //Tudjuk, hogy a 2. és 3. elem pump megadjuk ezek kezdő pozícióját
        elementViews.get(2).setX(560);
        elementViews.get(2).setY(340);

        elementViews.get(3).setX(560);
        elementViews.get(3).setY(340);
    }

}
