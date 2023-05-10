package prototype.src.Elements;

import prototype.src.Generator;

/**
 * A ciszternát reprezentáló osztály.
 */

public class Cistern extends Node{
    private Generator generator;
    private static long counter = 0;

    public Cistern(){
        ID = "cistern" + ++counter;
    }

    /**
     * A víz továbbítását végző függvény. A víz útja itt ér véget, már nem hív tovább semmit.
     * @param elem
     * TODO: pontadas a szereloknek
     */
   @Override
   public void ForwardWater(Element elem){}

    /**
     * A ciszternához tartozó generátorból felvesz egy pumpát.
     * @return visszaadja a pumpát, vagy ha nincs épp pumpa, null-t
     */
    @Override
    public Pump GetPump(){
       return generator.GeneratePump();
    }

    /**
     * Beállítja a ciszternához tartozó generátort.
     * @param g a generátor amit be szeretnénk állítani.
     */
    public void SetGenerator(Generator g) {
       generator = g;
    }
}
