package prototype.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import prototype.src.Elements.*;

/**
 * A generátort reprezentáló osztály.
 */
public class Generator implements Steppable{
    private Cistern cistern;
    private List<Pump> pumps = new ArrayList<Pump>();
    private String ID;
    private static long counter = 0;

    public String getID(){ return ID; }
    public Generator(){
        ID = "generator" + ++counter;
    }
    /**
     * Elemet generál, vagy pumpát vagy csövet.
     * @return a generált elem.
     */
    public Element GenerateElem(){
        Element result;
        if(!IO.input.get(1)) {
            IO.funcCalled("Generator.GeneratePipe()");
            result = this.GeneratePipe();
            IO.returnCalled("pipe");
        } else {
            IO.funcCalled("Generator.GeneratePump()");
            result = this.GeneratePump();
            IO.returnCalled("pump");
        }

        return result;
    }

    /**
     * A Steppable interface függvényének implementálása, ez váltja ki az elemgenerálást.
     */
    public void Step(){
        if(Game.random) {
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
    public Pump RequestPump(){return new Pump();}

    /**
     * Beállítja a hozzá tartozó ciszternát.
     * @param c a hozzá tartozó ciszterna
     */
    public void SetCistern(Cistern c) { cistern = c; }

    /**
     * Generál egy új pumpát.
     * @return az új pumpa
     */
    public Pump GeneratePump() {
        return new Pump();
    }

    /**
     * Generál egy új csövet.
     * @return az új cső
     */
    public Pipe GeneratePipe() {
        return new Pipe();
    }

    public void Read(String[] parsed){
        if(parsed[0] != "generator") return;
        ID = parsed[1];
    }
}
