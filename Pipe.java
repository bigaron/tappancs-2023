public class Pipe extends Element{
    private Node neighbours[];
    public Pipe(){neighbours = new Node[2];}
    @Override
    public boolean AcceptPlayer(Player p){return true;}
    @Override
    public void ForwardWater(Element elem){}
    @Override
    public Element GetNeighbor(int dir){return new Pipe();}
    @Override
    public void Split(Pump pump){}
    @Override
    public void ChangeElementMode(boolean mode){}
    @Override
    public boolean TakeoffPipe(Pipe pipe){return false;}
    @Override
    public void SetNeighbor(Element elem){
        if(neighbours[0] == null) neighbours[0] = (Pump)elem;
        else neighbours[1] = (Pump)elem;
    }
    public void SetDetached(boolean detached){}
}
