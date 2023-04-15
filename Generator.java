import java.util.ArrayList;
import java.util.List;

public class Generator implements Steppable{
    private Cistern cistern;
    private List<Pump> pumps = new ArrayList<Pump>();
    public Element GenerateElem(){return new Cistern();}
    public void Step(){}
    public Pump RequestPump(){return new Pump();}
    public void SetCistern(Cistern c) { cistern = c; }
}
