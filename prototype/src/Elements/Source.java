package prototype.src.Elements;

import prototype.src.Players.Player;
import prototype.src.View.SourceView;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A forrást reprezentáló osztály, a Node leszármazottja.
 */
public class Source extends Node{
    public static int counter = 0;
    private SourceView view;

    public void setView(SourceView view) {
        this.view = view;
    }

    public SourceView getView() {
        return view;
    }

    public Source(){
        ID = "source" + ++counter;
        view = null;
    }
    public static void resetCounter(){counter = 0;}

    /**
     * A Steppable interface függvényét valósítja meg, ez "indítja" a víz folyását.
     */
    @Override
    public void Step(){
        ForwardWater(this);
    }

    /**
     * Továbbítja a vizet a szomszédainak.
     * @param elem
     */
    @Override
    public void ForwardWater(Element elem){
        for(int i = 0; GetNeighbor(i) != null; ++i) GetNeighbor(i).ForwardWater(this);
    }

    @Override
    public void Save(FileWriter writer, boolean objectState) {
        try {
            if(objectState){
                writer.write("source+" + ID + "+" + working + "\n");
            } else {
                writer.write("source+" + ID);
                for(Player player : players) {
                    writer.write("+" + player.getID());
                }
                if(players.size() == 0) {
                    writer.write("+null");
                }
                for(Pipe pipe : neighbours) {
                    writer.write("+" + pipe.getID() + "\n");
                }
                if(neighbours.size() == 0) {
                    writer.write("+null\n");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void Read(String[] parsed){
        if(parsed[0] != "source") return;
        ID = parsed[1];
        working = Boolean.parseBoolean(parsed[2]); 
    }

    @Override
    public void list(){
        System.out.println("\nSource");
        System.out.println("id: " + getID());
        System.out.println("working: " + working);
        System.out.print("players: [");
        for(Player p: players) {
            if(p != null)System.out.print(p.getID() + " , ");
            else System.out.print("null,");
        }
        System.out.println("]");
        System.out.print("neighbours: [");
        for(Pipe p: neighbours){
            if(p != null)System.out.print(p.getID() + " , ");
            else System.out.print("null,");
        }
        System.out.println("]");
    }
}
