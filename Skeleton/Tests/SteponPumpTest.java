package Skeleton.Tests;

import Skeleton.src.IO;
import Skeleton.src.Pipe;
import Skeleton.src.Pump;
import Skeleton.src.Saboteur;

/**
 * A pumpára lépést vizsgáló teszt.
 */
public class SteponPumpTest extends Tester{
    Saboteur s;
    Pipe pipe;
    Pump pump;
    @Override
    public void initializeTest() {
        s = new Saboteur();
        pipe = new Pipe();
        pump = new Pump();
        s.SetElem(pipe);
        pipe.SetPlayer(s);
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
        return "SteponPumpTest";
    }
}
