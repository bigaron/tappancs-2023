package Skeleton.Tests;

import Skeleton.src.IO;
import Skeleton.src.Pipe;
import Skeleton.src.Plumber;
import Skeleton.src.Pump;

/**
 *A cső csatlakoztatásához létrehozott teszt.
 */
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
        IO.funcCalled("Plumber.AddPipe(pipe)");
        plumber.AddPipe(pipe);
        IO.returnCalled("void");
    }

    @Override
    public String toString(){
        return "AttachPipeTest";
    }
}
