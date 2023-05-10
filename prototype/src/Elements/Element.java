package prototype.src.Elements;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import prototype.src.Players.*;
import prototype.src.*;

/**
 * A pumpa, cső, ciszterna és forrás ősosztálya.
 */
public abstract class Element implements Steppable{
    protected List<Player> players = new ArrayList<Player>();
    protected boolean working;
    protected String ID;

    public Element(){
        working = true;
        ID = "";
    }

    public boolean AcceptPlayer(Player p){
        players.add(p);
        return true;
    }
    /**
     * We remove the player from the list of players standing on this element
     * @param p a játékos
     */
    public boolean RemovePlayer(Player p){
        players.remove(p);
        return true;
    }
    public abstract Element GetNeighbor(int dir);

    public abstract void Save(FileWriter writer, boolean objectState);

    /**
     * Fixes the element.
     */
    public void Repair(){
        working = true;
    }

    public void ChangeDirection(int outgoingPipe){}
    public abstract void ForwardWater(Element elem);
    public int TakeoffPipe(Pipe pipe){ return 0; }
    public void AttachPipe(Pipe pipe){}
    public void SabotagePipe() {}
    public void Step(){}
    public Pump GetPump(){ return null;}
    public boolean GetPipe(Pipe pipe){return true;}
    public void Split(Pump pump){}
    public void ChangeElementMode(boolean mode){ working = mode; }
    public void SetNeighbor(Element elem){}  
    public void SetPlayer(Player player){}
    public abstract void RemoveNeighbor(Element elem);
    public void ChangeSurface(Modifier m){}
    public String getID(){ return ID; }
}
