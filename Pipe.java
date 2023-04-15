public class Pipe extends Element{
    private Node neighbours[];
    public Pipe(){neighbours = new Node[2];}
    @Override
    public boolean AcceptPlayer(Player p){return true;}
    @Override
    public void ForwardWater(Element elem){
        if(neighbours[1] == null) return;
    }
    @Override
    public Element GetNeighbor(int dir){return new Pipe();}
    @Override
    public void Split(Pump pump3){
        Pump pump1 = (Pump) neighbours[0];
        IO.funcCalled("<<create>> newPipe");
        Pipe newPipe = new Pipe();
        IO.returnCalled("void");
        IO.funcCalled("RemoveNeighbor(this)");
        neighbours[0].RemoveNeighbor(this);
        IO.returnCalled("void");
        IO.funcCalled("RemoveNeighbor(pump1)");
        this.RemoveNeighbor(neighbours[0]);
        IO.returnCalled("void");
        IO.funcCalled("SetNeighbor(this)");
        pump3.SetNeighbor(this);
        IO.returnCalled("void");
        IO.funcCalled("SetNeighbor(pump3)");
        this.SetNeighbor(pump3);
        IO.returnCalled("void");
        IO.funcCalled("SetNeighbor(pump3)");
        newPipe.SetNeighbor(pump3);
        IO.returnCalled("void");
        IO.funcCalled("SetNeighbor(newPipe)");
        pump3.SetNeighbor(newPipe);
        IO.returnCalled("void");
        IO.funcCalled("SetNeighbor(pump1)");
        newPipe.SetNeighbor(pump1);
        IO.returnCalled("void");
        IO.funcCalled("SetNeighbor(newPipe)");
        pump1.SetNeighbor(newPipe);
        IO.returnCalled("void");
    }
    @Override
    public void ChangeElementMode(boolean mode){}
    @Override
    public boolean TakeoffPipe(Pipe pipe){return false;}
    @Override
    public void SetNeighbor(Element elem){
        if(neighbours[0] == null) neighbours[0] = (Node)elem;
        else neighbours[1] = (Node)elem;
    }
    public void SetDetached(boolean detached){}
}
