public abstract class Player {
    protected Element elem;
    public void Move(int dir){
        IO.funcCalled("elem.GetNeighbor(1)");
        Element neighbor = (elem.GetNeighbor(0));
        IO.returnCalled("result");
        IO.funcCalled("result.AcceptPlayer(this)");
        boolean successful = neighbor.AcceptPlayer(this);
        if(successful) {
            IO.returnCalled("true");
            IO.funcCalled("elem.RemoveNeighbor(this)");
            elem.RemovePlayer(this);
            IO.returnCalled("void");
            IO.funcCalled("this.SetElem(pump)");
            this.SetElem(neighbor);
            IO.returnCalled("void");
        } else {
            IO.returnCalled("false");
        }
    }
    public void ChangePumpDirection(int outgoingPipe){
        IO.funcCalled("ChangeDirection(outgoingPipe)");
        elem.ChangeDirection(outgoingPipe);
        IO.returnCalled("void");
    }
    public void RemovePipe(Pipe pipe){
        if(IO.input.get(0)) return;
        else{
            IO.funcCalled("Pump.TakeoffPipe(pipe)");
            boolean ret = elem.TakeoffPipe(pipe);
            IO.returnCalled(Boolean.toString(ret));
        }
    }
    public void AddPipe(Pipe pipe){
        IO.funcCalled("AttachPipe(pipe)");
        elem.AttachPipe(pipe);
        IO.returnCalled("void");
    }
    public void SetElem(Element elem) {
        this.elem = elem;
    }
}
