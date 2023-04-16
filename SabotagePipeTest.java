public class SabotagePipeTest extends Tester{
    Saboteur s;
    Pipe pipe;
    @Override
    public void initializeTest() {
        s = new Saboteur();
        pipe = new Pipe();
        s.SetElem(pipe);
        pipe.SetPlayer(s);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        IO.funcCalled("Pipe.SabotagePipe()");
        pipe.SabotagePipe();
        IO.returnCalled("void");
    }

    @Override
    public String toString() {
        return "SabotagePipeTest";
    }
}
