public class Pump extends Node{
    private Pipe output;

    @Override
    public void ChangeDirection(int outgoingPipe){
        IO.funcCalled("Pump.GetNeighbor(outgoingPipe)");
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
            IO.funcCalled("Pipe.ForwardWater(this)");
            output.ForwardWater(this);
            IO.returnCalled("void");
        }
    }
    @Override
    public void Step(){
        if(IO.input.get(0)) {
            IO.funcCalled("Pump.ChangeElementMode(false)");
            this.ChangeElementMode(false);
            IO.returnCalled("void");
        }
    }
    @Override
    public boolean TakeoffPipe(Pipe pipe){ 
        IO.funcCalled("Pipe.TakeoffPipe(pipe)");
        boolean ret = pipe.TakeoffPipe(pipe);
        IO.returnCalled(Boolean.toString(ret));
        if(!ret) return false;
        
        IO.funcCalled("Pump.RemoveNeighbor(pipe)");
        RemoveNeighbor(pipe);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.RemoveNeighbor(this)");
        pipe.RemoveNeighbor(this);
        IO.returnCalled("void");
        
        if(!IO.input.get(2)) return true;
        IO.funcCalled("Pump.ChangeElementMode(false)");
        ChangeElementMode(false);
        IO.returnCalled("void");
        return true;
    }
    @Override
    public void AttachPipe(Pipe pipe){
        output = pipe;
        IO.funcCalled("Pipe.SetNeighbor(this)");
        output.SetNeighbor(this);
        IO.returnCalled("void");

        IO.funcCalled("Pump.SetNeighbor(pipe)");
        SetNeighbor(pipe);
        IO.returnCalled("void");
    }
    @Override
    public void ChangeElementMode(boolean mode){};

    public void SetOutputPipe(Pipe pipe) {
        output = pipe;
    }
}
