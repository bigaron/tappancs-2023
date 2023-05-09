package prototype.src;

import java.util.ArrayList;
import java.util.List;
import prototype.src.Elements.*;

/**
 * A generátort reprezentáló osztály.
 */
public class Generator implements Steppable{
    private Cistern cistern;
    private List<Pump> pumps = new ArrayList<Pump>();

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
        if(!IO.input.get(0))
            return;
        else {
            IO.funcCalled("Generator.GenerateElem()");
            this.GenerateElem();
            if(IO.input.get(1))
                IO.returnCalled("pump");
            else
                IO.returnCalled("pipe");
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
}
