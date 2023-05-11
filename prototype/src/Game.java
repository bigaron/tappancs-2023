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
    public ArrayList<Saboteur> saboteurs = new ArrayList<>();
    private ArrayList<Plumber> plumbers = new ArrayList<>();
    private ArrayList<Generator> generators = new ArrayList<>();
    public Player activePlayer;
    private boolean plumbersTurn;
    private static int sPoints = 0;
    private static int pPoints = 0;
    private Mode mode;
    public static boolean random = true;

    public Game(){
        mode = Mode.config;
        plumbersTurn = false;
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
        Game game = new Game();      
        game.generate("D:/Negyedik_felev/Projlab/prot/tappancs-2023/prototype/src/testmap.txt"); //TODO ne égessük bele a path-t
        game.activePlayer = game.saboteurs.get(0);
        game.changeState(Mode.play);
        game.Move(0);
        game.changeState(Mode.config);
        game.Save("output.txt");
    }

    public void changeState(Mode mode){ this.mode = mode; }

    private int findSabo(String ID){
        for(int i = 0;i < saboteurs.size(); ++i) if(saboteurs.get(i).getID().equals(ID)) return i;
        return -1;
    }

    private int findPlumb(String ID){
        for(int i = 0;i < plumbers.size(); ++i) if(plumbers.get(i).getID().equals(ID)) return i;
        return -1;
    }

    private int findElem(String ID){
        for(int i = 0; i < desert.size(); ++i) if(desert.get(i).getID().equals(ID)) return i;
        return -1;
    }

    private int findGen(String ID){
        for(int i = 0; i < generators.size(); ++i) if(generators.get(i).getID().equals(ID)) return i;
        return -1;
    }

    private void insertPlayerToElem(int elemIDX, String[] players){
        for(String s: players){
            if(findPlumb(s) != -1) desert.get(elemIDX).AcceptPlayer(plumbers.get(findPlumb(s)));
            else desert.get(elemIDX).AcceptPlayer(saboteurs.get(findSabo(s)));
        }
    }

    private void insertNeighbourToElem(int elemIDX, String[] neighbours){
        for(String s: neighbours){
            if(!s.equals("null")){
                desert.get(elemIDX).SetNeighbor(desert.get(findElem(s)));
            }
        }
    }

    private void setNeighbourAndPlayers(int index, String[] values){
        if(!values[2].equals("null")){ insertPlayerToElem(index, values[2].split(",")); }
        insertNeighbourToElem(index, values[3].split(","));
    }

    private void cisternRelations(String[] parsed){
        int cIDX = findElem(parsed[1]); 
        setNeighbourAndPlayers(cIDX, parsed);
        Cistern c = (Cistern)desert.get(cIDX);
        c.SetGenerator(generators.get(findGen(parsed[4])));
        desert.set(cIDX, c);
    }

    private void pipeRelations(String[] parsed){
        int pIDX = findElem(parsed[1]);
        setNeighbourAndPlayers(pIDX, parsed);
    }

    private void pumpRelations(String[] parsed){
        int pIDX = findElem(parsed[1]);
        setNeighbourAndPlayers(pIDX, parsed);
        if(parsed.length == 5){
            Pump p = (Pump)desert.get(pIDX);
            if(parsed[4].equals("null")) {
                p.SetOutputPipe(null);
                return;
            }
            p.SetOutputPipe((Pipe)desert.get(findElem(parsed[4])));
        }
    }

    private void sourceRelations(String[] parsed){
        int sIDX = findElem(parsed[1]);
        setNeighbourAndPlayers(sIDX, parsed);
    }

    private void generatorRelations(String[] parsed){
        int gIDX = findGen(parsed[1]);
        String[] pumps = parsed[2].split(",");
        for(String s: pumps) if(!s.equals("null"))generators.get(gIDX).AddPump((Pump)desert.get(findElem(s)));;
        generators.get(gIDX).SetCistern((Cistern)desert.get(findElem(parsed[3])));
    }

    private void saboteurRelations(String[] parsed){
        int sIDX = findSabo(parsed[1]);
        saboteurs.get(sIDX).SetElem(desert.get(findElem(parsed[2])));
        if(parsed[3].equals("null")) return;
        saboteurs.get(sIDX).SetPipe((Pipe)desert.get(findElem(parsed[3])));
    }

    private void plumberRelations(String[] parsed){
        int pIDX = findPlumb(parsed[1]);
        plumbers.get(pIDX).SetElem(desert.get(findElem(parsed[2])));
        if(parsed[3].equals("null")) return;
        plumbers.get(pIDX).SetPipe((Pipe)desert.get(findElem(parsed[3])));
        if(parsed[3].equals("null")) return;
        plumbers.get(pIDX).SetPump((Pump)desert.get(findElem(parsed[4])));
    }


    private void readInSelectedObject(String line, boolean relations){
        String[] parsed = line.split("\\+");

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
                    generatorRelations(parsed);
                }
                break;
            }
            case "source": {
                if(!relations){
                    Source s = new Source();
                    s.Read(parsed);
                    desert.add(s);
                }else{
                    sourceRelations(parsed);
                }
                break;
            }
            case "pump": {
                if(!relations){
                    Pump p = new Pump();
                    p.Read(parsed);
                    desert.add(p);
                }else{
                    pumpRelations(parsed);
                }
                break;
            }
            case "pipe": {
                if(!relations){
                    Pipe p = new Pipe();
                    p.Read(parsed);
                    desert.add(p);
                }else{
                    pipeRelations(parsed);
                }
                break;
            }
            case "saboteur": {
                if(!relations){
                    Saboteur p = new Saboteur();
                    p.Read(parsed);
                    saboteurs.add(p);
                }else{
                    saboteurRelations(parsed);
                }
                break;
            }
            case "plumber":{
                if(!relations){
                    Plumber p = new Plumber();
                    p.Read(parsed);
                    plumbers.add(p);
                }else{
                    plumberRelations(parsed);
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
        if(mode != Mode.config) return;
        try {
            File file = new File(new File("prototype", "output"), filename);
            FileWriter writer = new FileWriter(file.getPath());

            writer.write((desert.size() + plumbers.size() + saboteurs.size() + generators.size()) + "\n");

            for(Element elem : desert) {
                elem.Save(writer, true);
            }

            for(Generator generator : generators) {
                generator.Save(writer, true);
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

            for(Generator generator : generators) {
                generator.Save(writer, false);
            }

            for(Saboteur s : saboteurs) {
                s.Save(writer, false);
            }

            for(Plumber p : plumbers) {
                p.Save(writer, false);
            }

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setRandom(boolean value){ if(mode == Mode.config) random = value; }
    public void Move(int direction){
        if(mode == Mode.config) return;
        activePlayer.Move(direction);
    }
    public void leakPipe(){
        if(mode == Mode.config || plumbersTurn || findSabo(activePlayer.getID()) == -1) return;
        activePlayer.SabotagePipe();
    }
    public void repair(){
        if(mode == Mode.config || !plumbersTurn || findPlumb(activePlayer.getID()) == -1) return;
        Plumber p = (Plumber)activePlayer;
        p.RepairElement();
    }
    public void changePumpDir(int direction){
        if(mode == Mode.config) return;
        activePlayer.changePumpDirection(direction);
    }
    public void changeSurface(Modifier mod){
        if(mode == Mode.config) return;
        if(mod == Modifier.Slippery){
            if(plumbersTurn) return;
            Saboteur s = (Saboteur)activePlayer;
            s.MakeSticky();
        }
        activePlayer.MakeSticky();
    }

    public void addPipe(){
        if(mode == Mode.config || !plumbersTurn) return;
        Plumber p = (Plumber) activePlayer;
        p.AddPipe();
    }
     
    public void removePipe(int number){
        if(mode == Mode.config || !plumbersTurn) return;
        Plumber p = (Plumber)activePlayer;
        
        p.RemovePipe(number);
    }

    public void placePump(){
        if(mode == Mode.config || !plumbersTurn) return;
        Plumber p = (Plumber)activePlayer;
        p.PlaceDown();
    }

    public void pickUpPump(){
        if(mode == Mode.config || !plumbersTurn) return;
        Plumber p = (Plumber)activePlayer;
        p.PickupPump();
    }

    public void pickUpPipe(int num){
        if(mode == Mode.config || !plumbersTurn) return;
        Plumber p = (Plumber)activePlayer;
        p.PickUpPipe(num);
    }
}
