package prototype.src.Players;

import prototype.src.IO;
import prototype.src.Elements.*;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A szerelőt reprezentáló osztály, a játékos leszármazottja.
 */
public class Plumber extends Player {
    private static int counter = 0;
    private Pump pump;

    public Plumber() {
        ++counter;
        ID = "plumber" + counter;
    }

    public void RepairElement(){
        elem.Repair();
    }
    public void PlaceDown(){
        if(pump != null) {
            elem.Split(pump);
        }
    }

    public void PickUpPipe(int dir) {
        Pipe p = (Pipe)elem.GetNeighbor(dir);
        boolean successful = elem.GetPipe(p);
        if(successful) this.pipe = p;
    }

    /**
     * Felvesz egy pumpát
     */
    public void PickupPump(){
        if(pump != null) return;
        pump = elem.GetPump();
    }

    /**
     * Beállítja a felvett pumpát az "inventory"-jában.
     * @param pump a felvett pumpa
     */
    public void SetPump(Pump pump) {
        this.pump = pump;
    }

    public void Save(FileWriter writer, boolean state) {
        try {
            if(state) {
                writer.write("plumber+" + ID + "\n");
            } else {
                writer.write("plumber+" + ID + "+" + elem.getID());
                if(pipe != null) {
                    writer.write("+" + pipe.getID());
                } else {
                    writer.write("+null");
                }
                if(pump != null) {
                    writer.write("+" + pump.getID() + "\n");
                } else {
                    writer.write("+null\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read(String[] parsed){
        if(parsed[0] != "plumber") return;
        ID = parsed[1];
    }
}
