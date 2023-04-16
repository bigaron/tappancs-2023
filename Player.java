public abstract class Player {
    protected Element elem;
    public void Move(int dir){
        IO.funcCalled("pipe.GetNeighbor(1)");
        Pump pump = (Pump)(elem.GetNeighbor(0));
        IO.returnCalled("pump");
        IO.funcCalled("pump.AcceptPlayer(this)");
        pump.AcceptPlayer(this);
        IO.returnCalled("true");
        IO.funcCalled("pipe.RemoveNeighbor(this)");
        elem.RemovePlayer(this);
        IO.returnCalled("void");
        IO.funcCalled("this.SetElem(pump)");
        this.SetElem(pump);
        IO.returnCalled("void");
    }
    public void ChangePumpDirection(int outgoingPipe){
        IO.funcCalled("ChangeDirection(outgoingPipe)");
        elem.ChangeDirection(outgoingPipe);
        IO.returnCalled("void");
    }
    public void RemovePipe(Pipe pipe){}
    public void AddPipe(Pipe pipe){
        IO.funcCalled("AttachPipe(pipe)");
        elem.AttachPipe(pipe);
        IO.returnCalled("void");
    }
    public void SetElem(Element elem) {
        this.elem = elem;
    }
}
