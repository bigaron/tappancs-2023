import java.util.ArrayList;
import java.util.List;

public abstract class Element implements Steppable{
    List<Player> players = new ArrayList<Player>();

    public abstract boolean AcceptPlayer(Player p);
    public void RemovePlayer(Player p){System.out.print("");}
    public abstract Element GetNeighbor(int dir);
    public void Repair(){
        IO.funcCalled("this.ChangeElementMode(true)");
        this.ChangeElementMode(true);
        IO.returnCalled("void");
    }
    public void SabotagePipe() {}
    public void ChangeDirection(int outgoingPipe){}
    public abstract void ForwardWater(Element elem);
    public boolean TakeoffPipe(Pipe pipe){
        // IO.funcCalled("Pipe.TakeoffPipe(pipe)");
        // boolean ret = pipe.TakeoffPipe(pipe);
        // IO.returnCalled(Boolean.toString(ret));
        // if(!ret) return false;
        
        // IO.funcCalled("Pump.RemoveNeighbor(pipe)");
        // RemoveNeighbor(pipe);
        // IO.returnCalled("void");
        // IO.funcCalled("Pipe.RemoveNeighbor(this)");
        // pipe.RemoveNeighbor(this);
        // IO.returnCalled("void");
        
        // if(IO.input.get(3)) return true;
        // IO.funcCalled("Pump.ChangeElementMode(false)");
        // ChangeElementMode(false);
        // IO.returnCalled("void");
         return true;
    }
    public void AttachPipe(Pipe pipe){}
    public void Step(){}
    public Pump GetPump(){ return null;}
    public void Split(Pump pump){}
    public void ChangeElementMode(boolean mode){}
    public void SetNeighbor(Element elem){}
    public void SetPlayer(Player player){}
    public void RemoveNeighbor(Element elem){}
}
