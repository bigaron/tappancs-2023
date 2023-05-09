package prototype.src.Elements;
import java.util.ArrayList;
import java.util.List;

/**
 * Az Elementből származik, a pumpa, a forrás, és a ciszterna ősosztálya.
 */
public abstract class Node extends Element {
    protected List<Pipe> neighbours = new ArrayList<Pipe>();

    /**
     * A játékos rálép a Node-ra.
     * @param p a rálépő játékos
     * @return a művelet sikeressége.
     */
    @Override
    public boolean AcceptPlayer(Player p){return true;}
    /**
     * A szomszédos elem lekérdezése.
     * @param dir megadjuk melyik szomszédját kérjük
     * @return a kért szomszéd
     */
    @Override
    public Element GetNeighbor(int dir){return neighbours.get(dir);}

    /**
     *A szomszédos elem beállítása.
     * @param elem a beállítandó elem
     */
    @Override
    public void SetNeighbor(Element elem){
        neighbours.add((Pipe)elem);
    }

    /**
     * A szomszédos elem eltávolítása.
     * @param elem az eltávolítandó elem.
     */
    @Override
    public void RemoveNeighbor(Element elem) {
        neighbours.remove(elem);
    }
}
