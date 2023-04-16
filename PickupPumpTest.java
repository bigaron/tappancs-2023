import java.util.ArrayList;

/**
 * Egy ciszternától való pumpa felvételét vizsgáló teszt.
 */
public class PickupPumpTest extends Tester{
    Plumber plumber;
    Cistern cistern;
    Generator g;
    @Override
    public void initializeTest() {
        plumber = new Plumber();
        cistern = new Cistern();
        g = new Generator();
        plumber.SetElem(cistern);
        cistern.SetPlayer(plumber);
        cistern.SetGenerator(g);
        g.SetCistern(cistern);
        ArrayList<String> out = new ArrayList<>();
        out.add(new String("Van a játékosnál pumpa vagy cső? (0: nem, 1: igen)"));
        IO.initializeTest(out);
        IO.getInput();
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Plumber.PickupPump()");
        plumber.PickupPump();
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "PickupPumpTest";
    }
}
