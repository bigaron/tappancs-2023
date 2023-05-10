package prototype.src.Elements;

import java.util.ArrayList;
import java.util.List;
import prototype.src.Players.*;

/**
 * Az Elementből származik, a pumpa, a forrás, és a ciszterna ősosztálya.
 */
public abstract class Node extends Element {
    private List<Pipe> neighbours = new ArrayList<Pipe>();

    /**
     * A játékos rálép a Node-ra.
     * @param p a rálépő játékos
     * @return a művelet sikeressége.
     */
    @Override
    public boolean AcceptPlayer(Player p){
        return super.AcceptPlayer(p);
    }
    /**
     * A szomszédos elem lekérdezése.
     * @param dir megadjuk melyik szomszédját kérjük
     * @return a kért szomszéd, vagy null, ha tul nagy szamot adott a felhasznalo
     */
    @Override
    public Element GetNeighbor(int dir){
        if(dir >= neighbours.size()) return null;
        return neighbours.get(dir);
    }

    @Override
    public boolean GetPipe(Pipe pipe){
        int res = pipe.TakeoffPipe(pipe);
        if(res == -1) return false;
        pipe.PickedUp();
        return true;
    }

    @Override
    public void AttachPipe(Pipe pipe){
        pipe.SetNeighbor(this);
        SetNeighbor(pipe);
    }

    @Override
    public int TakeoffPipe(Pipe pipe){
        int ret = pipe.TakeoffPipe(pipe);
        if(ret == -1) return  0;
        if(ret == 1) return 0;
        if(ret == 2) {
            pipe.RemoveNeighbor(this);
            RemoveNeighbor(pipe);
        }
        return 1;
    }
    
    /**
     * A szomszédos elem eltávolítása.
     * @param elem az eltávolítandó elem.
     */
    @Override
    public void RemoveNeighbor(Element elem) {
        neighbours.remove(elem);
    }
    /**
     *A szomszédos elem beállítása.
     * @param elem a beállítandó elem
     */
    @Override
    public void SetNeighbor(Element elem){
        neighbours.add((Pipe)elem);
    }
}
