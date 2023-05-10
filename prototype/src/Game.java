package prototype.src;
import prototype.src.Elements.*;
import prototype.src.Players.Player;
import prototype.src.Players.Plumber;
import prototype.src.Players.Saboteur;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

enum Mode{
    config, play
}

public class Game {
    private ArrayList<Element> desert = new ArrayList<>();
    private ArrayList<Saboteur> saboteurs = new ArrayList<>();
    private ArrayList<Plumber> plumbers = new ArrayList<>();
    private ArrayList<Generator> generators = new ArrayList<>();
    private static int sPoints = 0;
    private static int pPoints = 0;
    private Mode mode;
    public static boolean random = true;

    public Game(){
        mode = Mode.config;
    }

    public static void increasePoints(int team) {
        if(team == 0) {
            ++sPoints;
        } else {
            ++pPoints;
        }
    }

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

    public void changeState(Mode mode){ this.mode = mode; }

    private int findSabo(String ID){
        for(int i = 0;i < saboteurs.size(); ++i) if(saboteurs.get(i).getID() == ID) return i;
        return -1;
    }

    private int findPlumb(String ID){
        for(int i = 0;i < plumbers.size(); ++i) if(plumbers.get(i).getID() == ID) return i;
        return -1;
    }

    private int findElem(String ID){
        for(int i = 0; i < desert.size(); ++i) if(desert.get(i).getID() == ID) return i;
        return -1;
    }

    private void insertPlayerToElem(int elemIDX, Player p){
        desert.get(elemIDX).AcceptPlayer(p);
    }

    private void cisternRelations(String[] parsed){
        for(Element elem: desert) if(elem.getID() == parsed[1]);
        if(parsed[2] != "null"){
            String[] standers = parsed[2].split(",");
            for(String s: standers){
                if(findPlumb(s) != -1) insertPlayerToElem(findElem(parsed[1]), plumbers.get(findPlumb(s)));
                else insertPlayerToElem(findElem(parsed[1]), saboteurs.get(findSabo(s)));
            }
        }
    }

    private void readInSelectedObject(String line, boolean relations){
        String[] parsed = line.split("+");

        switch(parsed[0]){
            case "cistern":{
                if(!relations){
                    Cistern c = new Cistern();
                    c.Read(parsed);
                    desert.add(c);
                }else{
                    cisternRelations(parsed);
                }
                break;
            }
            case "generator": {
                if(!relations){
                    Generator g = new Generator();
                    g.Read(parsed);
                    generators.add(g);
                }else{
                    
                }
                break;
            }
            case "source": {
                if(!relations){
                    Source s = new Source();
                    s.Read(parsed);
                    desert.add(s);
                }else{
                    
                }
            }
            case "pump": {
                if(!relations){
                    Pump p = new Pump();
                    p.Read(parsed);
                    desert.add(p);
                }else{
                    
                }
                break;
            }
            case "pipe": {
                if(!relations){
                    Pipe p = new Pipe();
                    p.Read(parsed);
                    desert.add(p);
                }else{
                    
                }
                break;
            }
            case "saboteur": {
                if(!relations){
                    Saboteur p = new Saboteur();
                    p.Read(parsed);
                    saboteurs.add(p);
                }else{
                    
                }
                break;
            }
            case "plumber":{
                if(!relations){
                    Plumber p = new Plumber();
                    p.Read(parsed);
                    plumbers.add(p);
                }else{
                    
                }
                break;
            }
        }
    }

    public void generate(String path){
        if(mode == Mode.play) return;
        try{
            Scanner scn;
            File src = new File(path);
            scn = new Scanner(src);
            String line = scn.nextLine();
            int n = Integer.parseInt(line);
            for(int i = 0; i < n; ++i){
                line = scn.nextLine();
                readInSelectedObject(line, false);
            }
            
            line = scn.nextLine();
            sPoints = Integer.parseInt(line);
            line = scn.nextLine();
            pPoints = Integer.parseInt(line);

            for(int i = 0; i < n; ++i){
                line = scn.nextLine();
                readInSelectedObject(line, true);
            }

            scn.close();
        }catch(FileNotFoundException fnfEx){
            fnfEx.printStackTrace();
        }
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
