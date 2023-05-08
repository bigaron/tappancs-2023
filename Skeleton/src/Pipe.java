package Skeleton.src;
/**
 * A csövet reprezentáló osztály, az elem leszármazottja.
 */
public class Pipe extends Element{
    private Node neighbours[];
    public Pipe(){neighbours = new Node[2];}

    /**
     * A játékos rálép a csőre.
     * @param p a játékos
     * @return művelet sikeressége
     */
    @Override
    public boolean AcceptPlayer(Player p){
        if(players.size() == 0)
            return true;
        return false;
    }

    /**
     * Továbbítja a vizet a kimenetéhez csatlakoztatott Node-nak.
     * @param elem ahonnan jön a víz
     */
    @Override
    public void ForwardWater(Element elem){
        if(neighbours[1] == null) return;
        if(IO.input.get(0)) {
            return;
        }
        else if(IO.input.get(1)) {
            return;
        }
        else if(IO.input.get(2)) {
            return;
        }
        else {
            IO.funcCalled("Pump.ForwardWater(this)");
            neighbours[1].ForwardWater(this);
            IO.returnCalled("void");
        }
    }

    /**
     * A cső szabotálásakor meghívott függvény.
     */
    @Override
    public void SabotagePipe() {
        IO.funcCalled("this.ChangeElementMode(false)");
        this.ChangeElementMode(false);
        IO.returnCalled("void");
    }
    /**
     * A szomszédos elem lekérdezése.
     * @param dir megadjuk melyik szomszédját kérjük
     * @return a kért szomszéd
     */
    @Override
    public Element GetNeighbor(int dir){return neighbours[dir]; }

    /**
     * Egy pumpa beszúrása egy cső közepébe a cső kettévágásával.
     * @param pump3 a lerakandó pumpa
     */
    @Override
    public void Split(Pump pump3){
        Pump pump1 = (Pump) neighbours[0];
        IO.funcCalled("<<create>> newPipe");
        Pipe newPipe = new Pipe();
        IO.returnCalled("void");
        IO.funcCalled("Pump.RemoveNeighbor(this)");
        neighbours[0].RemoveNeighbor(this);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.RemoveNeighbor(pump1)");
        this.RemoveNeighbor(neighbours[0]);
        IO.returnCalled("void");
        IO.funcCalled("Pump.SetNeighbor(this)");
        pump3.SetNeighbor(this);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.SetNeighbor(pump3)");
        this.SetNeighbor(pump3);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.SetNeighbor(pump3)");
        newPipe.SetNeighbor(pump3);
        IO.returnCalled("void");
        IO.funcCalled("Pump.SetNeighbor(newPipe)");
        pump3.SetNeighbor(newPipe);
        IO.returnCalled("void");
        IO.funcCalled("Pipe.SetNeighbor(pump1)");
        newPipe.SetNeighbor(pump1);
        IO.returnCalled("void");
        IO.funcCalled("Pump.SetNeighbor(newPipe)");
        pump1.SetNeighbor(newPipe);
        IO.returnCalled("void");
    }

    /**
     * Megváltoztatja a cső modeját.
     * @param mode az új mode
     */
    @Override
    public void ChangeElementMode(boolean mode){}

    /**
     * A cső lecsatlakoztatása.
     * @param pipe
     * @return a művelet sikeressége
     */
    @Override
    public boolean TakeoffPipe(Pipe pipe){
        if(IO.input.get(1)) return false;
        IO.funcCalled("Pipe.SetDetached(true)");
        pipe.SetDetached(true);
        IO.returnCalled("void");
        return true;
    }
    /**
     *A szomszédos elem beállítása.
     * @param elem a beállítandó elem
     */
    @Override
    public void SetNeighbor(Element elem){
        if(neighbours[0] == null) neighbours[0] = (Node)elem;
        else neighbours[1] = (Node)elem;
    }

    /**
     * Beállítja, hogy a cső épp le van-e csatlakoztatva.
     * @param detached a lecsatlakoztatás állapota (igaz vagy hamis)
     */
    public void SetDetached(boolean detached){}
}
