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

    public void Move(int dir){
        Element neighbour = elem.GetNeighbor(dir);
        boolean successful = neighbour.AcceptPlayer(this);
        if(successful) {
            boolean result = elem.RemovePlayer(this);
            if(!result) {
                neighbour.RemovePlayer(this);
            }
        }
    }

    public void changePumpDirection(int outgoingPipe) {
        elem.ChangeDirection(outgoingPipe);
    }

    public void RemovePipe(int dir) {
        Element e = elem.GetNeighbor(dir);
        if(e.getID().indexOf("pipe") != -1) return;
        if(this.pipe != null || e == null) return;
        int result = elem.TakeoffPipe(pipe);
        if(result == 1) {
            this.pipe = (Pipe)e;
        }
    }

    public void AddPipe() {
        if(this.pipe != null) {
            elem.AttachPipe(pipe);
            pipe = null;
        }
    }

    public void SetElem(Element elem) {
        this.elem = elem;
    }

    public void SabotagePipe() {
        elem.SabotagePipe();
    }

    public void MakeSticky() {
        elem.ChangeSurface(Modifier.Sticky);
    }

    public void Slipped(Node n) {
        n.AcceptPlayer(this);
        elem = n;
    }
    
    /**
     * Beállítja a felvett csövet az "inventory"-jában.
     * @param pipe a felvett cső
     */
    public void SetPipe(Pipe pipe) {
        this.pipe = pipe;
    }
}
