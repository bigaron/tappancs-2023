import java.util.ArrayList;

/**
 * A cső lecsatolását vizsgáló teszt.
 */
public class TakeoffPipeTest extends Tester{
    private Plumber plumb;
    private Pump pump;
    private Pipe pipe;

    @Override
    public void initializeTest() {
        plumb = new Plumber();
        pump = new Pump();
        pipe = new Pipe();

        ArrayList<String> out = new ArrayList<>();
        out.add("Van a játékosnál pumpa vagy cső? (0: nem, 1: igen)");
        out.add("Áll valaki a csövön? (0: nem, 1: igen)");
        out.add("A kimenetet csatlakoztatom le? (0: nem, 1: igen)");
        IO.initializeTest(out);
        IO.getInput();

        plumb.SetElem(pump);
        pump.SetNeighbor(pipe);
        pipe.SetNeighbor(pump);
        pump.SetPlayer(plumb);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Plumber.RemovePipe(pipe)");
        plumb.RemovePipe(pipe);
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "TakeoffPipeTest";
    }
}
