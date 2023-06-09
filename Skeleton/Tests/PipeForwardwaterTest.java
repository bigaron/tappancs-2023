package Skeleton.Tests;
import java.util.ArrayList;

import Skeleton.src.IO;
import Skeleton.src.Pipe;
import Skeleton.src.Pump;

/**
 * A cső víztovábbítását vizsgáló teszt.
 */
public class PipeForwardwaterTest extends Tester{
    Pump pump1;
    Pump pump2;
    Pipe pipe;
    @Override
    public void initializeTest() {
        pump1 = new Pump();
        pump2 = new Pump();
        pipe = new Pipe();
        pump1.SetNeighbor(pipe);
        pipe.SetNeighbor(pump1);
        pump2.SetNeighbor(pipe);
        pipe.SetNeighbor(pump2);
        ArrayList<String> out = new ArrayList<>();
        out.add(new String("Lyukas-e a cső? (0: nem, 1: igen)"));
        out.add(new String("Le van csatlakoztatva a cső? (0: nem, 1: igen)"));
        out.add(new String("Honnan jön a víz? (0: egyik irány, 1: másik irány)"));
        IO.initializeTest(out);
        IO.getInput();
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Pipe.ForwardWater(pump1)");
        pipe.ForwardWater(pump1);
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "PipeForwardwaterTest";
    }
}
