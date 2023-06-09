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
    public static ArrayList<ElementView> elementViews;
    public static ArrayList<PlayerView> playerViews;
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Element> pickedUp = new ArrayList<>();
        ArrayList<ElementView> elems = new ArrayList<>();
        for(PlayerView playerView : playerViews) {
            if(playerView.referencedPlayer.getPipe() != null) pickedUp.add(playerView.referencedPlayer.getPipe());
        }
        boolean pickedup = false;
        //draw pipes first in order to draw them in the background
        for(int i = CisternView.counter + SourceView.counter + PumpView.counter; i < CisternView.counter + SourceView.counter + PumpView.counter + PipeView.counter; ++i) {
            pickedup = false;
            for(Element el: pickedUp) if(elementViews.get(i).referencedElement.getID().equals(el.getID())){
                elems.add(elementViews.get(i));
                pickedup = true;
            }
            if(!pickedup)elementViews.get(i).draw(g);
        }

        //draw other objects
        for(int i = 0; i < CisternView.counter + SourceView.counter + PumpView.counter; ++i) {
            elementViews.get(i).draw(g);
        }


        /*for (ElementView elementView : elementViews) {
            elementView.draw(g);
        }*/

        for(PlayerView playerView : playerViews) {
            if(playerView.referencedPlayer.getPipe() != null){
                for(int i = 0; i < elems.size(); ++i){
                    if(pickedUp.get(i).getID().equals(playerView.referencedPlayer.getPipe().getID())){
                        elems.get(i).x = playerView.getX() + playerView.image.getWidth()/2;
                        elems.get(i).y = playerView.getY() + playerView.image.getHeight() / 2;
                        elems.get(i).draw(g);
                    }
                }
            }
            playerView.draw(g);
        }
    }

    public void initGame(Game game) {
        ArrayList<Plumber> plumbers = game.getPlumbers();
        ArrayList<Saboteur> saboteurs = game.getSaboteurs();
        ArrayList<Element> elements = Game.desert; //mivel tudjuk, hogy mik a kezdő objektumok tudjuk hogy a tömbben kik vannak milyen sorrendben ezt kihasználjuk a forciklusokban és az inicializálásnál
        int i = 0;

        for(Plumber plumber : plumbers) {
            PlumberView view = new PlumberView(plumber);
            playerViews.add(view);
            plumber.setView(view);
        }
        for(Saboteur saboteur : saboteurs) {
            SaboteurView view = new SaboteurView(saboteur);
            playerViews.add(view);
            saboteur.setView(view);
        }
        for(; i < Cistern.counter; ++i) {
            CisternView view = new CisternView((Cistern) elements.get(i));
            elementViews.add(view);
            ((Cistern) elements.get(i)).setView(view);
        }
        for(; i < Cistern.counter + Source.counter; ++i) {
            SourceView view = new SourceView((Source) elements.get(i));
            elementViews.add(view);
            ((Source) elements.get(i)).setView(view);
        }
        for(; i < Cistern.counter + Source.counter + Pump.counter; ++i) {
            PumpView view = new PumpView((Pump) elements.get(i));
            elementViews.add(view);
            ((Pump) elements.get(i)).setView(view);
            //elementViews.add(new PumpView((Pump) elements.get(i)));
        }
        for(; i < Cistern.counter + Source.counter + Pump.counter + Pipe.counter; ++i) {
            PipeView view = new PipeView((Pipe) elements.get(i));
            elementViews.add(view);
            ((Pipe) elements.get(i)).setView(view);
        }

        /*//Tudjuk, hogy a 0. elem ciszterna megadjuk az egyetlen ciszterna pozícióját
        elementViews.get(0).setX(100);
        elementViews.get(0).setY(340);

        //Tudjuk, hogy az 1. elem source megadjuk az egyetlen source pozícióját
        elementViews.get(1).setX(980);
        elementViews.get(1).setY(340);

        //Tudjuk, hogy a 2. és 3. elem pump megadjuk ezek kezdő pozícióját
        elementViews.get(2).setX(560);
        elementViews.get(2).setY(340);

        elementViews.get(3).setX(560);
        elementViews.get(3).setY(340);*/
    }

    public void update() {
        repaint();
        elementViews.get(0).calculateCoords(250, 350);
        for(ElementView elementView : elementViews) {
            elementView.setVisited(false);
        }
        RecalculatePipePoints();
        for(PlayerView playerView : playerViews) {
            playerView.calculateCoords(0 ,0);
        }
    }

    public void RecalculatePipePoints() {
        for(int i = CisternView.counter + SourceView.counter + PumpView.counter; i < CisternView.counter + SourceView.counter + PumpView.counter + PipeView.counter; ++i) {
            elementViews.get(i).update();
        }
    }

}
