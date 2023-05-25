package prototype.src;
import prototype.src.Elements.*;
import prototype.src.Players.Player;
import prototype.src.Players.Plumber;
import prototype.src.Players.Saboteur;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import java.io.IOException;
import java.util.ArrayList;

enum Mode {
    config, play
}

public class Game {
    public static ArrayList<Element> desert = new ArrayList<>();
    public ArrayList<Saboteur> saboteurs = new ArrayList<>();
    private ArrayList<Plumber> plumbers = new ArrayList<>();
    private ArrayList<Generator> generators = new ArrayList<>();
    public Player activePlayer;
    private boolean plumbersTurn;
    private static int sPoints = 0;
    private static int pPoints = 0;
    private Mode mode;
    public static boolean random = false;

    public Game(){
        mode = Mode.play;
        plumbersTurn = false;
        generate("testmap.txt");
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
    public static int globalActionCounter = 0; //a random értékekhez determinizációjához kell kell
    public static int actionCounter = 4; //induljunk négyről és dekrementáljunk
    public static boolean successfulCmd = true;
    /* 
    public static void main(String[] args){
        //OLD MAIN
        Game game = new Game();

        while(true) {
            while (game.mode == Mode.config) {
                System.out.println("Várom a parancsot gazdám!");
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                String[] splitted = input.split(" ");

                switch (splitted[0]) {
                    case "changeState" -> game.changeState(splitted[1]);
                    case "generate" -> {
                        game.resetCounters();
                        game.generate(splitted[1]);
                        game.reset();
                    }
                    case "save" -> game.Save(splitted[1]);
                    case "setRandom" -> game.setRandom(Boolean.parseBoolean(splitted[1]));
                    case "exit" -> System.exit(0);
                    default -> System.out.println("Rossz a parancsod drága");
                }

            }

            //itt valahol endgame ->> endgame gamemodeot vált
            while (game.mode == Mode.play) { //ugye a flaget ellenőrzik a függvények. akkor itt attól függően h kinek a turnje van, végigmegyünk a dömbökön és mindenki léphet 4et
                if (game.activePlayer == null) {
                    System.out.println(game.saboteurs.get(0).getID() + " játékos következik.\n");
                    game.activePlayer = game.saboteurs.get(0);
                }
                //actionloop
                while (actionCounter != 0) {
                    System.out.println("Várom a parancsot gazdám!");
                    Scanner in = new Scanner(System.in);
                    String input = in.nextLine();
                    String[] cmd = input.split(" ");


                    switch (cmd[0]) { //ezeknél hibakezelés ugye van a meghívott függvényekben?
                        case "move" -> {
                            if(cmd.length == 1) {
                                successfulCmd = false;
                                continue; 
                            }
                            game.Move(Integer.parseInt(cmd[1]));
                        }
                        case "Move" -> {
                            if(cmd.length == 1) {
                                successfulCmd = false;
                                continue; 
                            }
                            game.Move(Integer.parseInt(cmd[1]));
                        }
                        case "leakPipe" -> game.leakPipe();
                        case "repair" -> game.repair();
                        case "changePumpDir" -> {
                            if(cmd.length == 1) {
                                successfulCmd = false;
                                continue; 
                            } 
                            game.changePumpDir(Integer.parseInt(cmd[1]));
                        }
                        case "changeSurface" ->{
                            if(cmd.length == 1) {
                                successfulCmd = false;
                                continue; 
                            } 
                            game.changeSurface(parseModifier(cmd[1]));
                        }
                        case "addPipe" -> game.addPipe();
                        case "removePipe" -> {
                            if(cmd.length == 1) {
                                successfulCmd = false;
                                continue; 
                            }
                            game.removePipe(Integer.parseInt(cmd[1]));
                        }
                        case "placePump" -> game.placePump();
                        case "pickUpPump" -> game.pickUpPump();
                        case "pickUpPipe" -> {
                            if(cmd.length == 1) {
                                successfulCmd = false;
                                continue; 
                            }
                            game.pickUpPipe(Integer.parseInt(cmd[1]));
                        }
                        case "changeState" -> {
                            if(cmd.length == 1) {
                                successfulCmd = false;
                                continue; 
                            } 
                            game.changeState(cmd[1]);
                        }
                        case "endTurn" -> game.endTurn();
                        case "list" -> {
                            if(cmd.length == 2) game.List(cmd[1]);
                            else game.List("");
                            successfulCmd = false;
                        }
                        default -> {
                            System.out.println("Érvényes parancsot adjál mert nem leszünk jóban.");
                            successfulCmd = false;
                        }
                    }
                    if (game.mode == Mode.config) break;
                    if(actionCounter != 0 && successfulCmd)  {
                        --actionCounter;
                        ++globalActionCounter;
                    } if(successfulCmd){
                        //step az összesre desert + generators
                        game.desert.forEach(Element::Step);
                        game.generators.forEach(Generator::Step);
                    }else {
                        successfulCmd = true;
                    }
                }
                if (game.mode == Mode.config) break;
                actionCounter = 4;
                //kövi játékosra léptetünk.
                if (game.plumbersTurn) {
                    int idx = game.plumbers.lastIndexOf((Plumber) game.activePlayer);
                    ++idx;
                    if (idx < game.plumbers.size()) {
                        System.out.println("Kör vége, a(z) "+game.plumbers.get(idx).getID() +" játékos következik.\n");
                        game.activePlayer = game.plumbers.get(idx); //finito ha minden ok
                    } else { //ha vége van a tömbnek -->> csapatváltás
                        game.plumbersTurn = false;
                        System.out.println("Kör vége, a(z) "+game.saboteurs.get(0).getID() +" játékos következik.\n");
                        game.activePlayer = game.saboteurs.get(0);  //itt indexelős hibakezelés??
                    }
                } else { //ugyan az csak másik tömbökkel
                    int idx = game.saboteurs.lastIndexOf((Saboteur) game.activePlayer);
                    ++idx;
                    if (idx < game.saboteurs.size()) {
                        System.out.println("Kör vége, a(z) "+game.saboteurs.get(idx).getID() +" játékos következik.\n");
                        game.activePlayer = game.saboteurs.get(idx);
                    } else {
                        game.plumbersTurn = true;
                        System.out.println("Kör vége, a(z) "+game.plumbers.get(0).getID() +" játékos következik.\n");
                        game.activePlayer = game.plumbers.get(0);
                    }
                }

            }

        }

        //OLD OLD MAIN
        //innentől ami volt, csak nem töröltem ki
        /*game.generate("testmap.txt");
        game.activePlayer = game.saboteurs.get(0);
        game.changeState(Mode.play);
        game.Move(0);
        game.changeState(Mode.config);
        game.Save("output.txt");

        //ACTUAL MAIN
        AppWindow app = new AppWindow(); 
        app.setVisible(true);
    }
    */
    public void parseInput(String input){
        if (activePlayer == null) {
            System.out.println(saboteurs.get(0).getID() + " játékos következik.\n");
            activePlayer = saboteurs.get(0);
        }

        //actionloop
        String[] cmd = input.split(" ");

        switch (cmd[0]) { //ezeknél hibakezelés ugye van a meghívott függvényekben?
        case "move" -> {
            if(cmd.length == 1) {
                successfulCmd = false;
            }
            Move(Integer.parseInt(cmd[1]));
        }
        case "Move" -> {
            if(cmd.length == 1) {
                successfulCmd = false;
            }
            Move(Integer.parseInt(cmd[1]));
        }
        case "leakPipe" -> leakPipe();
        case "repair" -> repair();
        case "changePumpDir" -> {
            if(cmd.length == 1) {
                successfulCmd = false;
            } 
            changePumpDir(Integer.parseInt(cmd[1]));
        }
        case "changeSurface" ->{
            if(cmd.length == 1) {
                successfulCmd = false;
            } 
            changeSurface(parseModifier(cmd[1]));
        }
        case "addPipe" -> addPipe();
        case "removePipe" -> {
            if(cmd.length == 1) {
                successfulCmd = false; 
            }
            removePipe(Integer.parseInt(cmd[1]));
        }
        case "placePump" -> placePump();
        case "pickUpPump" -> pickUpPump();
        case "pickUpPipe" -> {
            if(cmd.length == 1) {
                successfulCmd = false;
            }
            pickUpPipe(Integer.parseInt(cmd[1]));
        }
        case "changeState" -> {
            if(cmd.length == 1) {
                successfulCmd = false;
            } 
            changeState(cmd[1]);
        }
        case "endTurn" -> endTurn();
        // case "list" -> {
        //     if(cmd.length == 2) List(cmd[1]);
        //     else List("");
        //     successfulCmd = false;
        // }
        default -> {
            System.out.println("Érvényes parancsot adjál mert nem leszünk jóban.");
            successfulCmd = false;
        }
        }
        if(actionCounter != 0 && successfulCmd)  {
            --actionCounter;
            ++globalActionCounter;
        } if(successfulCmd){
            //step az összesre desert + generators
            desert.forEach(Element::Step);
            generators.forEach(Generator::Step);
        }else {
            successfulCmd = true;
        }
        if(actionCounter == 0){
            actionCounter = 4;
            //kövi játékosra léptetünk.
            if (plumbersTurn) {
                int idx = plumbers.lastIndexOf((Plumber) activePlayer);
                ++idx;
                if (idx < plumbers.size()) {
                    System.out.println("Kör vége, a(z) "+ plumbers.get(idx).getID() +" játékos következik.\n");
                    activePlayer = plumbers.get(idx); //finito ha minden ok
                } else { //ha vége van a tömbnek -->> csapatváltás
                    plumbersTurn = false;
                    System.out.println("Kör vége, a(z) "+ saboteurs.get(0).getID() +" játékos következik.\n");
                    activePlayer = saboteurs.get(0);  //itt indexelős hibakezelés??
                }
            } else { //ugyan az csak másik tömbökkel
                int idx = saboteurs.lastIndexOf((Saboteur) activePlayer);
                ++idx;
                if (idx < saboteurs.size()) {
                    System.out.println("Kör vége, a(z) "+ saboteurs.get(idx).getID() +" játékos következik.\n");
                    activePlayer = saboteurs.get(idx);
                } else {
                    plumbersTurn = true;
                    System.out.println("Kör vége, a(z) "+ plumbers.get(0).getID() +" játékos következik.\n");
                    activePlayer =  plumbers.get(0);
                }
            }   
        }
    }

    public void resetCounters(){
        Cistern.resetCounter();
        Pipe.resetCounter();
        Pump.resetCounter();
        Source.resetCounter();
        Plumber.resetCounter();
        Saboteur.resetCounter();
        Generator.resetCounter();
    }

    public void changeState(Mode mode){ 
        if (mode == Mode.play){
            System.out.println("Konfiguráció vége, kezdődhet a játék.\n");
        }
        this.mode = mode; }

    public void changeState(String str){
        if(Objects.equals(str, "config")) {
            this.mode = Mode.config;
            System.out.println("Konfigurációs módra váltottál.\n");
        }
        else if(Objects.equals(str, "play")) {
            this.mode = Mode.play;
            System.out.println("Játék módra váltottál.\n");
        }
        else {
            System.out.println("Rossz mod");
            System.out.println("Ilyen mód nem létezik, kérlek válassz a play/config módok közül.\n");
        }
    }

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

    public void reset(){
        activePlayer = saboteurs.get(0);
        actionCounter = 4;
        globalActionCounter = 0;
        plumbersTurn = false;
    }

    public void generate(String path){
        try{
            generators.clear();
            saboteurs.clear();
            desert.clear();
            plumbers.clear();


            Scanner scn;
            File src = new File(new File("prototype", "src"), path);
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
        if(mode == Mode.config ) return; //lyukaszthat plumber is
        activePlayer.SabotagePipe();
    }
    public void repair(){
        if(mode == Mode.config || !plumbersTurn || findPlumb(activePlayer.getID()) == -1)return;
        if(!plumbersTurn) System.out.println("A(z) elem nem javult meg, mert az csak szerelő esetén lép érvénybe.\n"); //TODO ez itt picit furi a kimeneti nyelvhe képest de idk hogy kéne máshogy
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
            if(plumbersTurn) {
                System.out.println("A felület nem változott meg, mert szerelővel próbáltuk csúszóssá tenni a felületet.\n");
                return;
            }
            Saboteur s = (Saboteur)activePlayer;
            s.MakeSlippery();
        }else if(mod == Modifier.Sticky)
            activePlayer.MakeSticky();
        else
            System.out.println("A felület nem változott meg, mert érvénytelen input.\n");

    }

    public void addPipe(){
        if(mode == Mode.config) return;
        activePlayer.AddPipe();
    }
     
    public void removePipe(int number){
        if(mode == Mode.config ) return;
        //Plumber p = (Plumber)activePlayer;
        
        //p.RemovePipe(number);
        activePlayer.RemovePipe(number);
    }

    public void placePump(){
        if(mode == Mode.config || !plumbersTurn) {
            if(!plumbersTurn)
                System.out.println("A pumpa lerakása sikertelen volt, mert nem szerelővel próbálkoztunk.\n");
            return;
        }
        Plumber p = (Plumber)activePlayer;
        p.PlaceDown();
    }

    public void pickUpPump(){
        if(mode == Mode.config || !plumbersTurn) {
            if(!plumbersTurn)
                System.out.println("Nem sikerült pumpát felvennünk, mert nem szerelővel próbálkoztunk.\n");
            return;
        }
        Plumber p = (Plumber)activePlayer;
        p.PickupPump();
    }

    public void pickUpPipe(int num){
        if(mode == Mode.config || !plumbersTurn) {
            if(!plumbersTurn)
                System.out.println("A(z) "+num+" csövet nem sikerült felvenni, mert nem szerelővel próbálkoztunk.\n");
            return;
        }
        Plumber p = (Plumber)activePlayer;
        p.PickUpPipe(num);
    }

    public void endTurn(){
        if(mode == Mode.config) return;
        actionCounter = 0;
        globalActionCounter++;
    }

    public void List(String flag){
        if(flag.equals("surround")){
            activePlayer.list();
            return;
        }

        if(flag.equals("")){
            for(Element elem: desert) elem.list();
            for(Saboteur s: saboteurs) s.list();
            for(Plumber p: plumbers) p.list();
            for(Generator g: generators) g.list();
            return;
        }

        if(findElem(flag) != - 1){
            desert.get(findElem(flag)).list();
            return;
        }
        if(findGen(flag) != - 1){
            generators.get(findGen(flag)).list();
            return;
        }
        if(findPlumb(flag) != - 1){
            plumbers.get(findPlumb(flag)).list();
            return;
        }
        if(findSabo(flag) != - 1){
            saboteurs.get(findSabo(flag)).list();
            return;
        }
    }
}
