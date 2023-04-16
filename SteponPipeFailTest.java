/**
 * A meghiúsúlt "csőre lépés" műveletet vizsgáló teszt.
 */
public class SteponPipeFailTest extends Tester{
    Saboteur s;
    Pump pump;
    Pipe pipe;
    Plumber p;
    @Override
    public void initializeTest() {
        s = new Saboteur();
        pump = new Pump();
        pipe = new Pipe();
        p = new Plumber();
        s.SetElem(pump);
        pump.SetPlayer(s);
        pipe.SetPlayer(p);
        p.SetElem(pipe);
        pipe.SetNeighbor(pump);
        pump.SetNeighbor(pipe);
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
        return "SteponPipeFailTest";
    }
}
