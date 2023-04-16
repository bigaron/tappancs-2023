public class PlacedownPlumpTest extends Tester{
    Plumber plumber;
    Pump pump1;
    Pump pump2;
    Pump pump3;
    Pipe pipe;
    @Override
    public void initializeTest() {
        plumber = new Plumber();
        pump1 = new Pump();
        pump2 = new Pump();
        pump3 = new Pump();
        pipe = new Pipe();
        plumber.SetElem(pipe);
        plumber.SetPump(pump3);
        pipe.SetPlayer(plumber);
        pipe.SetNeighbor(pump1);
        pipe.SetNeighbor(pump2);
        pump1.SetNeighbor(pipe);
        pump2.SetNeighbor(pipe);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Pipe.Split(pump3)");
        pipe.Split(pump3);
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "PlacedownPlumpTest";
    }
}
