package prototype.src.Elements;

import prototype.src.*;
/**
 * A pumpát reprezentáló osztály, a Node leszármazottja.
 */
public class Pump extends Node{
    private Pipe output;
    private boolean buffer;
    /**
     * Megváltoztatja a pumpa folyásirányát.
     * @param outgoingPipe a kimeneti cső
     */
    @Override
    public void ChangeDirection(int outgoingPipe){
        output = (Pipe)GetNeighbor(outgoingPipe);
    }

    /**
     * A víz továbbítását végző függvény.
     * @param elem az elem ahonnan jön a víz
     */
    @Override
    public void ForwardWater(Element elem){
        if(buffer || !getWorking() || elem == output) return;
        output.ForwardWater(elem);
    }

    /**
     * A Steppable függvényének implementálása, a pumpa random elromlik.
     * 
     *  TODO: ertekek megadasa
     */
    @Override
    public void Step(){
        if(Game.random){

        }
    }

    /**
     * A pumpa mode-ját megváltoztató függvény
     * @param mode az új mode
     */
    @Override
    public void ChangeElementMode(boolean mode){
        buffer = mode;
    }

    /**
     * A pumpáról egy cső lecsatolása.
     * @param pipe a lecsatolandó cső
     * @return művelet sikeressége.
     */
    @Override
    public int TakeoffPipe(Pipe pipe){ 
        int ret = pipe.TakeoffPipe(this);
        if(ret == -1) return 0;
        if(ret == 1) return 0;
        if(ret == 2){
            pipe.RemoveNeighbor(this);
            if(pipe == output) super.ChangeElementMode(false); 
            RemoveNeighbor(pipe);
            return 1;
        }
    }
    /**
     * A kimeneti cső beállítása.
     * @param pipe a beállítandó cső
     */
    public void SetOutputPipe(Pipe pipe) {
        output = pipe;
    }
    /**
     * A pumpához egy cső hozzácsatolása.
     * @param pipe a hozzácsatolandó cső
     */
    @Override
    public void AttachPipe(Pipe pipe){
        output = pipe;
        IO.funcCalled("Pipe.SetNeighbor(this)");
        output.SetNeighbor(this);
        IO.returnCalled("void");

        IO.funcCalled("Pump.SetNeighbor(pipe)");
        SetNeighbor(pipe);
        IO.returnCalled("void");
    }

}
