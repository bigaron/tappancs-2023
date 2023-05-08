package Skeleton.src;
/**
 * A játékosokat reprezentáló osztály, a szabotőr és szerelő őse.
 */
public abstract class Player {
    protected Element elem;

    /**
     * A játékosok mozgató függvénye a játékban.
     * @param dir az irány amerre mozogni szeretne a játékos
     */
    public void Move(int dir){
        IO.funcCalled("Element.GetNeighbor(1)");
        Element neighbor = (elem.GetNeighbor(0));
        IO.returnCalled("result");
        IO.funcCalled("result.AcceptPlayer(this)");
        boolean successful = neighbor.AcceptPlayer(this);
        if(successful) {
            IO.returnCalled("true");
            IO.funcCalled("Element.RemoveNeighbor(this)");
            elem.RemovePlayer(this);
            IO.returnCalled("void");
            IO.funcCalled("this.SetElem(pump)");
            this.SetElem(neighbor);
            IO.returnCalled("void");
        } else {
            IO.returnCalled("false");
        }
    }

    /**
     * Pumpa folyásirányának megváltoztatása.
     * @param outgoingPipe a beállítandó kimenet cső
     */
    public void ChangePumpDirection(int outgoingPipe){
        IO.funcCalled("Pump.ChangeDirection(outgoingPipe)");
        elem.ChangeDirection(outgoingPipe);
        IO.returnCalled("void");
    }

    /**
     * Cső eltávolítása.
     * @param pipe az eltávolítandó cső
     */
    public void RemovePipe(Pipe pipe){
        if(IO.input.get(0)) return;
        else{
            IO.funcCalled("Element.TakeoffPipe(pipe)");
            boolean ret = elem.TakeoffPipe(pipe);
            IO.returnCalled(Boolean.toString(ret));
        }
    }

    /**
     * Cső hozzáadása.
     * @param pipe a hozzáadandó cső.
     */
    public void AddPipe(Pipe pipe){
        IO.funcCalled("Element.AttachPipe(pipe)");
        elem.AttachPipe(pipe);
        IO.returnCalled("void");
    }

    /**
     * Beállítja, melyik elemen áll épp a játékos.
     * @param elem az elem amin áll.
     */
    public void SetElem(Element elem) {
        this.elem = elem;
    }
}
