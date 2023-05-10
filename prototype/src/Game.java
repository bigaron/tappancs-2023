package prototype.src;
import prototype.src.Elements.*;
import prototype.src.Players.Player;
import prototype.src.Players.Plumber;
import prototype.src.Players.Saboteur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    ArrayList<Element> desert = new ArrayList<>();
    ArrayList<Saboteur> saboteurs = new ArrayList<>();
    ArrayList<Plumber> plumbers = new ArrayList<>();
    int sPoints = 0;
    int pPoints = 0;
    public static boolean random = true;
    public static Modifier parseModifier(String line){
        switch(line){
            case "sticky": return Modifier.Sticky;
            case "slippery": return Modifier.Slippery;
            default: return Modifier.Plain;
        }
    }
    public static int actionCounter = 0;
    public static void main(String[] args){
    }

    public void Save(String filename) {
        try {
            File file = new File("output", filename);
            FileWriter writer = new FileWriter(file);
            for(Element elem : desert) {
                elem.Save(writer, true);
            }
            for(Saboteur s : saboteurs) {
                s.Save(writer, true);
            }
            for(Plumber p : plumbers) {
                p.Save(writer, true);
            }
            writer.write(sPoints + "\n");
            writer.write(pPoints + "\n");

            for(Element elem : desert) {
                elem.Save(writer, false);
            }

            for(Saboteur s : saboteurs) {
                s.Save(writer, false);
            }

            for(Plumber p : plumbers) {
                p.Save(writer, false);
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
