package prototype.src.Elements;

import prototype.src.*;
import prototype.src.Players.Player;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A pumpát reprezentáló osztály, a Node leszármazottja.
 */
public class Pump extends Node{
    private Pipe output;
    private boolean buffer;
    private static long counter = 0;

    public Pump(){
        ID = "pump" + ++counter;
    }
    /**
     * Megváltoztatja a pumpa folyásirányát.
     * @param outgoingPipe a kimeneti cső
     */
    @Override
    public void ChangeDirection(int outgoingPipe){
        output = (Pipe)GetNeighbor(outgoingPipe);
    }

    /**
     * A víz továbbítását végző függvény.
     * @param elem az elem ahonnan jön a víz
     */
    @Override
    public void ForwardWater(Element elem){
        if(buffer || !working || elem == output) return;
        output.ForwardWater(elem);
    }

    /**
     * A Steppable függvényének implementálása, a pumpa random elromlik.
     * 
     *  TODO: ertekek megadasa
     */
    @Override
    public void Step(){
        if(Game.random){

        }
    }

    /**
     * A pumpa mode-ját megváltoztató függvény
     * @param mode az új mode
     */
    @Override
    public void ChangeElementMode(boolean mode){
        buffer = mode;
    }

    /**
     * A pumpáról egy cső lecsatolása.
     * @param pipe a lecsatolandó cső
     * @return művelet sikeressége.
     */
    @Override
    public int TakeoffPipe(Pipe pipe){ 
        int ret = pipe.TakeoffPipe(pipe);
        if(ret == -1) return 0;
        if(ret == 1) return 0;
        if(ret == 2){
            pipe.RemoveNeighbor(this);
            if(pipe == output) super.ChangeElementMode(false); 
            RemoveNeighbor(pipe);
        }
        return 1;
    }
    /**
     * A kimeneti cső beállítása.
     * @param pipe a beállítandó cső
     */
    public void SetOutputPipe(Pipe pipe) {
        output = pipe;
    }

    public boolean GetPipe(Pipe pipe){
        int ret = pipe.TakeoffPipe(pipe);
        if(ret == -1) return false;
        if(pipe == output) super.ChangeElementMode(false);
        pipe.PickedUp();
        RemoveNeighbor(pipe);   
        return true;
    }

    @Override
    public void Save(FileWriter writer, boolean objectState) {
        try {
            if(objectState) {
                writer.write("pump+" + ID + "+" + working + "+" + buffer + "\n");
            } else {
                writer.write("pump+" + ID);
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
                if(output != null) {
                    writer.write("+" + output.getID() + "\n");
                } else {
                    writer.write("+null\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read(String line){
        String[] parsed = line.split("+");
        if(parsed[0] != "pump") return;
        ID = parsed[1];
        working = Boolean.parseBoolean(parsed[2]);
        buffer = Boolean.parseBoolean(parsed[3]);
    }
}
