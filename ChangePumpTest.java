public class ChangePumpTest extends Tester{
    Plumber plumber;
    Pump pump;
    Pipe pipe;
    @Override
    public void initializeTest() {
        plumber = new Plumber();
        pump = new Pump();
        pipe = new Pipe();
        plumber.SetElem(pump);
        pump.SetPlayer(plumber);
        pump.SetNeighbor(pipe);
        pipe.SetNeighbor(pump);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Plumber.ChangePumpDirection(1)");
        plumber.ChangePumpDirection(0);
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "ChangePumpTest";
    }
}
