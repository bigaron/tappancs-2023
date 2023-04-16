import java.util.ArrayList;
import java.util.List;

public abstract class Node extends Element {
    protected List<Pipe> neighbours = new ArrayList<Pipe>();
    @Override
    public boolean AcceptPlayer(Player p){return true;}
    @Override
    public Element GetNeighbor(int dir){return neighbours.get(dir);}
    @Override
    public void SetNeighbor(Element elem){
        neighbours.add((Pipe)elem);
    }
    @Override
    public void RemoveNeighbor(Element elem) {
        neighbours.remove(elem);
    }
}
