/**
 * A ciszterna víztovábbításához létrehozott teszt.
 */
public class CisternForwardwaterTest extends Tester{
    Pipe pipe;
    Cistern c;
    @Override
    public void initializeTest() {
        pipe = new Pipe();
        c = new Cistern();
        pipe.SetNeighbor(c);
        c.SetNeighbor(pipe);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Cistern.ForwardWater(pipe)");
        c.ForwardWater(pipe);
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "CisternForwardwaterTest";
    }
}
