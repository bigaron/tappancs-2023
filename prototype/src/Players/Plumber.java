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

    public static void resetCounter(){counter = 0;}

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
    public Pump getPump(){return pump;}
    public void PickUpPipe(int dir) {
        if (pipe != null){
            System.out.println("A cső felvétele sikertelen volt, mert már van nálunk cső.\n");
             return;
        } else if(pump != null) {
            System.out.println("A cső felvétele sikertelen volt, mert már van nálunk pumpa.\n");
            return;
        }
        Pipe p = (Pipe)elem.GetNeighbor(dir);
        boolean successful = elem.GetPipe(p);
        if(successful) {
            this.pipe = p;
            System.out.println("A(z) "+this.pipe.getID()+" cső a szerelő kezébe került.\n");
        }
    }

    /**
     * Felvesz egy pumpát
     */
    public void PickupPump(){
        if(pump != null) {
            System.out.println("A pumpa felvétele sikertelen volt, mert már van nálunk pumpa.\n");
            return;
        } else if(pipe != null) {
            System.out.println("Nem sikerült pumpát felvennünk, mert volt már nálunk cső.\n");
            return;
        }

        else{
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

    @Override
    public void list(){
        System.out.println("\nPlumber");
        System.out.println("id: " + getID());
        System.out.println("elem: " + elem.getID());
        if(pump != null)System.out.println("pump: " + pump.getID());
        else System.out.println("pump: null");
        if(pipe != null) System.out.println("pipe: " + pipe.getID());
        else System.out.println("pipe: null");
    }
}
