package prototype.src.Elements;

import prototype.src.Game;
import prototype.src.Generator;
import prototype.src.Players.Player;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A ciszternát reprezentáló osztály.
 */

public class Cistern extends Node{
    private Generator generator;
    private static long counter = 0;

    public Cistern(){
        ID = "cistern" + ++counter;
    }

    /**
     * A víz továbbítását végző függvény. A víz útja itt ér véget, már nem hív tovább semmit.
     * @param elem
     * TODO: pontadas a szereloknek SOLVED
     */
   @Override
   public void ForwardWater(Element elem){
       Game.increasePoints(1); //1-es csapat plumbers
   }

    /**
     * A ciszternához tartozó generátorból felvesz egy pumpát.
     * @return visszaadja a pumpát, vagy ha nincs épp pumpa, null-t
     */
    @Override
    public Pump GetPump(){
       return generator.GeneratePump();
    }

    /**
     * Beállítja a ciszternához tartozó generátort.
     * @param g a generátor amit be szeretnénk állítani.
     */
    public void SetGenerator(Generator g) {
       generator = g;
    }

    @Override
    public void Save(FileWriter writer, boolean objectState) {
        try {
            if(objectState) {
                writer.write("cistern+" + ID + "+" + working + "\n");
            } else {
                writer.write("cistern+" + ID);
                for(Player player : players) {
                    writer.write("+" + player.getID());
                }
                if(players.size() == 0) {
                    writer.write("+null");
                }
                for(Pipe pipe : neighbours) {
                    writer.write("+" + pipe.getID());
                }
                if(neighbours.size() == 0) {
                    writer.write("+null");
                }
                writer.write("+" + generator.getID() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read(String[] parsed){
        if(parsed[0].equals("cistern")) return;
        ID = parsed[1];
        working = Boolean.parseBoolean(parsed[2]);    
    }
}
