package prototype.src.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import prototype.src.Elements.*;
import prototype.src.Game;
import prototype.src.Players.*;
import prototype.src.Modifier;
/**
 * A csövet reprezentáló osztály, az elem leszármazottja.
 */
public class Pipe extends Element{
    public List<Node> neighbours;
    private Modifier state;
    private boolean detached;
    private int sabotageable;
    private int modifiedState;
    public static int counter = 0;

    public Modifier getState() {
        return state;
    }

    public Pipe(){
        neighbours = new ArrayList<>();
        neighbours.add(null);
        neighbours.add(null);
        sabotageable = 0;
        modifiedState = 0;
        state = Modifier.Plain;
        ID = "pipe" + ++counter;
    }

    @Override
    public void SetNeighbor(Element elem){
        if(neighbours.get(0) == null) neighbours.set(0, (Node)elem);
        else neighbours.set(1, (Node)elem);
    }  

    /**
     * A játékos rálép a csőre.
     * @param p a játékos
     * @return művelet sikeressége
     * TODO: random ertek itt is SOLVED?
     */
    @Override
    public int AcceptPlayer(Player p){
        if(players.size() != 0 || detached) return -1; //ha állnak rajta vagy le van csatlakoztatva
        if(state != Modifier.Slippery) {
            players.add(p);
            return 1; //ha sikerült elfogadni
        }
        if(state == Modifier.Slippery) {
            if(Game.random) {
                if(neighbours.size() == 2) {
                    Random random = new Random();
                    p.Slipped(neighbours.get(random.nextInt(2)));
                } else {
                    if(neighbours.get(0) != null) {
                        p.Slipped(neighbours.get(0));
                    } else {
                        p.Slipped(neighbours.get(1));
                    }
                }
            } else {
                if(neighbours.get(0) != null) p.Slipped(neighbours.get(0));
                else {
                    p.Slipped(neighbours.get(1));
                }
            }
        }

        return 0; // 0 ha slippery
    }

    /**
     * Továbbítja a vizet a kimenetéhez csatlakoztatott Node-nak.
     * @param elem ahonnan jön a víz
     */
    @Override
    public void ForwardWater(Element elem){
        //TODO: pont adas a szabotoroknek 0-as csapat a szabotor SOLVED
        if(detached || !working) {
            Game.increasePoints(0);
            return;
        }
        if(elem != GetNeighbor(0)){
            GetNeighbor(0).ForwardWater(this);
            return;
        }
        GetNeighbor(1).ForwardWater(this);
    }

    /**
     * A szomszédos elem lekérdezése.
     * @param dir megadjuk melyik szomszédját kérjük
     * @return a kért szomszéd, vagy null, ha az nem letezik
     */
    @Override
    public Element GetNeighbor(int dir){return dir > neighbours.size() ? null : neighbours.get(dir); }
    
    /**
     * Egy pumpa beszúrása egy cső közepébe a cső kettévágásával.
     * @param newPump a lerakandó pumpa
     */
    @Override
    public void Split(Pump newPump){
        Pump pump1 = (Pump) neighbours.get(0);
        Pipe newPipe = new Pipe();
        neighbours.get(0).RemoveNeighbor(this); 
        this.RemoveNeighbor(neighbours.get(0));
        newPump.SetNeighbor(this);
        this.SetNeighbor(newPump);
        newPipe.SetNeighbor(newPump);
        newPump.SetNeighbor(newPipe);     
        newPipe.SetNeighbor(pump1);   
        pump1.SetNeighbor(newPipe);
        System.out.println("A(z) " + newPump.getID()+ " lerakása sikeres volt.\n");
    }

    /**
     * Megváltoztatja a cső modeját.
     * @param mode az új mode
     */
    @Override
    public void ChangeElementMode(boolean mode){ super.ChangeElementMode(false); }

    @Override
    public int TakeoffPipe(Pipe pipe){
        if(players.size() > 0) {
            System.out.println("A(z) " +getID()+ " cső lecsatolása sikeretelen volt, mert állnak a csövön.\n");
            return -1; //ha állnak
        }
        if(neighbours.size() == 1) return 1; //ha csak 1 szomszéd
        return 2;
    }

    public void SetDetached(boolean detached){
        this.detached = detached;
    }

    //TODO: mit csinal ez a fuggveny?
    public void PickedUp(){
        super.ChangeElementMode(false);
        detached = true;
        for(Node n : neighbours){
            n.RemoveNeighbor(this);
            this.RemoveNeighbor(n);
        }
    }


    public void ChangeSurface(Modifier m){
        if(state == Modifier.Plain) {
            SetSurface(m);
            System.out.println("A felület megváltozott " + m + " típusúvá.\n");
        }else{
            System.out.println(" A felület nem változott meg, mert a felület nem plain\n");
        }
    }

    @Override
    public void Step(){
        if(sabotageable > 0) DecreaseTime(0);
        if(modifiedState > 0) DecreaseTime(1);
    }

    public void SetSurface(Modifier m){ state = m;}

    public boolean RemovePlayer(Player p){
        if(state == Modifier.Sticky) return false;
        return super.RemovePlayer(p);
    }

    public void Repair(){
        working = true;
        sabotageable = 5;
    }

    private void DecreaseTime(int target){
        switch(target){
            case 0:{
                sabotageable--;
                break;
            }
            case 1:{
                modifiedState--;
                if(modifiedState == 0) state = Modifier.Plain;
                break;
            }
        }
    }

    /**
     * A szomszédos elem eltávolítása.
     * @param elem az eltávolítandó elem.
    */
    @Override
    public void RemoveNeighbor(Element elem) {
        neighbours.remove((Node) elem);
    }

    @Override
    public void Save(FileWriter writer, boolean objectState) {
        try {
            if(objectState) {
                writer.write("pipe+" + ID + "+" + working + "+" + state + "+" + detached + "+" + sabotageable + "+" + modifiedState + "\n");
            } else {
                writer.write("pipe+" + ID);
                for(Player player : players) {
                    writer.write("+" + player.getID());
                }
                if(players.size() == 0) {
                    writer.write("+null");
                }
                if(neighbours.get(0) != null && neighbours.get(1) != null) {
                    writer.write("+" + neighbours.get(0).getID() + ",");
                    writer.write(neighbours.get(1).getID() + "\n");
                } else if(neighbours.get(0) != null && neighbours.get(1) == null) {
                    writer.write("+" + neighbours.get(0).getID() + ",");
                    writer.write("null\n");
                } else if(neighbours.get(0) == null && neighbours.get(1) != null) {
                    writer.write("+null," + neighbours.get(1).getID() + "\n");
                } else {
                    writer.write("+null,null\n");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void Read(String[] parsed){
        if(!parsed[0].equals("pipe")) return;
        ID = parsed[1];
        working = Boolean.parseBoolean(parsed[2]);
        state = Game.parseModifier(parsed[3]);
        detached = Boolean.parseBoolean(parsed[4]);
        sabotageable = Integer.parseInt(parsed[5]);
        modifiedState = Integer.parseInt(parsed[6]);
    }
    /**
     * Returns true if the paramater ID is already in the neighbours list, false otherwise
     */
    @Override
    public boolean containsNeighbour(String ID){
        for(Node p: neighbours) if(p.getID() == ID) return true;
        return false;
    }

    public boolean SabotagePipe() {
        if(sabotageable == 0) {
            working = false;
            System.out.println("A(z) " + getID() + " cső kilyukadt.\n");
        }
        else{
            System.out.println("A(z) " + getID() + " cső lyukasztása sikertelen, mivel a(z) " +getID()+ " cső még nem szabotálható.\n");
        }
        return true;
    }
}
