/**
 * A szerelőt reprezentáló osztály, a játékos leszármazottja.
 */
public class Plumber extends Player{
    private Pipe pipe;
    private Pump pump;
    public void RepairElement(){}
    public void PlaceDown(){}

    /**
     * Pumpa felvétele.
     * @return a felvett pumpa
     */
    public Pump PickupPump(){
        IO.funcCalled("Element.GetPump()");
        Pump result = elem.GetPump();
        IO.returnCalled("pump");
        return result;
    }

    /**
     * Beállítja a felvett csövet az "inventory"-jában.
     * @param pipe a felvett cső
     */
    public void SetPipe(Pipe pipe) {
        this.pipe = pipe;
    }

    /**
     * Beállítja a felvett pumpát az "inventory"-jában.
     * @param pump a felvett pumpa
     */
    public void SetPump(Pump pump) {
        this.pump = pump;
    }
}
