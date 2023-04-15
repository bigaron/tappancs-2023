public abstract class Player {
    protected Element elem;
    public void Move(int dir){}
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
