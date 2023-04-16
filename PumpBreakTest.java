import java.util.ArrayList;

public class PumpBreakTest extends Tester{
    Pump pump;
    @Override
    public void initializeTest() {
        pump = new Pump();
        ArrayList<String> out = new ArrayList<>();
        out.add(new String("Tönkrement-e véletlenszerűen a pumpa? (0: nem, 1: igen)"));
        IO.initializeTest(out);
        IO.getInput();
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Pump.Step()");
        pump.Step();
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "PumpBreakTest";
    }
}
