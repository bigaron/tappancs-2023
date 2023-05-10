package prototype.src.Players;

import prototype.src.Elements.Element;
import prototype.src.Elements.Node;
import prototype.src.Elements.Pipe;

import static prototype.src.Modifier.Sticky;

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

    public void RemovePipe(Pipe pipe) {
        if(this.pipe != null) return;
        int result = elem.TakeoffPipe(pipe);
        if(result == 1) {
            this.pipe = pipe;
        }
    }

    public void AddPipe(Pipe pipe) {
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
        elem.ChangeSurface(Sticky);
    }

    public void Slipped(Node n) {
        n.AcceptPlayer(this);
        elem = n;
    }
}
