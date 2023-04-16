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
        IO.funcCalled("pipe.Repair()");
        pipe.Repair();
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "RepairPipeTest";
    }
}
