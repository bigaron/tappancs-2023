package prototype.src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import prototype.src.Elements.*;

/**
 * A generátort reprezentáló osztály.
 */
public class Generator implements Steppable{
    private static int counter = 0;
    private String ID;
    private Cistern cistern;
    private List<Pump> pumps = new ArrayList<Pump>();

    public Generator() {
        ++counter;
        ID = "generator" + counter;
    }

    public String getID() {
        return ID;
    }

    /**
     * Elemet generál, vagy pumpát vagy csövet.
     * @return a generált elem.
     */
    public void GenerateElem(){
        Element result;
        if(Game.random) {
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt();
            if(randomNumber % 5 == 0) {
                GeneratePump();
            } else if(randomNumber % 6 == 0) {
                GeneratePipe();
            }
        } else {
            if(Game.globalActionCounter % 2 == 0) {
                GeneratePump();
            } else {
                GeneratePipe();
            }
        }
    }

    /**
     * A Steppable interface függvényének implementálása, ez váltja ki az elemgenerálást.
     */
    public void Step(){
        if(!Game.random) {
            GenerateElem();
        } else {
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt();
            if(randomNumber % 5 == 0) {
                GenerateElem();
            }
        }
    }

    /**
     * Visszaad egy pumpát ha már van legenerálva a generátorban.
     * @return egy pumpa ha van
     */
    public Pump RequestPump(){
        if(pumps.size() != 0) {
            return pumps.remove(pumps.size()-1);
        }
        return null;
    }

    /**
     * Beállítja a hozzá tartozó ciszternát.
     * @param c a hozzá tartozó ciszterna
     */
    public void SetCistern(Cistern c) { cistern = c; }

    /**
     * Generál egy új pumpát.
     * @return az új pumpa
     */
    public void GeneratePump() {
        Pump result = new Pump();
        Game.desert.add(Cistern.counter + Source.counter + Pump.counter - 1, result);
        pumps.add(result);
        //nem ad vissza semmit
    }

    /**
     * Generál egy új csövet.
     * @return az új cső
     */
    public Pipe GeneratePipe() {
        Pipe result = new Pipe();
        Game.desert.add( Cistern.counter + Source.counter + Pump.counter + Pipe.counter - 1, result);
        cistern.SetNeighbor(result);
        result.SetNeighbor(cistern);
        return result;
    }


    public void AddPump(Pump pump){
        pumps.add(pump);
    }

    public void Save(FileWriter writer, boolean objectState) {
        try {
            if(objectState) {
                writer.write("generator+" + ID + "\n");
            } else {
                writer.write("generator+" + ID);
                for(int i = 0; i < pumps.size(); ++i) {
                    if(i == 0) {
                        writer.write("+" + pumps.get(i).getID());
                    } else {
                        writer.write("," + pumps.get(i).getID());
                    }
                }
                if(pumps.size() == 0) {
                    writer.write("+null");
                }
                writer.write("+" + cistern.getID() + "\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void Read(String[] parsed){
        if(!parsed[0].equals("generator")) return;
        ID = parsed[1];
    }

    public void list(){
        System.out.println("\nGenerator");
        System.out.println("id: " + getID());
        if(cistern == null) System.out.println("cistern: null" );
        else System.out.println("cistern: " + cistern.getID());
        System.out.print("pipes: [" );
        for(Pump p: pumps){
           if(p != null) System.out.print(p.getID() + ",");
           else System.out.print("null,");
        }
        System.out.println("]" );
    }
}
