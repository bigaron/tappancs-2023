public class Plumber extends Player{
    private Pipe pipe;
    private Pump pump;
    public void RepairElement(){}
    public void PlaceDown(){}
    public Pump PickupPump(){
        IO.funcCalled("GetPump()");
        Pump result = elem.GetPump();
        IO.returnCalled("pump");
        return result;
    }
    public void SetPipe(Pipe pipe) {
        this.pipe = pipe;
    }
    public void SetPump(Pump pump) {
        this.pump = pump;
    }
}
