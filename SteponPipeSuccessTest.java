public class SteponPipeSuccessTest extends Tester{
    Saboteur s;
    Pump pump;
    Pipe pipe;
    @Override
    public void initializeTest() {
        s = new Saboteur();
        pump = new Pump();
        pipe = new Pipe();
        s.SetElem(pump);
        pump.SetPlayer(s);
        pump.SetNeighbor(pipe);
        pipe.SetNeighbor(pump);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Saboteur.Move(1)");
        s.Move(0);
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "SteponPipeSuccessTest";
    }
}
