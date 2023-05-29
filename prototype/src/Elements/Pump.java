package prototype.src.Elements;

import prototype.src.*;
import prototype.src.Players.Player;
import prototype.src.View.PumpView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * A pumpát reprezentáló osztály, a Node leszármazottja.
 */
public class Pump extends Node{
    private Pipe output;
    private boolean buffer;
    private boolean outChange = true;
    public static int counter = 0;
    //private PumpView view;

    /*public PumpView getView() {
        return view;
    }

    public void setView(PumpView view) {
        this.view = view;
    }*/

    public Pump(){
        ID = "pump" + ++counter;
    }
    public static void resetCounter(){counter = 0;}

    /**
     * Megváltoztatja a pumpa folyásirányát.
     * @param outgoingPipe a kimeneti cső
     */
    @Override
    public void ChangeDirection(int outgoingPipe){
        if(neighbours.size() < outgoingPipe){
            System.out.println("A(z) " +getID()+ " pumpa kimenete nem változott meg, mert érvénytelen input.\n");
            return;
        }
        if(output == null){
            output = (Pipe)GetNeighbor(outgoingPipe);
            System.out.println("A(z) " +getID()+ " pumpa kimenete megváltozott.\n" +
                            "A(z) " + GetNeighbor(outgoingPipe).getID() + " cső lett a kimenete.\n");
            return;
        }
        String id2 = output.getID();
        output = (Pipe)GetNeighbor(outgoingPipe);
        String id3 = output.getID();
        outChange = true;
        System.out.println("A(z) " +getID()+ " pumpa kimenete megváltozott.\n" +
                            "A(z) " + id2 + " cső helyett a(z) " + id3 + " cső lett a kimenete.\n");
    }

    /**
     * A víz továbbítását végző függvény.
     * @param elem az elem ahonnan jön a víz
     */
    @Override
    public void ForwardWater(Element elem){
        if(buffer || !working || elem == output || output == null) return;
        output.ForwardWater(elem);
    }

    /**
     * A Steppable függvényének implementálása, a pumpa random elromlik.
     * 
     *  TODO: ertekek megadasa SOLVED
     */
    @Override
    public void Step(){
        if(Game.random){
            Random random = new Random();
            if(random.nextInt() % 15 == 0) working = false;
        } else {
            if(Game.globalActionCounter % 17 == 0 && Game.globalActionCounter != 0) {
                working = false;
            }
        }
    }

    /**
     * A pumpa mode-ját megváltoztató függvény
     * @param mode az új mode
     */
    @Override
    public void ChangeElementMode(boolean mode){
        working = mode;
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
        if(ret == 1) return 1;
        if(ret == 2){
            pipe.RemoveNeighbor(this);
            pipe.SetDetached(true);
            if(pipe == output) super.ChangeElementMode(false); 
            RemoveNeighbor(pipe);
        }
        return 2;
    }

    public boolean getOutChanged(){ 
        if(!outChange) return false;
        outChange = false;
        return true;
    }

    /**
     * A kimeneti cső beállítása.
     * @param pipe a beállítandó cső
     */
    public void SetOutputPipe(Pipe pipe) {
        outChange = true;
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
                for(int i = 0; i < players.size(); ++i) {
                    if(i == 0) {
                        writer.write("+" + players.get(i).getID());
                    } else {
                        writer.write("," + players.get(i).getID());
                    }
                }
                if(players.size() == 0) {
                    writer.write("+null");
                }
                for(int i = 0; i < neighbours.size(); ++i) {
                    if(i == 0) {
                        writer.write("+" + neighbours.get(i).getID());
                    } else {
                        writer.write("," + neighbours.get(i).getID());
                    }
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

    public void Read(String[] parsed){
        if(parsed[0] != "pump") return;
        ID = parsed[1];
        working = Boolean.parseBoolean(parsed[2]);
        buffer = Boolean.parseBoolean(parsed[3]);
    }

    public boolean getBuffer(){return buffer;}
    public Pipe getOutput(){return output;}

    @Override
    public void list(){
        System.out.println("\nPump");
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
        System.out.println("buffer: " + buffer);
        System.out.println("output: " + output.getID());
    }
}
