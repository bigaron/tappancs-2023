import java.util.ArrayList;
import java.util.List;

/**
 * A pumpa, cső, ciszterna és forrás ősosztálya.
 */
public abstract class Element implements Steppable{
    List<Player> players = new ArrayList<Player>();
    public abstract boolean AcceptPlayer(Player p);

    /**
     * A játékos lelép az elemről.
     * @param p a játékos
     */
    public void RemovePlayer(Player p){System.out.print("");}
    public abstract Element GetNeighbor(int dir);

    /**
     * Az elem megjavítása.
     */
    public void Repair(){
        IO.funcCalled("Pipe.ChangeElementMode(true)");
        this.ChangeElementMode(true);
        IO.returnCalled("void");
    }

    public void SabotagePipe() {}
    public void ChangeDirection(int outgoingPipe){}
    public abstract void ForwardWater(Element elem);
    public boolean TakeoffPipe(Pipe pipe){
         return true;
    }
    public void AttachPipe(Pipe pipe){}
    public void Step(){}
    public Pump GetPump(){ return null;}
    public void Split(Pump pump){}
    public void ChangeElementMode(boolean mode){}
    public void SetNeighbor(Element elem){}

    /**
     * Az elemen álló játékost beállító függvény.
     * @param player a beállítandó játékos
     */
    public void SetPlayer(Player player){
        players.add(player);
    }
    public void RemoveNeighbor(Element elem){}
}
