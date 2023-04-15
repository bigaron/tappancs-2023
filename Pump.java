public class Pump extends Node{
    private Pipe output;

    @Override
    public void ChangeDirection(int outgoingPipe){
        IO.funcCalled("GetNeighbor(outgoingPipe)");
        output = (Pipe)GetNeighbor(outgoingPipe);
        IO.returnCalled("pipe");
    }
    @Override
    public void ForwardWater(Element elem){
        if(!IO.input.get(0)) {
            return;
        } else if(IO.input.get(1)) {
            return;
        } else if(IO.input.get(2)) {
            return;
        } else {
            IO.funcCalled("ForwardWater(this)");
            output.ForwardWater(this);
            IO.returnCalled("void");
        }
    }
    @Override
    public void Step(){}
    @Override
    public boolean TakeoffPipe(Pipe pipe){return true;}
    @Override
    public void AttachPipe(Pipe pipe){
        output = pipe;
        IO.funcCalled("SetNeighbor(this)");
        output.SetNeighbor(this);
        IO.returnCalled("void");

        IO.funcCalled("SetNeighbor(pipe)");
        SetNeighbor(pipe);
        IO.returnCalled("void");
    }
    @Override
    public void ChangeElementMode(boolean mode){};

    public void SetOutputPipe(Pipe pipe) {
        output = pipe;
    }
}
