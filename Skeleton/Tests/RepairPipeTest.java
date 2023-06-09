package Skeleton.Tests;

import Skeleton.src.IO;
import Skeleton.src.Pipe;
import Skeleton.src.Plumber;

/**
 * A cső megjavítását vizsgáló teszt.
 */
public class RepairPipeTest extends Tester{
    Plumber plumber;
    Pipe pipe;
    @Override
    public void initializeTest() {
        plumber = new Plumber();
        pipe = new Pipe();
        plumber.SetElem(pipe);
        pipe.SetPlayer(plumber);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Pipe.Repair()");
        pipe.Repair();
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "RepairPipeTest";
    }
}
