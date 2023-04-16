import java.util.ArrayList;
import java.util.List;

public class Generator implements Steppable{
    private Cistern cistern;
    private List<Pump> pumps = new ArrayList<Pump>();
    public Element GenerateElem(){
        Element result;
        if(!IO.input.get(1)) {
            IO.funcCalled("Generator.GeneratePipe()");
            result = this.GeneratePipe();
            IO.returnCalled("pipe");
        } else {
            IO.funcCalled("Generator.GeneratePump()");
            result = this.GeneratePump();
            IO.returnCalled("pump");
        }

        return result;
    }
    public void Step(){
        if(!IO.input.get(0))
            return;
        else {
            IO.funcCalled("Generator.GenerateElem()");
            this.GenerateElem();
            if(IO.input.get(1))
                IO.returnCalled("pump");
            else
                IO.returnCalled("pipe");
        }
    }
    public Pump RequestPump(){return new Pump();}
    public void SetCistern(Cistern c) { cistern = c; }
    public Pump GeneratePump() {
        return new Pump();
    }

    public Pipe GeneratePipe() {
        return new Pipe();
    }
}
