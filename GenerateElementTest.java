import java.util.ArrayList;

public class GenerateElementTest extends Tester{
    Generator g;
    @Override
    public void initializeTest() {
        g = new Generator();
        ArrayList<String> out = new ArrayList<>();
        out.add(new String("Generálódik elem? (0: nem, 1: igen)"));
        out.add(new String("Mi generálódik? (0: cső, 1: pumpa)"));
        IO.initializeTest(out);
        IO.getInput();
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Generator.Step()");
        g.Step();
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "GenerateElementTest";
    }
}
