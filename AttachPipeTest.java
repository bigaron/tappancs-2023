public class AttachPipeTest extends Tester{

    Plumber plumber;
    Pump pump;
    Pipe pipe;

    @Override
    public void initializeTest() {
        plumber = new Plumber();
        pump = new Pump();
        pipe = new Pipe();
        plumber.SetElem(pump);
        plumber.SetPipe(pipe);
        pump.SetPlayer(plumber);
        IO.initializeTest(null);
    }

    @Override
    public void executeTest() {
        initializeTest();
        IO.funcCalled("AddPipe(pipe)");
        plumber.AddPipe(pipe);
        IO.returnCalled("void");
    }

    @Override
    public String toString(){
        return "AttachPipeTest";
    }
}
