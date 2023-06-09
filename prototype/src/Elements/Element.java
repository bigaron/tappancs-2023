package prototype.src.Elements;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import prototype.src.Players.*;
import prototype.src.*;
import prototype.src.View.ElementView;

/**
 * A pumpa, cső, ciszterna és forrás ősosztálya.
 */
public abstract class Element implements Steppable{
    protected List<Player> players = new ArrayList<Player>();
    protected boolean working;
    protected String ID;
    protected ElementView view;

    public Element(){
        view = null;
        working = true;
        ID = "";
    }

    public ElementView getView() {
        return view;
    }

    public void setView(ElementView view) {
        this.view = view;
    }

    public int AcceptPlayer(Player p){
        players.add(p);
        return 1;
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

    public abstract boolean containsNeighbour(String id);
    public void ChangeDirection(int outgoingPipe){
        System.out.println("A kimenete nem változott meg, mert nem pumpán állunk.\n"); //ez kimeneti nyelvebn picit más
    }
    public abstract void ForwardWater(Element elem);
    public int TakeoffPipe(Pipe pipe){ return 0; }
    public void AttachPipe(Pipe pipe){System.out.println("A cső lehelyezése sikertelen, mert csövön állunk.\n");}
    public boolean SabotagePipe() {return false;}
    public void Step(){}
    public Pump GetPump(){ return null;}
    public boolean GetPipe(Pipe pipe){return true;}
    public void Split(Pump pump){System.out.println("A(z) " +pump.getID()+ " lerakása sikertelen, mert nem csövön állunk.\n");}
    public void ChangeElementMode(boolean mode){ working = mode; }
    public void SetNeighbor(Element elem){}  
    public void SetPlayer(Player player){}
    public abstract void RemoveNeighbor(Element elem);
    public void ChangeSurface(Modifier m){ System.out.println("A felület nem változott meg, mert nem csövön állunk\n");}
    public String getID(){ return ID; }
    public abstract void list();
    public boolean getWork(){return working;}
}
