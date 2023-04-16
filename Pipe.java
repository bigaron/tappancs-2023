public class Pipe extends Element{
    private Node neighbours[];
    public Pipe(){neighbours = new Node[2];}
    @Override
    public boolean AcceptPlayer(Player p){
        if(players.size() == 0)
            return true;
        return false;
    }
    @Override
    public void ForwardWater(Element elem){
        if(neighbours[1] == null) return;
        if(IO.input.get(0)) {
            return;
        }
        else if(IO.input.get(1)) {
            return;
        }
        else if(IO.input.get(2)) {
            return;
        }
        else {
            IO.funcCalled("Pump.ForwardWater(this)");
            neighbours[1].ForwardWater(this);
            IO.returnCalled("void");
        }
    }
    @Override
    public void SabotagePipe() {
        IO.funcCalled("this.ChangeElementMode(false)");
        this.ChangeElementMode(false);
        IO.returnCalled("void");
    }
    @Override
    public Element GetNeighbor(int dir){return neighbours[dir]; }
    @Override
    public void Split(Pump pump3){
        Pump pump1 = (Pump) neighbours[0];
        IO.funcCalled("<<create>> newPipe");
        Pipe newPipe = new Pipe();
        IO.returnCalled("void");
        IO.funcCalled("Pump.RemoveNeighbor(this)");
        neighbours[0].RemoveNeighbor(this);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.RemoveNeighbor(pump1)");
        this.RemoveNeighbor(neighbours[0]);
        IO.returnCalled("void");
        IO.funcCalled("Pump.SetNeighbor(this)");
        pump3.SetNeighbor(this);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.SetNeighbor(pump3)");
        this.SetNeighbor(pump3);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.SetNeighbor(pump3)");
        newPipe.SetNeighbor(pump3);
        IO.returnCalled("void");
        IO.funcCalled("Pump.SetNeighbor(newPipe)");
        pump3.SetNeighbor(newPipe);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.SetNeighbor(pump1)");
        newPipe.SetNeighbor(pump1);
        IO.returnCalled("void");
        IO.funcCalled("Pump.SetNeighbor(newPipe)");
        pump1.SetNeighbor(newPipe);
        IO.returnCalled("void");
    }
    @Override
    public void ChangeElementMode(boolean mode){}
    @Override
    public boolean TakeoffPipe(Pipe pipe){
        if(IO.input.get(1)) return false;
        IO.funcCalled("Pipe.SetDetached(true)");
        pipe.SetDetached(true);
        IO.returnCalled("void");
        return true;
    }
    @Override
    public void SetNeighbor(Element elem){
        if(neighbours[0] == null) neighbours[0] = (Node)elem;
        else neighbours[1] = (Node)elem;
    }
    public void SetDetached(boolean detached){}
}
