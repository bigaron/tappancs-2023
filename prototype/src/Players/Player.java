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
        //dir érbvénytelen?
        String id1 = elem.getID();
        Element neighbour = elem.GetNeighbor(dir);
        boolean successful = neighbour.AcceptPlayer(this);
        if(successful) {
            boolean result = elem.RemovePlayer(this);
            if(!result) {
                //ragadós és nem tudott lelépni
                System.out.println("A játékos nem léphet" + dir + "irányba, mert a cső amin állt, ragacsos volt.\n");
                neighbour.RemovePlayer(this);
            } else {
                String id2 = neighbour.getID();
                //if(neighbour)
                elem = neighbour;
                System.out.println("A játékos sikeresen lemozdult " + dir + " irányba.\n" +
                        "A(z) " + id1 + " elemről sikeresen lelépett.\n" +
                        "A(z) " + id2 + " elemre lépett.\n");
            }
        }else{
            System.out.println("A játékos nem léphet" + dir + "irányba, mert a csövön, amire lépni akart, már állnak.\n");

        }
        //cső csúszós?
    }

    public void changePumpDirection(int outgoingPipe) {
        elem.ChangeDirection(outgoingPipe);
    }

    public void RemovePipe(int dir) {
        Element e = elem.GetNeighbor(dir);
        if(e.getID().indexOf("pipe") != -1) {
            System.out.println("A cső lecsatolása sikeretelen volt, mert nem Node-on állunk\n"); //TODO ez itt ez az if ugye?
            return;
        }
        if(this.pipe != null || e == null) { //ez az érvénytelen szomszéd ugye?
            System.out.println("A cső lecsatolása érvéntelen volt, mert érvénytelen input.\n");
            return;
        }
        int result = elem.TakeoffPipe(pipe);
        if(result == 1) {
            this.pipe = (Pipe)e;
            System.out.println("A(z) "+ pipe.getID()+" cső lecsatolása sikeres volt, a(z) " +pipe.getID()+ " cső egyik vége a kezünkbe került.\n");
        }
    }

    public void AddPipe() {
        if(this.pipe != null) {
            elem.AttachPipe(pipe);
            System.out.println("A(z) " + pipe.getID() + " csövet sikeresen lehelyeztük.\n");
            pipe = null;
        }else
            System.out.println("A cső lehelyezése sikertelen, mert nincs a kezünkben cső.\n");
    }

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
        //elem = n;
    }
    
    /**
     * Beállítja a felvett csövet az "inventory"-jában.
     * @param pipe a felvett cső
     */
    public void SetPipe(Pipe pipe) {
        this.pipe = pipe;
    }
}
