/**
 * A forrás víztovábbítását vizsgáló teszt.
 */
public class SourceForwardwaterTest extends Tester{
    Source s;
    Pipe pipe1;
    Pipe pipen;
    @Override
    public void initializeTest() {
        s = new Source();
        pipe1 = new Pipe();
        pipen = new Pipe();
        s.SetNeighbor(pipe1);
        s.SetNeighbor(pipen);
        pipe1.SetNeighbor(s);
        pipen.SetNeighbor(s);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Source.Step()");
        s.Step();
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "SourceForwardwaterTest";
    }
}
