package prototype.src.Elements;

import java.util.ArrayList;
import java.util.List;
import prototype.src.Players.*;
import prototype.src.*;

/**
 * A pumpa, cső, ciszterna és forrás ősosztálya.
 */
public abstract class Element implements Steppable{
    private List<Player> players = new ArrayList<Player>();
    private boolean working;

    public Element(){
        working = true;
    }

    public abstract boolean AcceptPlayer(Player p);
    /**
     * We remove the player from the list of players standing on this element
     * @param p a játékos
     */
    public void RemovePlayer(Player p){
        players.remove(p);
    }
    public abstract Element GetNeighbor(int dir);

    /**
     * Fixes the element.
     */
    public void Repair(){
        this.ChangeElementMode(true);
    }

    public void ChangeDirection(int outgoingPipe){}
    public abstract void ForwardWater(Element elem);
    public boolean TakeoffPipe(Pipe pipe){ return true; }
    public void AttachPipe(Pipe pipe){}
    public void SabotagePipe() {}
    public void Step(){}
    public Pump GetPump(){ return null;}
    public void Split(Pump pump){}
    /**
     * Changes the working private field
     * @param mode - the value it is changed to
     */
    public void ChangeElementMode(boolean mode){working = mode;}
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
