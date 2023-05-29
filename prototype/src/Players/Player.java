package prototype.src.Players;

import prototype.src.Elements.Element;
import prototype.src.Elements.Node;
import prototype.src.Elements.Pipe;
import prototype.src.Modifier;

public class Player {
    protected String ID;
    protected Element elem;
    protected Pipe pipe;

    public String getID() {
        return ID;
    }

    //csúszós??
    public void Move(int dir){
        //dir érvénytelen?
        String id1 = elem.getID();
        Element neighbour = elem.GetNeighbor(dir);
        if(neighbour == null) return;
        int successful = neighbour.AcceptPlayer(this);
        if(successful == 1) {
            boolean result = elem.RemovePlayer(this);
            if(!result) {
                //ragadós és nem tudott lelépni
                System.out.println("A játékos nem léphet " + dir + " irányba, mert a cső amin állt, ragacsos volt.\n");
                neighbour.RemovePlayer(this);
            } else {
                String id2 = neighbour.getID();
                //if(neighbour)
                elem = neighbour;
                System.out.println("A játékos sikeresen elmozdult " + dir + " irányba.\n" +
                        "A(z) " + id1 + " elemről sikeresen lelépett.\n" +
                        "A(z) " + id2 + " elemre lépett.\n");
            }
        }else if(successful == -1){
            System.out.println("A játékos nem léphet " + dir + " irányba, mert a csövön, amire lépni akart, már állnak.\n");
        } else if(successful == 0) {
            System.out.println("A játékos nem léphet " + dir + " irányba, mert a cső csúszós, a játékos a(z) " + elem.getID() + " elemre került\n");
        }
        //cső csúszós?
    }

    public void changePumpDirection(int outgoingPipe) {
        elem.ChangeDirection(outgoingPipe);
    }

    public void RemovePipe(int dir) { //TODO ezzel bajok vannak nem is kicsi
        Element e = elem.GetNeighbor(dir);
        if(!e.getID().contains("pipe")) { //megnézzük, hogy a szomszéd amin állunk az tényleg cső-e
            System.out.println("A cső lecsatolása sikeretelen volt, mert nem Node-on állunk\n");
            return;
        }
        if(this.pipe != null || e == null) { //ha van a kezünkben cső? vagy érvénytelen dir
            System.out.println("A cső lecsatolása érvéntelen volt, mert érvénytelen input.\n");
            return;
        }
        int result = elem.TakeoffPipe((Pipe) e); //e a pipe amit leszedünk
        if(result == 1) {
            this.pipe = (Pipe) e; //kezébeveszi de miért nem????
            this.pipe.SetDetached(true);
            System.out.println("A(z) "+ pipe.getID()+" cső lecsatolása sikeres volt, a(z) " +pipe.getID()+ " cső egyik vége a kezünkbe került.\n");
        }
        //if result == 0 -> kiírás a többi fv-ben megvan, itt nem csinálunk semmit.
    }

    public void AddPipe() {
        if(this.pipe != null) {
            elem.AttachPipe(pipe);
            elem.AttachPipe(pipe);
            System.out.println("A(z) " + pipe.getID() + " csövet sikeresen lehelyeztük.\n");
            pipe = null;
        }else
            System.out.println("A cső lehelyezése sikertelen, mert nincs a kezünkben cső.\n");
    }

    public Pipe getPipe(){ return pipe; }

    public void SetElem(Element elem) {
        this.elem = elem;
    }

    public void SabotagePipe() {
        if(!elem.SabotagePipe()){
            System.out.println("Az " +elem.getID()+ " cső lyukasztása sikertelen, mivel az "+getID()+ " játékos nem csövön áll.\n");
        }
    }

    public void MakeSticky() {
        elem.ChangeSurface(Modifier.Sticky);
    }

    public void Slipped(Node n) {
        n.AcceptPlayer(this);
        elem.RemovePlayer(this);
        elem = n;
    }
    
    /**
     * Beállítja a felvett csövet az "inventory"-jában.
     * @param pipe a felvett cső
     */
    public void SetPipe(Pipe pipe) {
        this.pipe = pipe;
    }
    public void list(){}
    public Element getElement(){return elem;}
}
