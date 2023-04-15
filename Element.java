import java.util.ArrayList;
import java.util.List;

public abstract class Element implements Steppable{
    List<Player> players = new ArrayList<Player>();

    public abstract boolean AcceptPlayer(Player p);
    public void RemovePlayer(Player p){System.out.print("");}
    public abstract Element GetNeighbor(int dir);
    public void Repair(){}
    public void ChangeDirection(int outgoingPipe){}
    public abstract void ForwardWater(Element elem);
    public boolean TakeoffPipe(Pipe pipe){return true;}
    public void AttachPipe(Pipe pipe){}
    public void Step(){}
    public Pump GetItem(){ return new Pump();}
    public void Split(Pump pump){}
    public void ChangeElementMode(boolean mode){}
    public void SetNeighbor(Element elem){}
    public void SetPlayer(Player player){}
    public void RemoveNeighbor(Element elem){}
}
