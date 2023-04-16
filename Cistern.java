/**
 * A ciszternát reprezentáló osztály.
 */
public class Cistern extends Node{
   private Generator generator;

    /**
     * A víz továbbítását végző függvény. A víz útja itt ér véget, már nem hív tovább semmit.
     * @param elem
     */
   @Override
   public void ForwardWater(Element elem){}

    /**
     * A ciszternához tartozó generátorból felvesz egy pumpát.
     * @return visszaadja a pumpát, vagy ha nincs épp pumpa, null-t
     */
    @Override
    public Pump GetPump(){
       if(!IO.input.get(0)) {
           IO.funcCalled("Generator.RequestPump()");
           Pump pump = generator.RequestPump();
           IO.returnCalled("pump");
           return pump;
       } else {
           return null;
       }
    }

    /**
     * Beállítja a ciszternához tartozó generátort.
     * @param g a generátor amit be szeretnénk állítani.
     */
    public void SetGenerator(Generator g) {
       generator = g;
    }
}
