public class Pump extends Node{
    private Pipe output;

    @Override
    public void ChangeDirection(int outgoingPipe){}
    @Override
    public void ForwardWater(Element elem){}
    @Override
    public void Step(){}
    @Override
    public boolean TakeoffPipe(Pipe pipe){return true;}
    @Override
    public void AttachPipe(Pipe pipe){}
    @Override
    public void ChangeElementMode(boolean mode){};
}
