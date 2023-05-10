package prototype.src.Elements;

import prototype.src.Players.Player;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A forrást reprezentáló osztály, a Node leszármazottja.
 */
public class Source extends Node{
    private static long counter = 0;
    public Source(){
        ID = "source" + ++counter;
    }
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
}
