import java.util.ArrayList;

/**
 * A pumpa víztovábbítását vizsgáló teszt.
 */
public class PumpForwardwaterTest extends Tester{
    Pipe inputPipe;
    Pipe outputPipe;
    Pipe pipen;
    Pump p;
    @Override
    public void initializeTest() {
        inputPipe = new Pipe();
        outputPipe = new Pipe();
        pipen = new Pipe();
        p = new Pump();
        inputPipe.SetNeighbor(p);
        outputPipe.SetNeighbor(p);
        pipen.SetNeighbor(p);
        p.SetNeighbor(inputPipe);
        p.SetNeighbor(outputPipe);
        p.SetNeighbor(pipen);
        p.SetOutputPipe(outputPipe);
        ArrayList<String> out = new ArrayList<>();
        out.add(new String("Működik a pumpa? (0: nem, 1: igen)"));
        out.add(new String("Van víz a tartályban? (0: nem, 1: igen)"));
        out.add(new String("Az outputról érkezik a víz? (0: nem, 1: igen)"));
        IO.initializeTest(out);
        IO.getInput();
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Pump.ForwardWater(inputPipe)");
        p.ForwardWater(inputPipe);
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "PumpForwardwaterTest";
    }
}
