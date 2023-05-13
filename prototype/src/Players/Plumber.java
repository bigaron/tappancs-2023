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
        System.out.println("A(z) "+elem.getID()+ " elem újra funkcionál.\n");
    }

    public void PlaceDown(){
        if(pump != null) {
            elem.Split(pump);
        }else
            System.out.println("A pumpa lerakása sikertelen volt mert nincs nálunk pumpa.\n");

    }

    public void PickUpPipe(int dir) {
        if (dir < 0 || dir > neighbour.size()){
             System.out.println("A cső felvétele sikertelen volt, mert a megadott irány érvénytelen.\n");
             return;
        }
        if (pipe != null){
            System.out.println("A cső felvétele sikertelen volt, mert már van nálunk cső.\n");
             return;
        }
        if (pipe.players.size() != 0){
            System.out.println("A cső felvétele sikertelen volt, mert állnak rajta.\n");
             return;
        }
        Pipe p = (Pipe)elem.GetNeighbor(dir);
        boolean successful = elem.GetPipe(p);
        if(successful) this.pipe = p;
        System.out.println("A(z) "+this.pipe.getID()+" cső a szerelő kezébe került.\n");
    }

    /**
     * Felvesz egy pumpát
     */
    public void PickupPump(){
        if(pump != null) {
            System.out.println("A pumpa felvétele sikertelen volt, mert már van nálunk pumpa.\n");
            return;
        }else{
            pump = elem.GetPump();
            if (pump == null) {
                System.out.println("A pumpa felvétele sikertelen volt, mert üres a generátor.\n");
                return;
            }
            System.out.println("Felvettük a generátorból a(z) "+pump.getID()+" pumpát!\n");
        }
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
